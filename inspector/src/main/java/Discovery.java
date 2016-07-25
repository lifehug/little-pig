package com.etherfuse.inspector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Iterator;
import java.util.Objects;

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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM ppd HH:mm:ss yyyy");
    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

    return dateTime;
  }

  public Iterator<Host> hostsIterator(){
   return hosts.iterator();
  }

  public boolean equals(Object o) {

     if (o == this) return true;
     if (!(o instanceof Discovery)) {
         return false;
     }
     
     Discovery discovery = (Discovery) o;
     return Objects.equals(date, discovery.date) 
      && Objects.equals(command, discovery.command) 
      && hosts.containsAll(discovery.hosts) 
      && discovery.hosts.containsAll(hosts)
      && hosts.size() == discovery.hosts.size();
  }

  public int hashCode(){
    return Objects.hash(command, date);
  }

  public String toString(){

      StringBuilder sb = new StringBuilder();
      hosts.forEach( host -> {
          sb.append(host);
      });

      return "Discovery Date :" + date + " \nHosts \n" + sb.toString();

  }

}