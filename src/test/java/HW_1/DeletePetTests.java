package HW_1;

import HW_1.dto.CategoryDto;
import HW_1.dto.DeletedPetDto;
import HW_1.dto.PetDto;
import HW_1.dto.TagDto;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DeletePetTests {

    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void verifyPetCanBeDeleted() {
        int petDtoId = 81;
        String statusValue = "sold";
        String categoryName = "Taksa";
        String petName = "Taksa name";

        CategoryDto categoryDtoToCreate = new CategoryDto();
        categoryDtoToCreate.setId(3);
        categoryDtoToCreate.setName(categoryName);

        List<String> photoUrls = new ArrayList<>(List.of("firstUrls", "secondUrl", "thirdUrl"));

        TagDto tagDtoToCreate = new TagDto();
        tagDtoToCreate.setId(7);
        tagDtoToCreate.setName("someTag");
        List<TagDto> tagDtoList = new ArrayList<>(List.of(tagDtoToCreate));

        PetDto petDtoToCreate = new PetDto();
        petDtoToCreate.setId(petDtoId);
        petDtoToCreate.setCategory(categoryDtoToCreate);
        petDtoToCreate.setName(petName);
        petDtoToCreate.setPhotoUrls(photoUrls);
        petDtoToCreate.setTags(tagDtoList);
        petDtoToCreate.setStatus(statusValue);

        given().header("Content-Type", "application/json").body(petDtoToCreate).post(BASE_URL);

        Response previouslyCreatedPetResponse = given().get(BASE_URL + petDtoId);

        System.out.println(previouslyCreatedPetResponse.asPrettyString());

        PetDto previouslyCreatedPetDto = previouslyCreatedPetResponse.as(PetDto.class);

        Assert.assertEquals(petDtoId, previouslyCreatedPetDto.getId());

        Response deletedPetResponse = given().delete(BASE_URL + petDtoId);
        DeletedPetDto deletedPetDto = deletedPetResponse.as(DeletedPetDto.class);
        System.out.println(deletedPetResponse.asPrettyString());

        Assert.assertEquals("unknown", deletedPetDto.getType());
        Assert.assertEquals(String.valueOf(petDtoId), deletedPetDto.getMessage());
    }

}
