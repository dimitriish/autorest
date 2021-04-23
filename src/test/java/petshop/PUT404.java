package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import petshop.models.Category;
import petshop.models.Pet;

public class PUT404 {


    @Test
    public void test() {
        int id = 560012;

        String baseUrl = "https://petstore.swagger.io/v2";

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(baseUrl + "/pet/" + id);

        Pet newPet = new Pet();
        newPet.setId(id);
        newPet.setName("Oleg");
        Category newCategory = new Category();
        newCategory.setName("Cat");
        newPet.setCategory(newCategory);
        newPet.setStatus("unavailable");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(newPet)
                .put(baseUrl + "/pet")
                .then()
                .statusCode(404);

    }
}
