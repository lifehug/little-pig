import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.*;
import com.etherfuse.inspector.NmapSNType;
import com.etherfuse.inspector.Discovery;
import com.etherfuse.inspector.Host;
import java.util.ArrayList;
import java.util.List;

public class NmapSNTypeTest{
   NmapSNType parse;
   Discovery noMAC;
   String args = "nmap -sn -oX inspector/src/test/resources/nmap-run-nomac.xml 192.168.1.0/24";
   String time = "Mon Jul 25 15:23:34 2016";
   
   // assigning the values
   @Before
   public void setUp(){
      parse = new NmapSNType();
   }

   @Test 
   public void testParseWithoutMAC() throws Exception {
      Discovery test = parse.parseFile("src/test/resources/nmap-run-nomac.xml");
      List<Host> hosts = new ArrayList<Host>();
      Host host = new Host("192.168.1.1", "", "", "Pumphouse");
      hosts.add(host);                  
      noMAC = new Discovery(time, args, hosts);
      assertTrue(noMAC.equals(test));
   } 

   @Test 
   public void testParse() throws Exception {
      Discovery test = parse.parseFile("src/test/resources/nmap-run.xml");
      List<Host> hosts = new ArrayList<Host>();
      Host host = new Host("192.168.1.1", "C0:56:27:6F:22:21", "Belkin International", "Pumphouse");
      hosts.add(host);                  
      noMAC = new Discovery(time, args, hosts);
      assertTrue(noMAC.equals(test));
   }  
       
}