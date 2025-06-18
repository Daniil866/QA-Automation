package org.example;

import io.restassured.http.ContentType;
import org.example.dto.Category;
import org.example.dto.Pet;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutPetTests {

    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";

    @Test
    public void verifyPetCanBeModified() {
        // Create new pet (POST)
        Pet pet = new Pet();
        pet.setId(987654321);
        pet.setName("Sharik");
        pet.setStatus("available");
        pet.setCategory(new Category(1, "dog"));

        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(200);

        // Check created (GET)
        given()
                .get(BASE_URL + "/" + pet.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Sharik"))
                .body("status", equalTo("available"))
                .body("category.name", equalTo("dog"));

        // Modify pet
        pet.setName("Barsik");
        pet.setStatus("sold");
        pet.getCategory().setName("cat");

        // Send PUT request
        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put(BASE_URL)
                .then()
                .statusCode(200)
                .body("name", equalTo("Barsik"))
                .body("status", equalTo("sold"))
                .body("category.name", equalTo("cat"));

        // Verify changes (GET)
        given()
                .get(BASE_URL + "/" + pet.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Barsik"))
                .body("status", equalTo("sold"))
                .body("category.name", equalTo("cat"));
    }
}
