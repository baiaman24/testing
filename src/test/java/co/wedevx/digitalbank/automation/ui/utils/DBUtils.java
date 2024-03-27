package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.*;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;


public class DBUtils {
    private  static Connection connection = null;
    private  static Statement statement = null;
    private static ResultSet resultSet = null;
    //method to establish connection with the db
    public static void establishConnection(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    getPropertiesValue("digitalbank.db.url"),
                    getPropertiesValue("digitalbank.db.username"),
                    getPropertiesValue("digitalbank.db.password")); // in the real world it needs to be in the properties class

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //a method that can dynamically send select statements
    //and return a list of map of all column
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery){
        List<Map<String, Object>> resultList= new ArrayList<>();
        try {
             statement = connection.createStatement();
             resultSet = statement.executeQuery(sqlQuery);
             //getMetaData-- info about info
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
             int columnCount = resultSetMetaData.getColumnCount();
             while (resultSet.next()){
                 Map<String, Object> rowMap = new HashMap<>();
                 for(int col = 1; col <= columnCount; col++){
                     rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                 }
                 resultList.add(rowMap);
             }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    // a method that inserts into db
    //return the nums of rows updated or 0 when action is not taken


    //delete or truncate the table
    public static int runSQLUpdateQuery(String sqlQuery){
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }



//    Return the nth row from the table as a list of maps:
//
//    The method should return the nth row if the table is not null and the requested row number is present in the table
//
//    The method should accept a positive integer as the nth-row number
//
//    The return type is a list of maps, where the key is a string and the value is an object
//
//    Handle exceptions
    public static Map<String, Object> returnFirstRow(String sqlQuery){
       List<Map<String, Object>> allRows = runSQLSelectQuery(sqlQuery);
        return allRows.isEmpty() ? Collections.emptyMap() : allRows.get(0);
    }
    public static Map<String, Object> returnLastRow(String sqlQuery){
        List<Map<String, Object>> allRows = runSQLSelectQuery(sqlQuery);
        return allRows.isEmpty() ? Collections.emptyMap() : allRows.get(allRows.size() - 1);
    }
    public static Map<String, Object> returnNthRow(String sqlQuery, int rowNumber){
        List<Map<String, Object>> allRows = runSQLSelectQuery(sqlQuery);

        return (rowNumber >= 0 && rowNumber < allRows.size()) ? allRows.get(rowNumber) : Collections.emptyMap();
    }



    //close connection method
    public static void closeConnection(){
        try{
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


}
