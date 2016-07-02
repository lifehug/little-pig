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

public class NmapPRType implements Type{

  public void execute(String network) throws Exception{
    
      ProcessBuilder builder = new ProcessBuilder("nmap", "-PR", network, "-oX", "output");
      builder.redirectError(new File("error.log"));
      Process p = builder.start(); 
      int errCode = p.waitFor();

      if(errCode != 0){
        throw new Exception("Process Not Executed");
      }

  }
  
  public Discovery parse() throws Exception {

    // create a ParserType that overrides parese
    Discovery discovery;
    String macAddress = "";
    String ipAddress = "";
    String operatingSystem = "";
    String interfaceName = "";
    String time = "";
    List<Host> hosts = new ArrayList<Host>();
    List<String> lines;
      
      lines = Files.readAllLines(Paths.get("output.nmap"));
      System.out.println(lines);
      for(String line : lines){

        if(line.startsWith("MAC Address: ")){
          macAddress = getMAC(line);
          operatingSystem = getOS(line);
          hosts.add(new Host(ipAddress, macAddress, operatingSystem, interfaceName));
        } else if(line.startsWith("Nmap scan report for")){
          ipAddress = getIP(line);
          interfaceName = getInterfaceName(line);
        } else if(line.startsWith("Starting Nmap")){
          time = getTime(line);
        }

      }

    return new Discovery(time, hosts);

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
    return StringUtils.substringBetween(line, "(", ")");
  }

  public String getInterfaceName(String line){
    return StringUtils.substringBetween(line, "Nmap scan report for ", " (");
  }

}