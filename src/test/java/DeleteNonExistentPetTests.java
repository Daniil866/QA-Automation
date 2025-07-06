package tests;

import dtos.ApiResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteNonExistentPetTests extends BaseTest {

    @Test
    @DisplayName("Verify deleting a non-existent pet returns expected error")
    public void verifyNonExistentPetCannotBeDeleted() {
        long nonExistentPetId = 88888888888888L;

        ApiResponse errorResponse = given()
                .pathParam("petId", nonExistentPetId)
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .extract()
                .as(ApiResponse.class);

        assertEquals(1, errorResponse.getCode());
        assertEquals("error", errorResponse.getType());
        assertTrue(errorResponse.getMessage().contains("Pet not found"));
    }
}