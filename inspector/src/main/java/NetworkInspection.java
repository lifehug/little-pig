package com.etherfuse.inspector;

import java.lang.Process;
import java.sql.Connection;

public class NetworkInspection{

  public static void main(String args[]){

    try {

      if(args.length < 1) throw new Exception("Network argument must be provided : " + args[0]);

      String network = args[0];
      System.out.println(network);

      // discover the network
      Discovery discovery = new DiscoveryBuilder().setNetwork(network).setDiscoveryType(new NmapSNType()).create();
      System.out.println(discovery);

      // save those details to your database
      Connection connection = DiscoveryConnection.getConnection();
      DiscoveryDataHandler ddh = new DiscoveryDataHandler(discovery, connection);


    } catch (Exception e){
      e.printStackTrace();
    }
  }


}