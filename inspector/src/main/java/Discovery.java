package com.etherfuse.inspector;

import java.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class Discovery{
  String discovery;
  List<Host> hosts;

  public Discovery(String time, List<Host> hosts){
    this.discovery = time;
    this.hosts = hosts;
  }

  public LocalDateTime getDate(){
    return null;
  }

  public List<Host> getHosts(){
    return hosts;
  }

}