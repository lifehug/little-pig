package com.etherfuse.server.core;

import java.sql.Timestamp;
import java.util.Objects;

public class Device{
  
  private String mac;
  private Long ipAddress;
  private int sourcePort;
  private Timestamp discovered;
  private Timestamp lastSeen;
  private String vendor;
  private String osDetails;
  private String name;
  private int owner;
  private String hostname;

  public Device() {}

  public Device(String mac, Long ipAddress, int sourcePort, Timestamp discovered, Timestamp lastSeen, String vendor, String osDetails, String name, int owner, String hostname){

    this.mac = mac;
    this.ipAddress = ipAddress;
    this.sourcePort = sourcePort;
    this.discovered = discovered;
    this.lastSeen = lastSeen;
    this.vendor = vendor;
    this.osDetails = osDetails;
    this.name = name;
    this.owner = owner;
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

  public Timestamp getDiscovered(){
    return discovered;
  }

  public void setDiscovered( Timestamp discovered){
    this.discovered = discovered;
  }  

  public Timestamp getLastSeen(){
    return lastSeen;
  }

  public void setLastSeen(Timestamp lastSeen){
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

  public int getOwner(){
    return owner;
  }

  public void setOwner(int owner){
    this.owner = owner;
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