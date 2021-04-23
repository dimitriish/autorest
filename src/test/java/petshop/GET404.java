package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class GET404 {

    @Test
    public void test() {
        int id = -1;
        String baseUrl = "https://petstore.swagger.io/v2";
        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(baseUrl + "/pet/" + id)
                .then()
                .statusCode(404);
    }
}
