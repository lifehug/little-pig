package com.etherfuse.inspector;

import java.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class DiscoveryBuilder{

  Type discoveryType;
  String network;

  public DiscoveryBuilder setDiscoveryType(Type discoveryType){
    this.discoveryType = discoveryType;
    return this;
  }

  public DiscoveryBuilder setNetwork(String network){
    this.network = network;
    return this;
  }

  public DiscoveryBuilder run() throws Exception{
    discoveryType.execute(network);
    return this;
  }

  public Discovery create() throws Exception{

    return run().discoveryType.parse();

  }


}