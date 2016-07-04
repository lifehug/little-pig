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

  public DiscoveryDataHandler(Discovery discovery, Connection connection){
    this.discovery = discovery;
    this.connection = connection;
  }

  public void save() throws Exception {
    
    LocalDateTime date = discovery.getDate();
    Iterator<Host> it = discovery.hostsIterator();
    StringBuilder insertionQuery = new StringBuilder();

    Statement st = connection.createStatement();
    while (it.hasNext()){
      Host host = it.next();
      String entry = getQuery(host, date);
      System.out.println(entry);
      st.addBatch(entry);
    }
    int[] outcome = st.executeBatch();

    Arrays.parallelSort(outcome);
    if(outcome[0] == Statement.EXECUTE_FAILED) throw new Exception("You're database query didn't work");
    // verify the result set
  }

  public String getQuery(Host host, LocalDateTime date){
    String mac = host.getMAC();
    String vendor = host.getVendor();
    int ip_addr = host.getIPAddress();
    String hostname = host.getHostname();

    String query = "INSERT INTO device(hostname, discovered) SELECT '" + hostname + "', '" + date + "' FROM dual WHERE NOT EXISTS (SELECT * FROM device WHERE hostname = '" + hostname + "');";
    query += " UPDATE device SET ip_addr=" + ip_addr + ", last_seen='" + date + "',";
    if(!vendor.equals("")) query += " vendor='" + vendor + "',";
    query += " mac='" + mac + "' WHERE hostname ='" + hostname + "';";

    return query; 
  }

}

