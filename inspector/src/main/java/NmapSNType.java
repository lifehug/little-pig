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
import org.w3c.dom.Node;

public class NmapSNType implements Type{

  public void execute(String network) throws Exception{
    
      ProcessBuilder builder = new ProcessBuilder("nmap", "-sn", network, "-oX", "output");
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
    NodeList node = document.getElementsByTagName("nmaprun");
    NodeList hostsAttrs = document.getElementsByTagName("host");
    Node runData = node.item(0);
    //args="nmap -sn -oA output 192.168.1.0/24" scanner="nmap" start="1467474733" startstr="Sat Jul  2 08:52:13 2016" 

    return new Discovery(null, null, null);

  }


  public String getOS(String line){
    return StringUtils.substringBetween(line, "(", ")");
  }

  public String getMAC(String line){
    return StringUtils.substringBetween(line, "MAC Address: ", " (");
  }

  public String getTime(String line){
    return StringUtils.substringAfter(line, "( https://nmap.org ) at ");
  }

  public String getIP(String line){
    return StringUtils.substringAfter(line, "Nmap scan report for ");
  }

}