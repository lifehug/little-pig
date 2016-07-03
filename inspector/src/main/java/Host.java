package com.etherfuse.inspector;

import com.google.common.net.InetAddresses;
import java.net.InetAddress;

public class Host{
  
  InetAddress addr;
  String mac; 
  String vendor;
  String hostname;

  public Host(String ip, String mac, String vendor, String hostname){
    this.addr = InetAddresses.forString(ip);
    this.mac = mac;
    this.vendor = vendor;
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

  public String getVendor(){
    return (vendor == null ? "" : vendor);
  }

  public String getHostname(){
    return (hostname == null ? "" : hostname);
  }

  public String toString(){
    String result = (addr == null ? "" : "ip: " + addr.toString().replace("/", "") + " ");
    result += (mac == null ?  "" : " mac: " + getMAC() + " ");
    result += (vendor == null ? "" : " vendor: " + getVendor() + " ");
    result += (hostname == null ? "" : " interface :" + getHostname() + " ");
    result += "\n";
    return result;
  } 

}