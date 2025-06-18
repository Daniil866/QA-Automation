package org.example;

import io.restassured.http.ContentType;
import org.example.dto.ErrorResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteNonExistingPetTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";

    @Test
    public void verifyNonExistingPetCannotBeDeleted() {
        int invalidPetId = 999999999;

        ErrorResponse response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(BASE_URL + "/" + invalidPetId)
                .then()
                .statusCode(404)
                .extract()
                .as(ErrorResponse.class);

        assertEquals(response.getCode(), 1);
        assertEquals(response.getType(), "error");
        assertEquals(response.getMessage(), "Pet not found");
    }
}
