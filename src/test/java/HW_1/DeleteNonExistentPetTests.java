package hw_1;

import hw_1.dto.ErrorResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("DeletePetTests")
@DisplayName("DELETE Pet Operations")
public class DeleteNonExistentPetTests extends BaseTest {

    @Test
    @DisplayName("Verify that you cannot delete a non-existent pet")
    void verifyCannotDeleteNonExistentPet() {
        long nonExistentPetId = 8888888888888888L; // Another very large ID unlikely to exist

        // Verify that a non-existent pet cannot be deleted.
        // The Petstore API typically returns a 404 for deleting a non-existent resource.
        ErrorResponseDto errorResponse = given()
                .header("api_key", "special-key") // API key is often required for delete operations
                .pathParam("petId", nonExistentPetId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(404) // Expect 404 Not Found
                .extract().as(ErrorResponseDto.class); // Extract the response into our DTO

        assertThat(errorResponse, notNullValue());
        assertThat(errorResponse.getCode(), equalTo(1)); // Common error code for "not found"
        assertThat(errorResponse.getType(), equalTo("error"));
        assertThat(errorResponse.getMessage(), equalTo("Pet not found"));
    }
}