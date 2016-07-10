package com.etherfuse.server.core;

import java.sql.Date

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
    this.lastSeen lastSeen;
    this.vendor = vendor;
    this.osDetails = osDetails;
    this.name = name;
    this.hostname = hostname;

  }

  public String getMac(){
    return mac;
  }

  public setMac(String mac){
    this.mac = mac;
  }

  public Long getIpAddress(){
    return ipAddress;
  }

  public setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
  }

  public int getSourcePort(){
    return sourcePort;
  }

  public setSourcePort(int sourcePort){
    this.sourcePort = sourcePort;
  }

  public Date getDiscovered(){
    return discovered;
  }

  public setDiscovered( Date discovered){
    this.discovered = discovered;
  }  

  public Date getLastSeen(){
    return lastSeen;
  }

  public setLastSeen(Date lastSeen){
    this.lastSeen = lastSeen;
  }

  public String getVendor(){
    return vendor;
  }

  public setVendor(String vendor){
    this.vendor = vendor;
  }

  public String getOsDetails(){
    return osDetails;
  }

  public setOsDetails(String osDetails){
    this.osDetails = osDetails;
  }

  public String getName(){
    return name;
  }

  public setName(String name){
    this.name = name;
  }

  public String getHostname(){
    return hostname;
  }

  public setHostname(String hostname){
    this.hostname = hostname;
  }

}