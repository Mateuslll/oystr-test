package oystr.java.test.models.impl;

import oystr.java.test.models.Machine;

public class MachineImpl implements Machine {
    private String model;
    private String contractType;
    private String make;
    private int year;
    private int workedHours;
    private String city;
    private double price;
    private String photoUrl;

    @Override
    public String getModel() { return model; }
    @Override
    public void setModel(String model) { this.model = model; }

    @Override
    public String getContractType() { return contractType; }
    @Override
    public void setContractType(String contractType) { this.contractType = contractType; }

    @Override
    public String getMake() { return make; }
    @Override
    public void setMake(String make) { this.make = make; }

    @Override
    public int getYear() { return year; }
    @Override
    public void setYear(int year) { this.year = year; }

    @Override
    public int getWorkedHours() { return workedHours; }
    @Override
    public void setWorkedHours(int workedHours) { this.workedHours = workedHours; }

    @Override
    public String getCity() { return city; }
    @Override
    public void setCity(String city) { this.city = city; }

    @Override
    public double getPrice() { return price; }
    @Override
    public void setPrice(double price) { this.price = price; }

    @Override
    public String getPhotoUrl() { return photoUrl; }
    @Override
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    @Override
    public String toString() {
        return "MachineImpl{" +
                "model='" + model + '\'' +
                ", contractType='" + contractType + '\'' +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", workedHours=" + workedHours +
                ", city='" + city + '\'' +
                ", price=" + price +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}