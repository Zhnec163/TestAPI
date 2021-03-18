package org.example.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.model.Store;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HomeTaskApiTest {

    Store store = new Store();

    @BeforeClass
    public void prepare(){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", "1234")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.filters(new ResponseLoggingFilter());
    }

    @Test
    public void testOrderCreation() {
        given()
                .body(store)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);
    }

    @Test
    public void testOrderSearch() {
        Store actual =
        given()
                .body(store)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract().body()
                .as(Store.class);

        given()
                .pathParam("orderId", actual.getId())
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200);
    }
    @Test
    public void testDelete() {
        Store actual =
        given()
                .body(store)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract().body()
                .as(Store.class);

        given()
                .pathParam("orderId", actual.getId())
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);

        given()
                .pathParam("orderId", actual.getId())
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }
}
