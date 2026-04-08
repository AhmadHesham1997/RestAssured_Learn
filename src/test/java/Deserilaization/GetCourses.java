
package Deserilaization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Commons.Utils.rawToJson;
import static io.restassured.RestAssured.given;

public class GetCourses {
    String accessToken;
    @BeforeMethod
    public void beforeMethod() {
        RestAssured.baseURI = "http://localhost:3000/";

    }

    @Test
    //To get courses i need to generate a token
    public void generateToken() {
        //here we work with form params instead of body according to documentation
        String response = given().formParams("client_id", "test_client_id",
                        "client_secret", "test_client_secret",
                        "grant_type", "client_credentials",
                        "scope", "read")
                .when().post("oauth/token")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = rawToJson(response);
        accessToken = js.getString("access_token");

        System.out.println("Access Token: " + accessToken);
    }

    @Test
    public void getCourses() {
        given().queryParam("access_token", accessToken) // We get the access token from the previous method using extraction 
                .when().get("getCourseDetails")
                .then().assertThat().statusCode(200)
                .log().all();
    }
}
