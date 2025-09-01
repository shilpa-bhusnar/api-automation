package deleteApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class DeleteApiTest {
    @Test
    public static void verifyDeleteApi() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        int id = 1;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParams("id", id)
                .when()
                .delete("/objects/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = new JsonPath(response.asString());

        String expectedMessage = "Object with id = 6, has been deleted.";
        String actualMessage = jsonPath.getString("message");

        Assertions.assertThat(actualMessage)
                .as("verify delete message ")
                .isEqualTo(expectedMessage);
    }
}
