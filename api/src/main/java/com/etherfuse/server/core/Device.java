package com.etherfuse.server.core;

import java.sql.Date;
import java.util.Objects;

public class Device{
  
  private String mac;
  private Long ipAddress;
  private int sourcePort;
  private Date discovered;
  private Date lastSeen;
  private String vendor;
  private String osDetails;
  private String name;
  private String hostname;

  public Device() {}

  public Device(String mac, Long ipAddress, int sourcePort, Date discovered, Date lastSeen, String vendor, String osDetails, String name, String hostname){

    this.mac = mac;
    this.ipAddress = ipAddress;
    this.sourcePort = sourcePort;
    this.discovered = discovered;
    this.lastSeen = lastSeen;
    this.vendor = vendor;
    this.osDetails = osDetails;
    this.name = name;
    this.hostname = hostname;

  }

  public String getMac(){
    return mac;
  }

  public void setMac(String mac){
    this.mac = mac;
  }

  public Long getIpAddress(){
    return ipAddress;
  }

  public void setIpAddress(Long ipAddress){
    this.ipAddress = ipAddress;
  }

  public int getSourcePort(){
    return sourcePort;
  }

  public void setSourcePort(int sourcePort){
    this.sourcePort = sourcePort;
  }

  public Date getDiscovered(){
    return discovered;
  }

  public void setDiscovered( Date discovered){
    this.discovered = discovered;
  }  

  public Date getLastSeen(){
    return lastSeen;
  }

  public void setLastSeen(Date lastSeen){
    this.lastSeen = lastSeen;
  }

  public String getVendor(){
    return vendor;
  }

  public void setVendor(String vendor){
    this.vendor = vendor;
  }

  public String getOsDetails(){
    return osDetails;
  }

  public void setOsDetails(String osDetails){
    this.osDetails = osDetails;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getHostname(){
    return hostname;
  }

  public void setHostname(String hostname){
    this.hostname = hostname;
  }

  public boolean equals(Object o) {

     if (o == this) return true;
     if (!(o instanceof Device)) {
         return false;
     }
     
     Device device = (Device) o;
     return ipAddress.equals(device.ipAddress) && Objects.equals(mac, device.mac);
  }

  public int hashCode(){
    return Objects.hash(mac, ipAddress);
  }

  public String toString(){
    return mac + ":" + ipAddress;
  }

}