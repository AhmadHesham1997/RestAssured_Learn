package Library;

public class LibraryPayload {
    public static String addBook(String courseName, String aisle) {
        return
                "{\n" +
                        "  \"name\": \"" + courseName + "\",\n" +
                        "  \"isbn\": \"route\",\n" +
                        "  \"aisle\": \"" + aisle + "\",\n" +
                        "  \"author\": \"John foe\"\n" +
                        "}";
    }
}
