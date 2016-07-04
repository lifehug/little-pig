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
      String hostname = host.getHostname();
      if(!hostname.equals("")){
        String insert = getInsert(host, date);
        String update = getUpdate(host, date);
        System.out.println(insert);
        System.out.println(update);
        st.addBatch(insert);
        st.addBatch(update);
      }
    }
    int[] outcome = st.executeBatch();

    Arrays.parallelSort(outcome);
    if(outcome[0] == Statement.EXECUTE_FAILED) throw new Exception("You're database query didn't work");
    // verify the result set
  }

  public String getInsert(Host host, LocalDateTime date){
    String mac = host.getMAC();
    String vendor = host.getVendor();
    long ip_addr = host.getIPAddress();
    String hostname = host.getHostname();

    String query = "INSERT INTO device(hostname, discovered, ip_addr, last_seen) SELECT '" + hostname + "', '" + date + "', '" + ip_addr + "', '" + date + "' FROM dual WHERE NOT EXISTS (SELECT * FROM device WHERE hostname = '" + hostname + "');";


    return query; 
  }

  public String getUpdate(Host host, LocalDateTime date){

    String mac = host.getMAC();
    String vendor = host.getVendor();
    long ip_addr = host.getIPAddress();
    String hostname = host.getHostname();

    String query = "UPDATE device SET ip_addr=" + ip_addr + ", last_seen='" + date + "', vendor='" + vendor + "', mac='" + mac + "' WHERE hostname ='" + hostname + "';";

    return query; 
  }

}

