package hw_1;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        // Filters for logging requests and responses (very useful for debugging)
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}