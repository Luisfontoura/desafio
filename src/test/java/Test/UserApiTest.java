package Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import BaseUrl.ApiEndpoints;
import Environment.Client;
import Users.Users;

public class UserApiTest {

        @Test
        public void testRegisterUser() {
            RestAssured.baseURI = ApiEndpoints.BASE_URI;
    
            String email = Client.getEmail();
            String password = Client.getPassword();
    
            Users user = new Users(email, password);
    
            given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post(ApiEndpoints.REGISTER_ENDPOINT)
            .then()
                .statusCode(200)
                .body("token", notNullValue());
        }
    
@Test

public void testUserLogin() {
    RestAssured.baseURI = ApiEndpoints.BASE_URI;

    String email = Client.getEmail();
    String password = Client.getPassword();

    Users user = new Users(email, password);

    given()
        .contentType(ContentType.JSON)
        .body(user)
    .when()
        .post(ApiEndpoints.LOGIN_ENDPOINT)
    .then()
        .statusCode(200)
        .body("token", notNullValue());
}

@Test
public void testInvalidUserRegistration() {
    RestAssured.baseURI = ApiEndpoints.BASE_URI;

    String email = Client.getEmail();

    given()
        .contentType(ContentType.JSON)
        .body("{ \"email\": \"" + email + "\"}")
    .when()
        .post("/register")
    .then()
        .statusCode(400);
}

@Test
public void testEmptyPasswordLogin() {
    RestAssured.baseURI = ApiEndpoints.BASE_URI;

    String email = Client.getEmail();

    given()
        .contentType(ContentType.JSON)
        .body("{ \"email\": \"" + email + "\"}")
    .when()
        .post("/login")
    .then()
        .statusCode(400)
        .body("error", equalTo("Missing password"));
}

@Test
public void testEmptyFIeldsLogin() {
    RestAssured.baseURI = ApiEndpoints.BASE_URI;

    given()
        .contentType(ContentType.JSON)
        .body("")
    .when()
        .post("/login")
    .then()
        .statusCode(400)
        .body("error", equalTo("Missing email or username"));
}
@Test
public void testEmptyFIeldsRegister() {
    RestAssured.baseURI = ApiEndpoints.BASE_URI;

    given()
        .contentType(ContentType.JSON)
        .body("")
    .when()
        .post("/register")
    .then()
        .statusCode(400)
        .body("error", equalTo("Missing email or username"));
}
}