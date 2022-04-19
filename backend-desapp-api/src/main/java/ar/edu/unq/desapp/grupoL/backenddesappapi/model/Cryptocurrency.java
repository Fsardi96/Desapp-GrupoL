package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

public class Cryptocurrency {
    private String name;
    private Float price;
    private String lastUpdateDateAndTime;

    public Cryptocurrency() { }

    public Cryptocurrency(String name, Float price, String lastUpdateDateAndTime) {
        this.name = name;
        this.price = price;
        this.lastUpdateDateAndTime = lastUpdateDateAndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLastUpdateDateAndTime() {
        return lastUpdateDateAndTime;
    }

    public void setLastUpdateDateAndTime(String lastUpdateDateAndTime) {
        this.lastUpdateDateAndTime = lastUpdateDateAndTime;
    }
}
