package oystr.java.test.models;

public interface Machine {
    String getModel();
    String getContractType();
    String getMake();
    int getYear();
    int getWorkedHours();
    String getCity();
    double getPrice();
    String getPhotoUrl();

    void setModel(String model);
    void setContractType(String contractType);
    void setMake(String make);
    void setYear(int year);
    void setWorkedHours(int workedHours);
    void setCity(String city);
    void setPrice(double price);
    void setPhotoUrl(String photoUrl);
}
