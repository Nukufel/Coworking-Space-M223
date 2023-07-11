package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

        @Test
    public void testDelete() {

        Entry requestBody = new Entry(); // replace with your request body
            requestBody.setCheckIn(LocalDateTime.now());
            requestBody.setCheckOut(LocalDateTime.now());
        // Send a POST request to the endpoint you want to test
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/entries"); // replace with your actual endpoint

        given()
          .when().delete("/entries/1")
          .then()
             .statusCode(204);
            
    }

    @Test
    public void testCreateEndpoint() {
        // Define the request body for creating a new item
        Entry requestBody = new Entry(); // replace with your request body
            requestBody.setCheckIn(LocalDateTime.now());
            requestBody.setCheckOut(LocalDateTime.now());
        // Send a POST request to the endpoint you want to test
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/entries") // replace with your actual endpoint

            .then()
            .statusCode(200);  // replace with the expected status code

    }

    @Test
    public void testUpdate(){
                // Define the request body for creating a new item
        Entry requestBody = new Entry(); // replace with your request body
            requestBody.setCheckIn(LocalDateTime.now());
            requestBody.setCheckOut(LocalDateTime.now());
        // Send a POST request to the endpoint you want to test
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .put("/entries") // replace with your actual endpoint

            .then()
            .statusCode(204);  // replace with the expected status code

    }



}