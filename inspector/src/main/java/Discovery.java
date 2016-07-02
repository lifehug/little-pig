package com.etherfuse.inspector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Iterator;

public class Discovery{
  String date;
  List<Host> hosts;
  String command; 

  public Discovery(String date, String command, List<Host> hosts){
    this.date = date;
    this.command = command;
    this.hosts = hosts;
  }

  public LocalDateTime getDate(){

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm zzz");
    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

    return dateTime;
  }

  public Iterator<Host> hostsIterator(){
   return hosts.iterator();
  }

  public String toString(){

      StringBuilder sb = new StringBuilder();
      hosts.forEach( host -> {
          sb.append(host);
      });

      return "Discovery Date :" + date + " \nHosts \n" + sb.toString();

  }

}