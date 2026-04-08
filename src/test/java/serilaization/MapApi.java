package serilaization;

import io.restassured.RestAssured;

import static Payloads.MapsPayload.addPlaceRequestBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class MapApi {

    public void testMapApi() {
        //Add place using post method request
        RestAssured.baseURI = "https://rahulshettyacademy.com/"; //We take the baseuri common alone so we don't have to repeat it in every when method and be used in all of methods
        //Rest Assured works in BDD technique which is given,when,then format
        String addPlaceResponse = given().queryParam("key", "qaclick123")// we get the key and value of our url which is our query params
                .header("Content-Type", "application/json")// we get the header and value of our url which is our header
                .body()// we get the body of our url which is our payload
                .when().post("maps/api/place/add/json")// we get the method of our url which is post this where we take an action, we send the end point only
                .then().assertThat().statusCode(200)// we get the status code of our url which is 200, and we assert it for example
                .assertThat().body("scope", equalTo("APP")) // We assert that key scope in body has a value called APP
                //.log().all();// we log all the response of our url
                //.log().body() //we log the body only to see it
                .extract().response().asString();  // Since we need to assert on a specific value in the response we need to extract the response and convert it from json
    }
}
