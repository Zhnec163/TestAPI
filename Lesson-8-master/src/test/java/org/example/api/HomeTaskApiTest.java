package org.example.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.model.Store;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class HomeTaskApiTest {

    Store store = new Store();

    @BeforeClass
    public void prepare() throws IOException {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", "1234")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.filters(new ResponseLoggingFilter());
        store.setId(4);
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
        given()
                .body(store)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);

        given()
                .pathParam("orderId", store.getId())
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200);
    }
    @Test
    public void testDelete() {
        given()
                .body(store)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);

        given()
                .pathParam("orderId", store.getId())
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);

        given()
                .pathParam("orderId", store.getId())
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404);
    }
}
