package com.etherfuse.inspector;

import java.lang.Integer;
import java.util.Objects;

public class Host{
  
  Long addr;
  String mac; 
  String vendor;
  String hostname;

  public Host(String ip, String mac, String vendor, String hostname){
    this.addr = ipToLong(ip);
    this.mac = mac;
    this.vendor = vendor;
    this.hostname = hostname; 
  }

  public Host(String ip, String hostname){
    this.addr = ipToLong(ip);
    this.hostname = hostname; 
  }

  public Long getIPAddress(){
    return (addr == null ? 0 : addr);
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

  public long ipToLong(String ipAddress) {

    String[] ipAddressInArray = ipAddress.split("\\.");

    long result = 0;
    for (int i = 0; i < ipAddressInArray.length; i++) {

      int power = 3 - i;
      int ip = Integer.parseInt(ipAddressInArray[i]);
      result += ip * Math.pow(256, power);

    }

    return result;
  }

  public boolean equals(Object o) {

     if (o == this) return true;
     if (!(o instanceof Host)) {
         return false;
     }
     
     Host host = (Host) o;
     return addr.equals(host.addr) && Objects.equals(mac, host.mac);
  }

  public int hashCode(){
    return Objects.hash(addr, mac, vendor, hostname);
  }

  public String toString(){
    String result = (addr == null ? "" : "ip: " + addr + " ");
    result += (mac == null ?  "" : " mac: " + getMAC() + " ");
    result += (vendor == null ? "" : " vendor: " + getVendor() + " ");
    result += (hostname == null ? "" : " interface :" + getHostname() + " ");
    result += "\n";
    return result;
  } 

}