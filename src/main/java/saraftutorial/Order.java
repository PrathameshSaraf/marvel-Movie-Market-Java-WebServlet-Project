package saraftutorial;

public class Order extends Product{
    private int orderId;
    private int uId;
    private int resolution;
    private String Date;

    public Order() {
    }

    public Order(int orderId, int uId, int resolution, String date) {
        this.orderId = orderId;
        this.uId = uId;
        this.resolution = resolution;
        Date = date;
    }

    public Order(int uId, int resolution, String date) {
        this.uId = uId;
        this.resolution = resolution;
        Date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", uId=" + uId +
                ", resolution=" + resolution +
                ", Date='" + Date + '\'' +
                '}';
    }
}
