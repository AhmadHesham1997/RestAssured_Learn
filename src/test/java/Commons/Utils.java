package Commons;

import io.restassured.path.json.JsonPath;

// this class is used to add json path method as common since it will be used frequently
public class Utils {
    // This method takes a raw JSON response and converts it into a Jsonpath object for easy querying
    public static JsonPath rawToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
