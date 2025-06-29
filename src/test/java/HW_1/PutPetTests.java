package hw_1;

import hw_1.dto.CategoryDto;
import hw_1.dto.PetDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.RandomStringUtils; // Import for generating random strings
import java.util.Collections;
import java.util.Random; // Import for generating random numbers

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("PutPetTests")
@DisplayName("PUT Pet Operations")
public class PutPetTests extends BaseTest {

    @Test
    @DisplayName("Verify pet can be modified")
    void verifyPetCanBeModified() {
        // Generate a unique pet ID to avoid conflicts
        long petId = new Random().nextInt(1000000) + 100000L; // Random ID between 100,000 and 1,099,999
        String initialPetName = "Buddy_" + RandomStringUtils.randomAlphanumeric(5);
        String initialStatus = "available";
        String initialCategoryName = "Dogs_" + RandomStringUtils.randomAlphabetic(3);

        CategoryDto initialCategory = new CategoryDto(1L, initialCategoryName);
        PetDto petToCreate = new PetDto(petId, initialCategory, initialPetName, Collections.singletonList("photo1.jpg"), Collections.emptyList(), initialStatus);

        // 1. Create an entity using POST.
        given()
                .contentType("application/json")
                .body(petToCreate)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo(initialPetName))
                .body("status", equalTo(initialStatus));

        // 2. Make a GET request and verify the entity was created.
        PetDto createdPet = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract().as(PetDto.class);

        assertNotNull(createdPet);
        assertThat(createdPet.getId(), equalTo(petId));
        assertThat(createdPet.getName(), equalTo(initialPetName));
        assertThat(createdPet.getStatus(), equalTo(initialStatus));
        assertThat(createdPet.getCategory().getName(), equalTo(initialCategoryName));

        // 3. Set some new values in the object used for the POST request (e.g., name, status, category.name).
        String updatedPetName = "MaxTheModified_" + RandomStringUtils.randomAlphanumeric(5);
        String updatedStatus = "sold";
        String updatedCategoryName = "UpdatedCats_" + RandomStringUtils.randomAlphabetic(3);

        petToCreate.setName(updatedPetName);
        petToCreate.setStatus(updatedStatus);
        petToCreate.setCategory(new CategoryDto(2L, updatedCategoryName));

        // 4. Make a PUT request with this modified object.
        given()
                .contentType("application/json")
                .body(petToCreate)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo(updatedPetName))
                .body("status", equalTo(updatedStatus))
                .body("category.name", equalTo(updatedCategoryName));

        // 5. Using a GET request, verify that the name, status, and category.name values have changed in this object.
        PetDto updatedPet = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract().as(PetDto.class);

        assertNotNull(updatedPet);
        assertThat(updatedPet.getId(), equalTo(petId));
        assertThat(updatedPet.getName(), equalTo(updatedPetName));
        assertThat(updatedPet.getStatus(), equalTo(updatedStatus));
        assertThat(updatedPet.getCategory().getName(), equalTo(updatedCategoryName));

        // Cleanup: Delete the created pet after the test
        given()
                .header("api_key", "special-key")
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(petId)));
    }
}