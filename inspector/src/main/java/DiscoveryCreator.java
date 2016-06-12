package com.etherfuse.inspector;

import java.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;


public class DiscoveryCreator{

  String fileName;

  public DiscoveryCreator(String fileName){
    this.fileName = fileName;
  }

  public Discovery create(){
    
    Discovery discovery;
    String macAddress = "";
    String ipAddress = "";
    String operatingSystem = "";
    String time = "";
    List<Host> hosts = new ArrayList<Host>();
    List<String> lines;

    try {

      if(fileName == null){
       throw new Exception("host discovery file is null or does not exist");
      }
      
      lines = Files.readAllLines(Paths.get(fileName));
      for(String line : lines){

        if(line.startsWith("MAC Address: ")){
          macAddress = getMAC(line);
          operatingSystem = getOS(line);
          hosts.add(new Host(ipAddress, macAddress, operatingSystem));
        } else if(line.startsWith("Nmap scan report for")){
          ipAddress = getIP(line);
        } else if(line.startsWith("Starting Nmap")){
          time = getTime(line);
        }

      }



    } catch (Exception e){
      e.printStackTrace();
    }

    return new Discovery(time, hosts);
  }

  public static String getMAC(String string){
    //MAC Address: E0:88:5D:D2:6C:53 (Technicolor CH USA)
    return StringUtils.substringBetween(string, "MAC Address: ", " (");
  }

  public static String getOS(String os){
    return StringUtils.substringBetween(os, "(", ")");
  }

  public static String getIP(String ip){
    return StringUtils.substringAfter(ip, "Nmap scan report for ");
  }

  public static String getTime(String time){
    return StringUtils.substringAfter(time, "( https://nmap.org ) at ");
  }

}