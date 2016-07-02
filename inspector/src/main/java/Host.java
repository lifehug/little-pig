package com.etherfuse.inspector;

import com.google.common.net.InetAddresses;
import java.net.InetAddress;

public class Host{
  
  InetAddress addr;
  String mac; 
  String os;
  String hostname;

  public Host(String ip, String mac, String os, String hostname){
    this.addr = InetAddresses.forString(ip);
    this.mac = mac;
    this.os = os;
    this.hostname = hostname; 
  }

  public Host(String ip, String hostname){
    this.addr = InetAddresses.forString(ip);
    this.hostname = hostname; 
  }

  public int getIPAddress(){
    return (addr == null ? 0 : InetAddresses.coerceToInteger(addr));
  }

  public String getMAC(){
    return (mac == null ? "" : mac);
  }

  public String getOS(){
    return (os == null ? "" : os);
  }

  public String getHostname(){
    return (hostname == null ? "" : hostname);
  }

  public String toString(){
    String result = (addr == null ? "" : "ip: " + addr.toString().replace("/", "") + " ");
    result += (mac == null ?  "" : " mac: " + getMAC() + " ");
    result += (os == null ? "" : " os: " + getOS() + " ");
    result += (hostname == null ? "" : " interface :" + getHostname() + " ");
    result += "\n";
    return result;
  } 

}