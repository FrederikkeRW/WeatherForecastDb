package WeatherForecast.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static java.sql.DriverManager.getConnection;

public class DbAccess {
    /**
    Url for the WeatherForecast database
     */
    private static String url = "jdbc:sqlite:"+ System.getProperty("user.home") +"/Databaser/weather_forecast.sqlite";

    /**
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

    /**
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

    /**
    Get temperature values from tariff, based on DMI data
     */

    public static HashMap<String,String> getTemperatureValues(int dmiTemp){
        String sql =  "SELECT TEMP_VALUE VALUE,TEMP_TEXT TEXT,TEMP_DESCRIPTION DESCRIPTION FROM TEMP_TARIFF WHERE TEMP_FROM <= ? AND TEMP_TO >= ?";

        return getValues(dmiTemp, sql);
    }

    public static HashMap<String,String> getWindValues(int dmiTemp){
        String sql =  "SELECT WIND_VALUE VALUE, WIND_TEXT TEXT, WIND_DESCRIPTION DESCRIPTION FROM WIND_TARIFF WHERE WIND_FROM <= ? AND WIND_TO >= ?";

        return getValues(dmiTemp, sql);
    }

    public static HashMap<String,String> getRainValues(int dmiTemp){
        String sql =  "SELECT RAIN_VALUE VALUE, RAIN_TEXT TEXT, RAIN_DESCRIPTION DESCRIPTION FROM RAIN_TARIFF WHERE RAIN_FROM <= ? AND RAIN_TO >=?";

        return getValues(dmiTemp, sql);
    }

    public static HashMap<String,String> getSnowValues(int dmiTemp){
        String sql =  "SELECT SNOW_VALUE VALUE, SNOW_TEXT TEXT, SNOW_DESCRIPTION DESCRIPTION FROM SNOW_TARIFF WHERE SNOW_FROM <= ? AND SNOW_TO >= ?";

        return getValues(dmiTemp, sql);
    }


    private static HashMap<String,String> getValues(int dmiTemp, String sql){
        HashMap<String,String> result = new HashMap<>();

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


            /**
            Error Handling:
                1. if:  Handles value. The value has to exist in the database
                2. if:  Handles size. We should only get one result back
             */
            if (rs.isClosed()){
                throw new RuntimeException("Value " + dmiTemp +" does not exist in database, using this sql: "+ sql+"\n Please update table, to include this value.");
            }

            if (rs.getFetchSize() > 1){
                throw new RuntimeException("The database should only return one row. The value "+dmiTemp+" returns "+ rs.getFetchSize() + " in sql: "+ sql+". Please update table.");
            }

            result.put("VALUE",rs.getString("VALUE"));
            result.put("TEXT",rs.getString("TEXT"));
            result.put("DESCRIPTION",rs.getString("DESCRIPTION"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDbConnection(conn);
        return result;
    }

}
