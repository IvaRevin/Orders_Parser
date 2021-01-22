package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

public class Order {
    private Long orderId;
    private final Double amount;
    private final String comment;
    private final String fileName;
    private final Integer line;


    private final String status;


    public Order(Long id, Double amount, String currency, String comment, String fileName, Integer line, String status) {
        this.orderId = id;
        this.amount = amount;
        Currency.getInstance(currency);
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        this.status = status;
    }

    public void setId(Long id) {
        this.orderId = id;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = new DecimalFormat("#.#####");
        return "{" +
                "\"id\":" + orderId +
                ", \"amount\":" + numberFormat.format(amount) +
                ", \"comment\":\"" + comment + "\"" +
                ", \"fileName\":\"" + fileName + "\"" +
                ", \"line\":" + line +
                ", \"result\":\"" + status + "\"" +
                '}';
    }
}
