package hw_1;

import hw_1.dto.ErrorResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("GetPetTests")
@DisplayName("GET Pet Operations")
public class GetNonExistentPetTests extends BaseTest {

    @Test
    @DisplayName("Verify that an entity with a non-existent ID cannot be retrieved")
    void verifyNonExistentPetCannotBeRetrieved() {
        long nonExistentPetId = 9999999999999999L; // A very large ID unlikely to exist

        // Verify that an entity with a non-existent ID cannot be retrieved.
        // Expecting 404 Not Found and a corresponding error structure.
        ErrorResponseDto errorResponse = given()
                .pathParam("petId", nonExistentPetId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404) // Expect 404 Not Found
                .extract().as(ErrorResponseDto.class); // Extract the response into our DTO

        assertThat(errorResponse, notNullValue());
        assertThat(errorResponse.getCode(), equalTo(1)); // Common error code for "not found" in Petstore API
        assertThat(errorResponse.getType(), equalTo("error"));
        assertThat(errorResponse.getMessage(), equalTo("Pet not found"));
    }
}