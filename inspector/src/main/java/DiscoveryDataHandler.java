
public class DiscoveryDataHandler{

  Discovery discovery;

  public DiscoveryDataHandler(Discovery discovery){
    this.discovery = discovery;
  }

  public void save(){


    // get a connection to the database

    // iterate through disovery object and save to database
  }

  // device (mac BIGINT UNSIGNED NOT NULL,
  // ip_addr INT UNSIGNED NOT NULL,
  // src_prt INT UNSIGNED NOT NULL,
  // discovered DATETIME NOT NULL, 
  // last_seen DATETIME NOT NULL,
  // os VARCHAR(35), 
  // os_details VARCHAR(255),
  // name VARCHAR(100),
  // PRIMARY KEY (mac));

  public String getHostDetailQuery(String mac, String os, String ip_addr, String last_seen, String discovered){
    return "INSERT INTO device (mac, ip_addr, last_seen, discovered) VALUES (" + mac "," + ip_addr "," + last_seen + "," + os + "," + discovered + ");"
  }

  public String getDiscoveredQuery(String mac){
    return "SELECT discovered FROM device WHERE mac =" + mac + ";";
  }

  public void executeQuery(Host host, String last_seen, String discovered){
    Connection conn = getConnection();
    Statement st = conn.createStatement();
    ResultSet results = st.executeQuery( getHostDetailQuery(host.getMAC(), host.getOS(), host.getIPAddress(), last_seen, discovered) );

    // do a check here for the results, throw and error if it errors
  }

  public String executeDiscoveredQuery(Host host, last_seen){
    
    Connection conn = getConnection();
    Statement st = conn.createStatement();
    ResultSet results = st.executeQuery( getDiscoveredQuery(host.getMAC());

    return last_seen; 
  }



  public Connection getConnection(){

  }
}