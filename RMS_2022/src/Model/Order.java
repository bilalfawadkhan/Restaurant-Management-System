package Model;

public class Order {
    String name;
    Double Pries;
    int anzahl;
    String customer;

    public Order (String name, Double pries, int anzahl,String customer) {
        this.name = name;
        Pries = pries;
        this.anzahl = anzahl;
        this.customer = customer;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Double getPries () {
        return Pries;
    }

    public void setPries (Double pries) {
        Pries = pries;
    }

    public int getAnzahl () {
        return anzahl;
    }

    public void setAnzahl (int anzahl) {
        this.anzahl = anzahl;
    }

    public String getCustomer () {
        return customer;
    }

    public void setCustomer (String customer) {
        this.customer = customer;
    }
}
