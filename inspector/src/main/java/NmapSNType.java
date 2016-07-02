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

public class NmapSPType implements Type{

  public void execute(String network, String outputFile) throws Exception{
    
      ProcessBuilder builder = new ProcessBuilder("nmap", "-sn", network);
      builder.redirectOutput(new File(outputFile));
      builder.redirectError(new File(outputFile));
      Process p = builder.start(); // throws IOException
      int errCode = p.waitFor();

      if(errCode != 0){
        throw new Exception("Process Not Executed");
      }

  }
  
  public Discovery parse(String filename) throws Exception {

    // create a ParserType that overrides parese
    Discovery discovery;
    String macAddress = "";
    String ipAddress = "";
    String operatingSystem = "";
    String time = "";
    List<Host> hosts = new ArrayList<Host>();
    List<String> lines;


      if(filename == null){
       throw new Exception("host discovery file is null or does not exist");
      }
      
      lines = Files.readAllLines(Paths.get("output.txt"));
      for(String line : lines){

        if(line.startsWith("MAC Address: ")){
          macAddress = StringUtils.substringBetween(line, "MAC Address: ", " (");
          operatingSystem = StringUtils.substringBetween(line, "(", ")");
          hosts.add(new Host(ipAddress, macAddress, operatingSystem));
        } else if(line.startsWith("Nmap scan report for")){
          ipAddress = StringUtils.substringAfter(line, "Nmap scan report for ");
        } else if(line.startsWith("Starting Nmap")){
          time = StringUtils.substringAfter(line, "( https://nmap.org ) at ");
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
    return StringUtils.substringAfter(line, "Nmap scan report for ");
  }

}