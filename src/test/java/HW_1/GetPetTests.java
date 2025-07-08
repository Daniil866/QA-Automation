package HW_1;

import HW_1.dto.PetDto;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPetTests {

    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void verifyPetCanBeObtained() {
        int petId = 81;

        Response response = given().get(BASE_URL + petId);

        System.out.println(response.asPrettyString());
        System.out.println(response.statusCode());

        PetDto petDto = response.as(PetDto.class);

        System.out.println(petDto.getId());
        System.out.println(petDto.getName());
        System.out.println(petDto.getStatus());
        System.out.println(petDto.getTags());
        System.out.println(petDto.getCategory());

        Assert.assertEquals(petId, petDto.getId());
    }

}
