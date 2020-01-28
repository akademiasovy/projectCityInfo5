package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;



import java.net.URI;
import java.util.List;

public class Controller {
    public ComboBox<String> combo1;
    public ComboBox combo2;
    public Button btnSearchData;
    public Label lblCity;
    public Label lblCountry;
    public Label lblPopulation;
    public Pane paneDetail;
    private List<City> cities = null;
    List<String> countries = null;

    public Controller() throws Exception {
        Database db = new Database();
        countries = db.getListOfCountries();
        for (String s : countries)
            System.out.println(s);
    }


    public void selectCountry(Event event) {
        combo1.getItems().setAll(countries);


    }

    public void getCountry(ActionEvent actionEvent) throws Exception {

        String country = null;
        country=(String)combo1.getValue();
        System.out.println("You selected: "+country);

        if(country!=null){
            Database db = new Database();
            cities = db.getListOfCities(country);
            combo2.getItems().clear();
            for (City s : cities) {
                System.out.println(s.getName());
                combo2.getItems().add(s.getName());
            }
            combo2.setDisable(false);
        }
    }

    public void getCity(ActionEvent actionEvent) {
        btnSearchData.setDisable(false);
    }

    public void showData(ActionEvent actionEvent) throws  Exception {

        String cityName= (String)combo2.getValue();

        City city=null;
        for(City c : cities){
            if(c.getName().equalsIgnoreCase(cityName)) {
                city = c;
                break;
            }
        }
        if(city==null)
            return;

        System.out.println("Data:" + city.getName() + " " + city.getPopulation());
        lblCity.setText("City:   "+city.getName());
        lblCountry.setText("Country:   "+city.getCountry() +" ("+city.getCode3()+")");
        lblPopulation.setText("Population:   "+formatPopString(city.getPopulation()));
        //
    }

    private String formatPopString(int population) {
        String data=String.valueOf(population);
        String text="";

        for(int i=0;i<=data.length()-1;i++) {
            if(i%3==0)
                text=" "+text;
            text = data.charAt(data.length()-i-1)+text;
        }
        return text.trim();
    }

}