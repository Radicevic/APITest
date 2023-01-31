package BookerAndBookStore;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import BookerAndBookStore.pojo.authorization;
import BookerAndBookStore.pojo.body;
import BookerAndBookStore.pojo.token;

import static io.restassured.RestAssured.given;

public class BookstoreAPIPojo {
    @Test
    public void CreateUser() {
        body createUserBody = new body();
        createUserBody.setUserName("Random12dsdsd");
        createUserBody.setPassword("Qwersty123!@#");
        RestAssured.baseURI = "https://demoqa.com";
        given().log().all()
                .header("Content-Type", "application/json")
                .body(createUserBody)
                .log().all()
                .post("Account/v1/User")
                .then().log().all()
                .assertThat().statusCode(201);
    }

    @Test
    public void GetUserID (){
        String username = "Random12345+-*";
        String password = "Qwerty123!@#";
        body createUserBody = new body();
        createUserBody.setUserName(username);
        createUserBody.setPassword(password);
        RestAssured.baseURI = "https://demoqa.com";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(createUserBody)
                .log().all()
                .post("Account/v1/User")
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String userID = js.getString("userID");
    }

    @Test
    public void CreateToken (){
        String username = "Randommm123+-*";
        String password = "Qwerty123!@#";

        body createUserBody = new body();
        createUserBody.setUserName(username);
        createUserBody.setPassword(password);
        RestAssured.baseURI = "https://demoqa.com";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(createUserBody)
                .log().all()
                .post("Account/v1/User")
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String userID = js.getString("userID");

        token createToken = new token();
        createToken.setUserName(username);
        createToken.setPassword(password);

        String response1 = given().log().all()
                .header("Content-Type", "application/json")
                .body(createToken)
                .log().all()
                .post("Account/v1/GenerateToken")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(response1);
        String token = js1.getString("token");
        System.out.println("TOKEEEEN" + token);

        authorization getAuthorization = new authorization();
        getAuthorization.setUserName(username);
        getAuthorization.setPassword(password);

        String response2 = given().log().all()
                .header("Content-Type", "application/json")
                .body(getAuthorization)
                .log().all()
                .post("Account/v1/Authorized")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        String response3 = given().log().all()
                .header("Authorization", "Bearer "+token)
                .when().delete("Account/v1/User/"+userID)
                .then().log().all()
                .assertThat().statusCode(204)
                .extract().response().asString();

    }
}