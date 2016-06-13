package com.etherfuse.inspector;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.lang.Integer;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DiscoveryDataHandler{

  Discovery discovery;
  Connection connection;

  public DiscoveryDataHandler(Discovery discovery){
    this.discovery = discovery;

  }

  public void save() throws SQLException {
    
    connection = getConnection();
    Date date = Date.valueOf(discovery.getDate());
    Iterator<Host> it = discovery.hostIterator();
    StringBuilder insertionQuery = new StringBuilder();

    Statement st = connection.createStatement();
    while (it.hasNext()){
      String entry = getQuery(host, date);
      st.addBatch(entry);
    }


    ResultSet results = st.executeBatch();
    // verify the result set
  }


// Update Users set weight=160, desiredWeight=145  WHERE id = 1;

  public String getQuery(String mac, String os, String ip_addr, Date last_seen, Date discovered){
    String query =  "INSERT INTO device (mac, discovered) WHERE NOT EXISTS( SELECT mac FROM DEVICE WHERE mac = " + mac + ");" +
    "UPDATE device SET ip_addr =" + ip_addr + ", last_seen=" + last_seen + ", os=" + os + " WHERE mac = " + mac + ";";
    return query; 
  }


  // public String executeQuery(Host host, Date last_seen) throws SQLException{

  //   Date discovered = last_seen
  //   Optional<Date> possibleDate = executeDiscoveredQuery(Host host);

  //   if(possibleDate.isPresent()){
  //     discovered = possibleDate.get();
  //   }
   
  //   return getHostDetailQuery(host.getMAC(), host.getOS(), Integer.toString(host.getIPAddress()), last_seen, discovered) 

  // }

  public Optional<Date> executeDiscoveredQuery(Host host) throws SQLException {
    
    Connection conn = getConnection();
    Statement st = conn.createStatement();
    ResultSet results = st.executeQuery( getDiscoveredQuery(host.getMAC()));
    Date discovered = results.getDate("discovered");
    Optional<Date> possibleDiscoveredDate = Optional.of(discovered.toString());
    return possibleDiscoveredDate; 

  }



  public Connection getConnection(){

    if(connection == null){

      // get this data from a properties file
      String url = "jdbc:mysql://database:3306/snort";
      String user = "snort";
      String password = "Wdnb6EV2x6nNaVuDzeaZ2N9MFbtz3J8YBciz34wkvpkyGT8Apq";
      // Load the Connector/J driver
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager.getConnection(url, user, password);
    }

    return connection;

  }

}

