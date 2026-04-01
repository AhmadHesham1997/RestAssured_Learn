package Library;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Commons.Utils.rawToJson;
import static Library.LibraryPayload.addBook;
import static io.restassured.RestAssured.*;

public class AddBook {
    @Test(dataProvider = "BooksData")
    //After we pass the data provider name we pass the 2 parameters of our data like the title of our columns then pass these parameters to the add Book method
    public void addBookRequest(String bookName, String aisle) {
        RestAssured.baseURI ="https://rahulshettyacademy.com/";

       String response = given().log().all()
                .header("Content-Type","application/json")
                .body(addBook(bookName, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .log().body().extract().response().asString();

        JsonPath js = rawToJson(response);
        String bookId = js.get("ID");
        System.out.println("Book ID: " + bookId);
    }

    //To enter bookID multiple times we create DataProvider if we give it a name we pass it to the @Test if not we pass the method name to the @Test
    @DataProvider(name = "BooksData")
    public Object[][] getBooksData() {
        return new Object[][]{
                {"RestAssured Based on Python", "1998"},
                {"Selenium WebDriver with Java", "1999"},
                {"TestNG Framework", "2000"},
                {"Appium with Java", "2001"},
                {"Node JS", "2002"},
        };
    }
}
