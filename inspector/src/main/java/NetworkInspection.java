package com.etherfuse.inspector;

import java.lang.Process;
import java.sql.Connection;
import java.lang.Runnable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class NetworkInspection{

  public static void main(String args[]){

      if(args.length < 1){
        System.out.println("Neet Network Arguments  ");
        return; 
      }

      String network = args[0];
      int initialDelay = 0;
      int period = 1;
      System.out.println(network);
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

      Runnable task = () -> {
        
        try{
          // discover the network
          Discovery discovery = new DiscoveryBuilder().setNetwork(network).setDiscoveryType(new NmapSNType()).create();
          System.out.println(discovery);

          // save those details to your database
          Connection connection = DiscoveryConnection.getConnection();
         DiscoveryDataHandler ddh = new DiscoveryDataHandler(discovery, connection);
          ddh.save();

        } catch (Exception e){
          e.printStackTrace();
        }

      };

      executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.HOURS);
  }


}