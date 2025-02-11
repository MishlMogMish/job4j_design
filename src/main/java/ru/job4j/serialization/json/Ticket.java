package ru.job4j.serialization.json;

import java.util.Arrays;

public class Ticket {
    private final boolean oneWay;
    private final int price;
    private final String[] fromToAirports;
    private final Documents documents;

    public Ticket(boolean oneWay, int price, String[] fromToAirports, Documents documents) {
        this.oneWay = oneWay;
        this.price = price;
        this.fromToAirports = fromToAirports;
        this.documents = documents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ticket{");
        sb.append("oneWay=").append(oneWay);
        sb.append(", price=").append(price);
        sb.append(", fromToAirports=").append(fromToAirports == null ? "null" : Arrays.asList(fromToAirports).toString());
        sb.append(", documents=").append(documents.toString());
        sb.append('}');
        return sb.toString();
    }
}
