package com.etherfuse.inspector;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.lang.Integer;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Iterator;


public class DiscoveryDataHandler{

  Discovery discovery;
  Connection connection;

  public DiscoveryDataHandler(Discovery discovery){
    this.discovery = discovery;

  }

  public void save() throws Exception {
    
    connection = getConnection();
    LocalDateTime date = discovery.getDate();
    Iterator<Host> it = discovery.hostsIterator();
    StringBuilder insertionQuery = new StringBuilder();

    Statement st = connection.createStatement();
    while (it.hasNext()){
      Host host = it.next();
      String entry = getQuery(host, date);
      st.addBatch(entry);
    }

    int[] outcome = st.executeBatch();
    Arrays.parallelSort(outcome);
    if(outcome[0] == Statement.EXECUTE_FAILED) throw new Exception("You're database query didn't work");
    // verify the result set
  }

  public String getQuery(Host host, LocalDateTime last_seen){
    String mac = host.getMAC();
    String os = host.getOS();
    int ip_addr = host.getIPAddress();
    String query =  "INSERT INTO device (mac, discovered) WHERE NOT EXISTS( SELECT mac FROM DEVICE WHERE mac = " + mac + ");" +
    "UPDATE device SET ip_addr =" + ip_addr + ", last_seen=" + last_seen + ", os=" + os + " WHERE mac = " + mac + ";";
    return query; 
  }

  public Connection getConnection() throws Exception{

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

