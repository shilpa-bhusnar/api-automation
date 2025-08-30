package postApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.File;

public class PostApiTest1 {

    @Test
    public void verifyPostApi() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        File payload =new File("src/test/resources/PostPayload.json");

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/objects")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonpath = new JsonPath(response.asString());
        String expectedName="Apple MacBook Pro 16";

        String actualName=jsonpath.getString("name");

        Assertions.assertThat(actualName)
                .as("verify name")
                .isEqualTo(expectedName);
    }
}
