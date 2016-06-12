package com.etherfuse.inspector;

public class NetworkInspection{

  public static void main(String args[]){

    // call nmap for the discovery and pipe it to a filename
    String filename = "output.txt";
    String network = "198.162.1.0/24"

    p = Runtime.getRuntime().exec("nmap -sP " + network + " > " + filename);
    p.waitFor();
    Discovery discovery = new DiscoveryCreator(filename).create();

    // save the details to the database

  }


}