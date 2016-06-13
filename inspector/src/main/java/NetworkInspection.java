package com.etherfuse.inspector;

import java.lang.Process;

public class NetworkInspection{

  public static void main(String args[]){

    try {

      // need to check that nmap is installed on the local machine

      // call nmap for the discovery and pipe it to a filename
      String filename = "output.txt";
      String network = "198.162.1.0/24";

      Process p = Runtime.getRuntime().exec("nmap -sP " + network + " > " + filename);
      p.waitFor();
      Discovery discovery = new DiscoveryCreator(filename).create();

      // save the details to the database
    } catch (Exception e){
      e.printStackTrace();
    }
  }


}