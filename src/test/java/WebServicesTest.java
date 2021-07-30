import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServicesTest {
    String baseURI = "https://jsonplaceholder.typicode.com/";
    String basePath = "users";


    @Test
    public void getStatusCode() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI + basePath)
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User is not found");
    }

    @Test
    public void getContentType() {
        Response response = RestAssured
                .given()
                .when()
                .get(baseURI + basePath)
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
                .get(baseURI + basePath)
                .then()
                .assertThat()
                .body("size()", Is.is(10));
    }
}