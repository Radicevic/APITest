package BookerAndBookStore.files;

public class payload {
    public static String createBookingBody (){
        return "{\n" +
                "    \"firstname\" : \"Dragan\",\n" +
                "    \"lastname\" : \"Radicevic\",\n" +
                "    \"totalprice\" : 99,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";
    }
}
