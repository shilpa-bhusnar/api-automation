package getApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ReqresGetApiTest {

   @Test(description = "Verify get api")
   public void verifyGetApi() {
      RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

      Response response = RestAssured.given()
              .contentType(ContentType.JSON)
              .when()
              .get("/posts")
              .then()
              .statusCode(200)
              .extract()
              .response();

      // ✅ FIX: Get actual response body as String
      JsonPath jsonpath = new JsonPath(response.asString());

      String expectedTitle = "qui est esse";

      // ✅ Correct JsonPath extraction
      String actualTitle = jsonpath.getString("find { it.id == 2 }.title");
      System.out.println("Title with id=2: " + actualTitle);

      // ✅ AssertJ assertion
      Assertions.assertThat(actualTitle)
              .as("Verify title for id=2")
              .isEqualTo(expectedTitle);
   }
}
