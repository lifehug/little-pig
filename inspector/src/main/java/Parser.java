package com.etherfuse.inspector;

import java.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;


public class Parser{
  public static void main(String [] args){
     
    if(args.length != 1){
      System.out.println("Needs argument");
      return;
    }

    try {
      
      List<String> lines = Files.readAllLines(Paths.get(args[0]));
      
      String macAddress = "";
      String ipAddress = "";
      String operatingSystem = "";
      String time = "";
      List<Host> hosts = new ArrayList<Host>();
      Discovery discovery;

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

        System.out.println(line);

      }

      discovery = new Discovery(time, hosts);


    } catch (Exception e){
      e.printStackTrace();
    }


  }

  public static String getMAC(String string){
    return "";
  }

  public static String getOS(String os){
    return "";
  }

  public static String getIP(String ip){
    return "";
  }

  public static String getTime(String time){
    return "";
  }

}