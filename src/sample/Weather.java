package sample;

public class Weather {
    private String name;
    private String country;
    private double temp;
    private int humidity;
    private double lon;
    private double lat;

    public Weather(String name, String country, double temp, int humidity, double lon, double lat) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
