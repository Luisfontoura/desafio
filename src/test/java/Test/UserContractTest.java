package Test;
//import org.apache.http.HttpStatus;
import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.notNullValue;
//import static org.hamcrest.Matchers.*;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import BaseUrl.ApiEndpoints;
import Environment.Client;
import Users.Users;
import io.restassured.RestAssured;
//import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserContractTest {

        @Before
    public void setUp() {
        // Configura a URI base para os endpoints da API
        RestAssured.baseURI = ApiEndpoints.BASE_URI;
    }

    @Test
    public void contractRegisterUsersTest(){

    
            String email = Client.getEmail();
            String password = Client.getPassword();
    
            Users user = new Users(email, password);
        given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post(ApiEndpoints.REGISTER_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/UserRegisterSchema.json"));
    }
    @Test
    public void contractLoginUsersTest(){
    
            String email = Client.getEmail();
            String password = Client.getPassword();
    
            Users user = new Users(email, password);

        given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post(ApiEndpoints.LOGIN_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/UserLoginSchema.json"));
    }

        }