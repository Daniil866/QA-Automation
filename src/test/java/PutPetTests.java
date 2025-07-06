package tests;

import dtos.Category;
import dtos.Pet;
import dtos.Tag;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PutPetTests extends BaseTest {

    @Test
    @DisplayName("Verify pet can be modified using PUT operation")
    public void verifyPetCanBeModified() {
        Pet originalPet = new Pet();
        originalPet.setName("OriginalDog_" + System.currentTimeMillis());
        originalPet.setStatus("available");
        originalPet.setCategory(new Category(1L, "dogs"));
        originalPet.setPhotoUrls(Collections.singletonList("http://example.com/photo1.jpg"));
        originalPet.setTags(Collections.singletonList(new Tag(1L, "friendly")));

        Pet createdPet = given()
                .contentType(ContentType.JSON)
                .body(originalPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);

        assertNotNull(createdPet.getId());
        assertEquals(originalPet.getName(), createdPet.getName());
        assertEquals(originalPet.getStatus(), createdPet.getStatus());
        assertEquals(originalPet.getCategory().getName(), createdPet.getCategory().getName());

        Pet fetchedPet = given()
                .pathParam("petId", createdPet.getId())
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);

        assertEquals(createdPet.getId(), fetchedPet.getId());
        assertEquals(createdPet.getName(), fetchedPet.getName());
        assertEquals(createdPet.getStatus(), fetchedPet.getStatus());
        assertEquals(createdPet.getCategory().getName(), fetchedPet.getCategory().getName());

        String updatedName = "UpdatedCat_" + System.currentTimeMillis();
        String updatedStatus = "pending";
        String updatedCategoryName = "cats";

        createdPet.setName(updatedName);
        createdPet.setStatus(updatedStatus);
        createdPet.getCategory().setName(updatedCategoryName);

        Pet updatedPetResponse = given()
                .contentType(ContentType.JSON)
                .body(createdPet)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);

        assertNotNull(updatedPetResponse.getId());
        assertEquals(updatedName, updatedPetResponse.getName());
        assertEquals(updatedStatus, updatedPetResponse.getStatus());
        assertEquals(updatedCategoryName, updatedPetResponse.getCategory().getName());

        Pet fetchedUpdatedPet = given()
                .pathParam("petId", updatedPetResponse.getId())
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);

        assertEquals(updatedPetResponse.getId(), fetchedUpdatedPet.getId());
        assertEquals(updatedName, fetchedUpdatedPet.getName());
        assertEquals(updatedStatus, fetchedUpdatedPet.getStatus());
        assertEquals(updatedCategoryName, fetchedUpdatedPet.getCategory().getName());
    }
}