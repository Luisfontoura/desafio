package Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import BaseUrl.ApiEndpoints;
import Environment.Client;
import Users.Users;

public class UserApiTest {

    @Before
    public void setUp() {
        // Configura a URI base para os endpoints da API
        RestAssured.baseURI = ApiEndpoints.BASE_URI;
    }

        @Test
        public void testRegisterUser() {
    
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