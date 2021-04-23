package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import petshop.models.Category;
import petshop.models.Pet;

public class PUT200 {

    @Test
    public void test() {
        int id = 560012;
        Pet myPet = new Pet();
        myPet.setId(id);
        myPet.setName("superDog");
        Category category = new Category();
        category.setName("Dog");
        myPet.setCategory(category);
        myPet.setStatus("available");

        String baseUrl = "https://petstore.swagger.io/v2";
        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post(baseUrl + "/pet")
                .then()
                .statusCode(200);


        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(baseUrl + "/pet/" + id)
                .then()
                .statusCode(200).extract().as(Pet.class);

        Pet newPet = new Pet();
        newPet.setId(id);
        newPet.setName("Oleg");
        Category newCategory = new Category();
        newCategory.setName("Cat");
        newPet.setCategory(newCategory);
        newPet.setStatus("unavailable");

        Pet restAssuredPet = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(newPet)
                .put(baseUrl + "/pet")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPet, newPet);

        restAssuredPet = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(baseUrl + "/pet/" + id)
                .then()
                .statusCode(200)
                .extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPet, newPet);
    }
}
