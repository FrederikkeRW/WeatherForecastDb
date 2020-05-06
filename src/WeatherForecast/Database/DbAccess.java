package WeatherForecast.Database;

import java.sql.Connection;
import java.sql.SQLException;
import static java.sql.DriverManager.getConnection;

public class DbAccess {
    /*
    Url for the WeatherForecast database
     */
    private static String url = "jdbc:sqlite:"+ System.getProperty("user.home") +"/Databaser/weather_forecast.sqlite";

    /*
    Open connection, to access the WeatherForecast database url
     */
    public static Connection openDBConnection(){
        Connection conn = null;
        try {
            conn = getConnection(url);
        } catch
        (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /*
    To avoid memory- and resource leak, always remember to close a connection after use.
    Everytime you make an 'open' resource, always have a 'close'.
    */
    public static void closeDbConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
