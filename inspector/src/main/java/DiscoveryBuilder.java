package com.etherfuse.inspector;

import java.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

// could get rid of file by piping the output to string
public class DiscoveryBuilder{

  Type discoveryType;
  String network;
  String filename;

  public DiscoveryBuilder setDiscoveryType(Type discoveryType){
    this.discoveryType = discoveryType;
    return this;
  }

  public DiscoveryBuilder setNetwork(String network){
    this.network = network;
    return this;
  }

  public DiscoveryBuilder setFilename(String filename){
    this.filename = filename;
    return this;
  }

  public void run() throws Exception{

    discoveryType.execute(network, filename);

  }

  public Discovery create() throws Exception{
    run();
    return discoveryType.parse(filename);

  }


}