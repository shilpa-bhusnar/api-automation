package putApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.File;

public class PutApiTest {
    @Test
    public void verifyPutApi(){
        RestAssured.baseURI="https://api.restful-api.dev";

        File payload=new File("src/test/resources/PutPayload.json");

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/objects/ff8081819782e69e0198fc4ed55d764d")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonpath = new JsonPath(response.asString());

        String actualName=jsonpath.getString("name");
        String expectedName="Apple MacBook Pro 17";

        Assertions.assertThat(actualName)
                .as("verify name")
                .isEqualTo(expectedName);

        Assertions.assertThat(actualName)
                .as("verify name")
                .isEqualTo(expectedName);

        Assertions.assertThat(actualName)
                .as("verify name")
                .isEqualTo(expectedName);

        Assertions.assertThat(actualName)
                .as("verify name")
                .isEqualTo(expectedName);

    }
}
