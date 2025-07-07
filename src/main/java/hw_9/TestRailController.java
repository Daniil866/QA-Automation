package hw_9;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class TestRailController {

    private static final String RUN_ID = "1";
    private final static String BASE_API_URL = "https://danschoolveselova.testrail.io/index.php?/api/v2/";
    private static final String USER_EMAIL = "Ganna.veselova@gmail.com";
    private static final String USER_PASSWORD = "annAVes123!@#$";
    private static final String CREDENTIALS = Base64.getEncoder().encodeToString((USER_EMAIL + ":" + USER_PASSWORD).getBytes());

    public static void publishData(int statusId, int testCaseId) {
        given().header("Authorization", "Basic " + CREDENTIALS)
                .contentType("Application/json")
                .body(new TestRailStatusDto(statusId))
                .post(BASE_API_URL + format("add_result_for_case/%s/%s", RUN_ID, testCaseId));
    }

}
