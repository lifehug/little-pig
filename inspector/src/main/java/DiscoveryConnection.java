package com.etherfuse.inspector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DiscoveryConnection{
  
  static Connection connection;
  
  private DiscoveryConnection(){

  }

  public static Connection getConnection() throws Exception{

    if(connection == null){

      // eventually get this data from a properties file loaded from environment variables via docker
      String url = "jdbc:mysql://127.0.0.1:3306/snort";
      String user = "snort";
      String password = "Wdnb6EV2x6nNaVuDzeaZ2N9MFbtz3J8YBciz34wkvpkyGT8Apq";
      //Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager.getConnection(url, user, password);
    }

    return connection;

  }

}