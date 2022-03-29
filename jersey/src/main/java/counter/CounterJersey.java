package counter;

public class CounterJersey {
    private String date;
    private int value;
    public static int count;

    public CounterJersey(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
