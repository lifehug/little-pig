// import org.junit.Test;
// import org.junit.Before;
// import org.junit.Ignore;
// import java.util.List;
// import java.util.ArrayList;
// import static org.junit.Assert.*;
// import com.etherfuse.inspector.DiscoveryDataHandler;
// import com.etherfuse.inspector.Discovery;
// import com.etherfuse.inspector.Host;
// import java.sql.Connection;

// public class DiscoveryDataHandlerTest{
//   DiscoveryDataHandler dataHandler;
//   Discovery discovery;
//   Host host;

   
//    @Before
//     public void setUp(){
//       host = new Host("172.168.1.1", "E0:88:5D:D2:6C:53", "Apple", "kindle-6b8dcf313.hsd1.ca.comcast.net");
//       List<Host> hosts = new ArrayList<Host>();
//       hosts.add(host);
//       discovery = new Discovery("Tue Jul 12 08:52:13 2016", null, hosts);
//       dataHandler = new DiscoveryDataHandler(discovery, null);

//    }

//    // test method to add two values
//    @Test
//    public void testQuery(){
//       String query = dataHandler.getQuery(host, discovery.getDate());
//       String hope = "INSERT INTO device(hostname, discovered) SELECT 'kindle-6b8dcf313.hsd1.ca.comcast.net', '2016-07-12T08:52:13' FROM dual WHERE NOT EXISTS (SELECT * FROM device WHERE hostname = 'kindle-6b8dcf313.hsd1.ca.comcast.net'); UPDATE device SET ip_addr=2896691457, last_seen='2016-07-12T08:52:13', vendor='Apple', mac='E0:88:5D:D2:6C:53' WHERE hostname ='kindle-6b8dcf313.hsd1.ca.comcast.net';";
//       assertEquals(hope, query);

//    }

// }