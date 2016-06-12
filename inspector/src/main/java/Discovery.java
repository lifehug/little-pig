package com.etherfuse.inspector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class Discovery{
  String discovery;
  List<Host> hosts;

  public Discovery(String time, List<Host> hosts){
    this.discovery = time;
    this.hosts = hosts;
  }

  public LocalDateTime getDate(){

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm zzz");
    LocalDateTime dateTime = LocalDateTime.parse(discovery, formatter);

    return dateTime;
  }

  public List<Host> getHosts(){
    return hosts;
  }

  public String toString(){

      StringBuilder sb = new StringBuilder();
      hosts.forEach( host -> {
          sb.append(host);
      });

      return "Discovery Date :" + discovery + " \nHosts \n" + sb.toString();

  }

}