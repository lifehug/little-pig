import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import com.etherfuse.inspector.DiscoveryCreator;

public class TestExtraction{
   DiscoveryCreator parse;
   
   // assigning the values
   protected void setUp(){
       DiscoveryCreator parse = new DiscoveryCreator("filename");
   }


// Starting Nmap 7.00 ( https://nmap.org ) at 2016-06-11 16:02 PDT
// Nmap scan report for 10.0.0.1
// Host is up (0.011s latency).
// MAC Address: E0:88:5D:D2:6C:53 (Technicolor CH USA)
// Nmap scan report for 10.0.0.2
// Host is up (0.011s latency).
// MAC Address: E0:88:5D:D2:6C:55 (Technicolor CH USA)
// Nmap scan report for 10.0.0.10
// Host is up (0.013s latency).
// MAC Address: 1C:1A:C0:67:70:1D (Apple)
// Nmap scan report for 10.0.0.7
// Host is up.
// Nmap done: 256 IP addresses (4 hosts up) scanned in 2.13 seconds

   // test method to add two values
   @Test
   public void testMAC(){
      String line = "MAC Address: E0:88:5D:D2:6C:53 (Technicolor CH USA)";
      String result = parse.getMAC(line);

      assertEquals("E0:88:5D:D2:6C:53", result);

   }

   @Test
   public void testOS(){
      String line = "MAC Address: E0:88:5D:D2:6C:53 (Technicolor CH USA)";
      String result = parse.getOS(line);

      assertEquals("Technicolor CH USA", result);

   }

   @Test
   public void testIP(){
      String line = "Nmap scan report for 10.0.0.2";
      String result = parse.getIP(line);

      assertEquals("10.0.0.2", result);

   }

   @Test
   public void testDate(){
      String line = "Starting Nmap 7.00 ( https://nmap.org ) at 2016-06-11 16:02 PDT";
      String result = parse.getTime(line);

      assertEquals("2016-06-11 16:02 PDT", result);

   }   
}