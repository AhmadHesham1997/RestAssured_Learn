package Courses;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static Commons.Utils.rawToJson;
import static Payloads.CoursesPayload.getCourseDetails;
import static org.testng.Assert.assertEquals;

@Test
public class ComplexJson {
    // This class demonstrates how to parse a complex JSON response using Rest Assured's JsonPath.
    // It retrieves course details from a hardcoded JSON string and allows us to query it easily.
    JsonPath js = rawToJson(getCourseDetails());

    // print number of courses
    public void printNumberOfCourses() {
        // we use course.size then courses are in an array so it helps me count how objects in the array
        int courseCount = js.getInt("courses.size()");
        System.out.println("Number of courses: " + courseCount);
        assertEquals(courseCount, 3, "courses count mismatch"); // We assert here that number of courses are correct which are 3
    }

    //print purchase amount
    public void printPurchaseAmount() {
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount: " + purchaseAmount);
        assertEquals(purchaseAmount, 910, "purchase amount mismatch"); // We assert here that purchase amount is correct which is 910
    }

    //print title of first course
    public void printTitleOfFirstCourse() {
        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println("Title of first course: " + firstCourseTitle);
        assertEquals(firstCourseTitle, "Selenium Python", "first course title mismatch"); // assertion
    }

    //Print All course titles and their respective Prices
    public void printAllCourseTitlesAndPrices() {
        int courseCount = js.getInt("courses.size()");
        for (int i = 0; i < courseCount; i++) {
            // we injected the i because its an integer number from the for loop not a string
            String title = js.getString("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            System.out.println("Course Title: " + title + ", Price: " + price);
        }
        //assert that all course titles are correct and there respective prices are correct

    }

    //Print no of copies sold by RPA Course
    public void printCopiesSoldByRPA() {
//        int copies = js.getInt("courses[2].copies");
//        System.out.println("Course Copies: " + copies);
//        assertEquals(copies, 10, "copies mismatch");

        // another way in case i dont know whats the index of the course so i need to loop using for
        int courseCount = js.getInt("courses.size()");
        for (int i = 0; i < courseCount; i++) {
            String title = js.getString("courses[" + i + "].title");
            if (title.equalsIgnoreCase("RPA")) {
                int copiesRPA = js.getInt("courses[" + i + "].copies");
                System.out.println("Course Copies: " + copiesRPA);
                assertEquals(copiesRPA, 10, "copies mismatch");
                break; // we break here because we found the course we are looking for so no need to continue looping
            }
        }
    }

    //. Verify if Sum of all Course prices matches with Purchase Amount
    public void verifySumOfAllCoursePricesMatchesPurchaseAmount() {
        int courseCount = js.getInt("courses.size()");
        int totalAmount = 0;
        for (int i = 0; i < courseCount; i++) {
            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            totalAmount += price * copies;
        }
        System.out.println("Total Amount: " + totalAmount);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        assertEquals(totalAmount, purchaseAmount, "Total amount mismatch");
    }
}