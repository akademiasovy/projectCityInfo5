package sample;

public class City {
    private String name;
    private  int  population;
    private String code3;
    private String code2;
    private String country;

    public City(String name, int population, String code3, String code2, String country) {
        this.name = name;
        this.population = population;
        this.code3 = code3;
        this.code2 = code2;
        this.country=country;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getCode3() {
        return code3;
    }

    public String getCode2() {
        return code2;
    }

    public String getCountry() {
        return country;
    }
}
