package com.etherfuse.inspector;

import java.lang.Process;

public class NetworkInspection{

  public static void main(String args[]){

    try {

      if(args.length <= 1) throw new Exception("Network argument must be provided");
      // may be able to pull local network automatically from uname -a or something
      // call nmap for the discovery and pipe it to a filename
      String filename = "output.txt";
      String network = args[0];
      Discovery discovery = new DiscoveryBuilder().setNetwork(network).setFilename(filename).setDiscoveryType(new NmapSPType()).create();
      //System.out.println(discovery);

      Connection connection = DiscoveryConnection.getConnection();
      DiscoveryDataHandler ddh = new DiscoveryDataHandler(discovery, connection);

      // save the details to the database
    } catch (Exception e){
      e.printStackTrace();
    }
  }


}