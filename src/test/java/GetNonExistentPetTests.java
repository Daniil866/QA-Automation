package tests;

import dtos.ApiResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetNonExistentPetTests extends BaseTest {

    @Test
    @DisplayName("Verify getting a non-existent pet returns expected error")
    public void verifyNonExistentPetCannotBeRetrieved() {
        long nonExistentPetId = 99999999999999L;

        ApiResponse errorResponse = given()
                .pathParam("petId", nonExistentPetId)
                .when()
                .get("/pet/{petId}")
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