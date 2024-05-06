package org.example;

import javax.xml.transform.Result;
import java.sql.*;

public class HelloDB {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:sqlite:hello.sqLite";

        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

     //   String createTableSQL = "CREATE TABLE cats (name TEXT, age INTEGER)";
      //  statement.execute(createTableSQL);

       // String insertDataSQL = "INSERT INTO cats VALUES ('Maru', 10)"; // use single quotes'' for sql
       // statement.execute(insertDataSQL);

       // insertDataSQL = "INSERT INTO cats VALUES ('Hello Kitty', 40)";
       // statement.execute(insertDataSQL);

        String getAllDateSQL = "SELECT * FROM cats";  // SELECT * = get all data
        ResultSet allCats = statement.executeQuery(getAllDateSQL);

        while (allCats.next()) {
            String name = allCats.getString("name");
            int age = allCats.getInt("age");
            System.out.println(name + " is " + age + " years old.");
        }
        /*
        getString(colName) method for TEXT columns,
        getInt(colName) for INTEGER columns,
        getDouble(colName) for REAL columns
         */



    }
}
