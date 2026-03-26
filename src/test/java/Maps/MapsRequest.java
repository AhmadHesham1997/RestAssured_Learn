package Maps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static Payloads.MapsPayload.addPlaceRequestBody;
import static Payloads.MapsPayload.updatePlaceRequestBody;
import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class MapsRequest {
    String newAddress = "10th Street of bartenders"; // We define the new address to be updatedString
    //We define the variable outside the method to be used in both methods, and we can change it easily if we want to update the place with another address
    @Test
    public void testMapApi(){
        //Add place using post method request
        RestAssured.baseURI = "https://rahulshettyacademy.com/"; //We take the baseuri common alone so we don't have to repeat it in every when method and be used in all of methods
        //Rest Assured works in BDD technique which is given,when,then format
        String addPlaceResponse =  given().queryParam("key", "qaclick123")// we get the key and value of our url which is our query params
                .header("Content-Type", "application/json")// we get the header and value of our url which is our header
                .body(addPlaceRequestBody())// we get the body of our url which is our payload
                .when().post("maps/api/place/add/json")// we get the method of our url which is post this where we take an action, we send the end point only
                .then().assertThat().statusCode(200)// we get the status code of our url which is 200, and we assert it for example
                .assertThat().body("scope", equalTo("APP")) // We assert that key scope in body has a value called APP
                //.log().all();// we log all the response of our url
                //.log().body() //we log the body only to see it
                .extract().response().asString();  // Since we need to assert on a specific value in the response we need to extract the response and convert it from json
                // into string and store it in a variable
                System.out.println(addPlaceResponse);
                //Extract placeID from response using JsonPath class which help me get a value from response
               JsonPath js = new JsonPath(addPlaceResponse); // we create an object of json path and pass the response in it
                String placeId = js.getString("place_id"); // we use the getString method to get the value of place_id and store it in a variable
                System.out.println("PlaceID: " + placeId);

                //Update place using PUT request
                given().queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(updatePlaceRequestBody(placeId, newAddress)) // We use method with parameter to pass the placeId from the variable created in line32 and newAddress in the body of the request
                        .when().put("maps/api/place/update/json")
                        .then().assertThat().statusCode(200)
                        .assertThat().body("msg", equalTo("Address successfully updated")) // We assert on msg in the response body
                        .log().all(); // So I can see the response i don't need to extract a certain key because the response body already has only 1 key

                // Get place using GET request to validate that the address is updated
                String getPlaceResponse = given().queryParam("key", "qaclick123")
                        .queryParam("place_id", placeId) // we pass the placeId in the query param to get the place details
                        .header("Content-Type", "application/json")
                        .when().get("maps/api/place/get/json")
                        .then().assertThat().statusCode(200)
                        .assertThat().body("address", equalTo("10th Street of bartenders")) //Simple type of assertion to confirm that address is changed
                        .extract().response().asString(); // we extract the response and convert it to string to be able to assert on a specific value in it
                System.out.println(getPlaceResponse);
                JsonPath js1 = new JsonPath(getPlaceResponse); // we create an object of json path and pass the response in it
                String actualAddress = js1.getString("address"); // we use the getString method to get the value of address and store it in a variable
                System.out.println("Actual Address: " + actualAddress);
                //Another way of assertion: Assert that the actual address is equal to the new address we updated using hamcrest which is built in tool with restassured
                if(actualAddress.equals(newAddress)){
                    System.out.println("Address updated successfully");
                }else {System.out.println("Address update failed");
                }

                //Another way of assertion using TestNG assertEquals
               assertEquals(actualAddress, newAddress, "Address update failed"); // we assert that the actual address is equal to the new address and we can add a message if the assertion failed

    }
}
