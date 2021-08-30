import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebServicesTest {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        RestAssured.basePath = "users";
    }

    @Test
    public void getStatusCode() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User is not found");
    }

    @Test
    public void getContentType() {
        Response response = RestAssured
                .given()
                .when()
                .get()
                .andReturn();
        String contentTypeHeader = response.getHeader("Content-Type");
        String expectedContentTypeHeader = "application/json; charset=utf-8";
        Assert.assertEquals(contentTypeHeader, expectedContentTypeHeader,
                "Content type is not " + expectedContentTypeHeader);
    }

    @Test
    public void getBody() {
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .get()
                .then()
                .assertThat()
                .body("size()", Is.is(10));
    }
}