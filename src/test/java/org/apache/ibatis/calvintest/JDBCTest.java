package org.apache.ibatis.calvintest;

import java.sql.*;

public class JDBCTest {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = null;
    try {
      connection= DriverManager.getConnection("jdbc:mysql://47.98.195.145:3306/test_1", "root", "123456");
      PreparedStatement statement = connection.prepareStatement("select id , content from blog");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()){
        int id = resultSet.getInt("id");
        String content = resultSet.getString("content");
        System.out.println(id+"--"+content);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      connection.close();
    }
  }
}
