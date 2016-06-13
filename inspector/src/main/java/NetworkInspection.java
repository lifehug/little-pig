package com.etherfuse.inspector;

import java.lang.Process;

public class NetworkInspection{

  public static void main(String args[]){

    try {

      // need to check that nmap is installed on the local machine

      // call nmap for the discovery and pipe it to a filename
      String filename = "output.txt";
      String network = "198.162.1.0/24";
      Discovery discovery = new DiscoveryBuilder().setNetwork(network).setFilename(filename).setDiscoveryType(new NmapSPType()).create();

      // save the details to the database
    } catch (Exception e){
      e.printStackTrace();
    }
  }


}