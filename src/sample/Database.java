package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String SELECT_COUNTRY="SELECT Code, Name FROM country ORDER BY Name";
    private final String SELECT_CITY=
            "SELECT country.Name, city.Name, city.CountryCode, country.Code2, JSON_EXTRACT(Info, '$.Population') AS Info "+
            " FROM city "+
            " INNER JOIN country ON city.CountryCode = country.Code "+
            " WHERE country.Name LIKE ? "+
            " ORDER BY city.Name";


    public List getListOfCountries() throws Exception{

            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(SELECT_COUNTRY);
            ResultSet rs = pstm.executeQuery();
            String country;
            List<String> list = new ArrayList<>();
            while(rs.next()){
                country= rs.getString("Name");

                list.add(country);
            }
            con.close();
            return list;

    }

    public List getListOfCities(String country) throws Exception{

        Connection con = getConnection();
        PreparedStatement pstm = con.prepareStatement(SELECT_CITY);
        pstm.setString(1,country);
        ResultSet rs = pstm.executeQuery();

        List<City> list = new ArrayList<>();

        while(rs.next()){
            String name= rs.getString("city.Name");
            String code2=rs.getString("country.Code2");
            String code3=rs.getString("city.CountryCode");
            String countryName=rs.getString("country.Name");
            int population=rs.getInt("Info");
            City newCity=new City(name,population,code3,code2, countryName);
            list.add(newCity);
        }
        return list;

    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://itsovy.sk:3306/world_x", "student", "kosice2019");
        return con;
    }
}
