package Maps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static Payloads.MapsPayload.addPlaceRequestBody;
import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class MapsRequest {

    @Test
    public void testMapApi(){
        //Add place using post method request
        RestAssured.baseURI = "https://rahulshettyacademy.com/"; //We take the baseuri common alone so we dont have to repeat it in every when method and be used in all of methods
        //Rest Assured works in BDD technique which is given,when,then format
        String addPlaceResponse =  given().queryParam("key", "qaclick123")// we get the key and vlaue of our url which is our query params
                .header("Content-Type", "application/json")// we get the header and value of our url which is our header
                .body(addPlaceRequestBody())// we get the body of our url which is our payload
                .when().post("maps/api/place/add/json")// we get the method of our url which is post this where we take an action, we send the end point only
                .then().assertThat().statusCode(200)// we get the status code of our url which is 200 and we assert it for example
                .assertThat().body("scope", equalTo("APP")) // We assert that key scope in body has a value called APP
                //.log().all();// we log all the response of our url
                //.log().body() //we log the body only to see it
                .extract().response().asString();  // Since we need to assert on a specific value in the response we need to extract the response and convert it from json
                // into string and store it in a variable
                System.out.println(addPlaceResponse);
                //Extract placeID from response using JsonPath class which help me get a value from response
               JsonPath js = new JsonPath(addPlaceResponse); // we create an object of json path and pass the response in it
                String placeID = js.getString("place_id"); // we use the getString method to get the value of place_id and store it in a variable
                System.out.println("PlaceID: " + placeID);
    }
}
