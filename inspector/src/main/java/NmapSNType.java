package com.etherfuse.inspector;

import java.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.lang.ProcessBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NmapSNType implements Type{

  public void execute(String network) throws Exception{
    
      ProcessBuilder builder = new ProcessBuilder("nmap", "-sn", network, "-oX", "output.xml");
      builder.redirectError(new File("error.log"));
      Process p = builder.start();
      int errCode = p.waitFor();

      if(errCode != 0){
        throw new Exception("Process Not Executed");
      }

  }
  
  public Discovery parse() throws Exception {

    Discovery discovery;
    List<Host> hosts = new ArrayList<Host>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(Paths.get("output.xml").toFile());
    
    // discovery event data
    NodeList nmapRun = document.getElementsByTagName("nmaprun");
    NamedNodeMap runData = nmapRun.item(0).getAttributes();
    String args = runData.getNamedItem("args").getNodeValue();
    String time = runData.getNamedItem("startstr").getNodeValue();

    // host details
    NodeList hostList = document.getElementsByTagName("host");

    // iterate through all the hosts
    for(int i = 0; i < hostList.getLength(); i++){
      
      Node n = hostList.item(i);
      NodeList hostData = n.getChildNodes();
      String address = "";
      String mac = "";
      String vendor = "";
      String hostname = "";

      // iterate through all of the hosts data and find the correct vaules
      for(int x = 0; x < hostData.getLength(); x++){
        
        Node hostDetail = hostData.item(x);
        if(hostDetail.getNodeName().equals("address")){
          
          NamedNodeMap addressDetails = hostDetail.getAttributes();
          // get the correct address type data
          if(addressDetails.getNamedItem("addrtype").getNodeValue().equals("mac")){
            mac = addressDetails.getNamedItem("addr").getNodeValue();

            if(addressDetails.getNamedItem("vendor") != null){
              vendor = addressDetails.getNamedItem("vendor").getNodeValue();
            }

          } else {
            address = addressDetails.getNamedItem("addr").getNodeValue();
          }

        } else if(hostDetail.getNodeName().equals("hostnames")){
          
          NodeList hostNames = hostDetail.getChildNodes();
          // iterate through all of hostnames elements and get the hostname name
          for (int y = 0; y < hostNames.getLength(); y++){
            Node hostName = hostNames.item(y);

            if(hostName.getNodeName().equals("hostname")){
              NamedNodeMap hostnameAttrs = hostName.getAttributes();
              hostname = hostnameAttrs.getNamedItem("name").getNodeValue();
            }
          }

        }
     
      }

      hosts.add(new Host(address, mac, vendor, hostname));
    }

    return new Discovery(time, args, hosts);

  }

}