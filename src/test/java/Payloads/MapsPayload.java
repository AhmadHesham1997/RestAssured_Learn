package Payloads;
//This class is used to store Body plyload of api and send it to the main class thru class object instead of hard coding the body
public class MapsPayload {
    public static String addPlaceRequestBody(){
        return "{\n" +
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
                "}";
    }
    // We created this method to get the extracted value from the previous POST request and pass it in the body of the PUT request to update the place, we use method with parameters to pass the value
    public static String updatePlaceRequestBody(String placeId, String newAddress){
        return "{\n" +
                "\"place_id\":\""+placeId+"\",\n" + // we concatenate the placeID in the body of the request
                "\"address\":\""+newAddress+"\",\n" + // we concatenate the new address in the body of the request
                "\"key\":\"qaclick123\"\n" +
                "}";
    }
}
