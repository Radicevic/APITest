package BookerPojoAPI.pojo;

public class Bookingdates {
    private String checkin;
    private String checkout;

    public String getCheckin() {
        if (checkin == null) return "2022-01-01";
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        if (checkout == null) return "2022-01-02";
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}