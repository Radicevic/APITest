package BookerAndBookStore;

import BookerAndBookStore.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static BookerAndBookStore.files.payload.createBookingBody;
import static io.restassured.RestAssured.given;

public class BookerAPI {

    @Test
    public void PingCheckTest() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        //given().when().get("/ping");
        //given().log().all().when().get("/ping");
        given().log().all()
                .when().get("/ping")
                .then().log().all()
                .assertThat().statusCode(201);

    }

    @Test
    public void GetAllBookings() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all()
                .when().get("/booking")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void CreateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Dragoljub\",\n" +
                        "    \"lastname\" : \"Boranijasevic\",\n" +
                        "    \"totalprice\" : 99,\n" +
                        "    \"depositpaid\" : false,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2022-01-01\",\n" +
                        "        \"checkout\" : \"2023-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Dinner\"\n" +
                        "}")
                .when().post("/booking")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void CreateBookingWithPayload() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all()
                .header("Content-Type", "application/json")
                .body(createBookingBody())
                .when().post("/booking")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void CreateBookingJson() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.createBookingBody())
                .when().post("/booking")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String firstname = js.getString("booking.firstname");
        String lastname = js.getString("booking.lastname");
        int totalprice = js.getInt("booking.totalprice");

        Assert.assertEquals(firstname, "Dragoljub");
        Assert.assertEquals(lastname, "Boranijasevic");
        Assert.assertEquals(totalprice, 99);

    }

    @Test
    public void GetBookingID() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.createBookingBody())
                .when().post("/booking")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("Response : " + response);

        JsonPath js = new JsonPath(response);
        String bookingid = js.getString("bookingid");

        String response1 = given().log().all()
                .when().get("/booking/"+bookingid)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("Response1 : " + response1);

        JsonPath js1 = new JsonPath(response1);
        String firstname = js1.getString("firstname");
        String lastname = js1.getString("lastname");
        int totalprice = js1.getInt("totalprice");

        Assert.assertEquals(firstname, "Dragan");
        Assert.assertEquals(lastname, "Radicevic");
        Assert.assertEquals(totalprice, 99);
    }

}