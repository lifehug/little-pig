import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.*;
import com.etherfuse.inspector.NmapSNType;

public class NmapSNTypeTest{
   NmapSNType parse;// = new NmapSPType();
   
   // assigning the values
   @Before
    public void setUp(){
       parse = new NmapSNType();

   }

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

   @Test 
   public void testParse(){
    // test the parse function from a static file
    assertTrue(true);
   }  
}