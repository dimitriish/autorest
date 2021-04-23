package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import petshop.models.Category;
import petshop.models.Pet;

public class POST200 {

    @Test
    public void test() {
        Pet myPet = new Pet();
        myPet.setId(560012);
        myPet.setName("superDog");
        Category category = new Category();
        category.setName("Dog");
        myPet.setCategory(category);
        myPet.setStatus("available");

        String baseUrl = "https://petstore.swagger.io/v2";
        Pet restAssuredPet = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post(baseUrl + "/pet")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);

        Assertions.assertEquals(myPet, restAssuredPet);

        restAssuredPet = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(baseUrl + "/pet/560012")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);

        Assertions.assertEquals(myPet, restAssuredPet);
    }
}
