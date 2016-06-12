package com.etherfuse.inspector;

import com.google.common.net.InetAddresses;
import java.net.InetAddress;

public class Host{
  
  InetAddress addr;
  String mac; 
  String os;

  public Host(String ip, String mac, String os){
    this.addr = InetAddresses.forString(ip);
    this.mac = mac;
    this.os = os;
  }

  public int getIPAddress(){
    return InetAddresses.coerceToInteger(addr);
  }

  public String getMAC(){
    return mac;
  }

  public String getOS(){
    return os;
  }

}