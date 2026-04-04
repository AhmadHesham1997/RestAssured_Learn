package Deserilaization;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCourses {
    @BeforeMethod
    public void beforeMethod() {
        RestAssured .baseURI = "https://rahulshettyacademy.com/";

    }

    @Test
    //To get courses i need to generate a token
    public void generateToken()
    {
        //here we work with form params instead of body according to documentation
        given().formParams("client_id","rahulshettyacademy")
                .formParams("client_secret","rahulshettyacademy")
                .formParams("grant_type","client_credentials")
                .when().post("oauth2/token")
                .then().log().all();
    }

}
