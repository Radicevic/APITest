package BookerPojoAPI;


import BookerPojoAPI.pojo.Booking;
import BookerPojoAPI.pojo.Bookingdates;
import io.restassured.RestAssured;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class BookerPojoAPI {

    @Test
    public void createBooking() {
        Booking bookingBody = new Booking();
        bookingBody.setFirstname("QA");
        bookingBody.setLastname("Automation");
        bookingBody.setTotalprice(100);
        bookingBody.setDepositpaid(true);

        Bookingdates dates = new Bookingdates();
        dates.setCheckin("2022-01-01");
        dates.setCheckout("2022-01-02");
        bookingBody.setBookingdates(dates);
        bookingBody.setAdditionalneeds("Dinner");

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all()
                .header("Content-Type", "application/json")
                .body(bookingBody)
                .when().post("/booking")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void createBookingWithDefaultValues() {
        Booking bookingBody = new Booking();
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given().log().all()
                .header("Content-Type", "application/json")
                .body(bookingBody)
                .when().post("/booking")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
