package entitys;

public class infoDay {
    private int count;
    private double sum;
    private String date;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public infoDay(int count, double sum, String date) {
        this.count = count;
        this.sum = sum;
        this.date = date;
    }
}
