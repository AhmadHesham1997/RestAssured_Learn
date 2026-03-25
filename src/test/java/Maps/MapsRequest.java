package Maps;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class MapsRequest {

    @Test
    public void testMapApi(){
        //Add place using post method request
        RestAssured.baseURI = "https://rahulshettyacademy.com/"; //We take the baseuri common alone so we dont have to repeat it in every when method and be used in all of methods
        //Rest Assured works in BDD technique which is given,when,then format
        given().queryParam("key", "qaclick123")// we get the key and vlaue of our url which is our query params
                .header("Content-Type", "application/json")// we get the header and value of our url which is our header
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\"shoe park\", \"shop\"],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")// we get the body of our url which is our payload
                .when().post("maps/api/place/add/json")// we get the method of our url which is post this where we take an action, we send the end point only
                .then().assertThat().statusCode(200)// we get the status code of our url which is 200 and we assert it for example
                .log().all();// we log all the response of our url
    }
}
