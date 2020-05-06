package WeatherForecast.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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

    /*
    Get temp values from tariff, based on DMI data
     */

    public static HashMap<String,String>  getTempValues (int dmiTemp){
        HashMap<String,String> result = new HashMap<>();

        String sql =  "SELECT * FROM TEMP_TARIFF WHERE TEMP_FROM <= ? AND TEMP_TO >= ?";
         Connection conn = openDBConnection();
         PreparedStatement pstmt = null;
             try {
                 pstmt = conn.prepareStatement(sql);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             try {
                 pstmt.setInt(1,  dmiTemp);
                 pstmt.setInt(2,  dmiTemp);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             ResultSet rs = null;
             try {
                 rs = pstmt.executeQuery();

                 result.put("TEMP_VALUE",rs.getString("TEMP_VALUE"));
                 result.put("TEMP_TEXT",rs.getString("TEMP_TEXT"));
                 result.put("TEMP_DESCRIPTION",rs.getString("TEMP_DESCRIPTION"));

             } catch (SQLException e) {
                 e.printStackTrace();
             }

             closeDbConnection(conn);
             return result;
    }

}
