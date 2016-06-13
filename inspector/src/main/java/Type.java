package com.etherfuse.inspector;

public interface Type{
  public Discovery parse(String filename) throws Exception;
  public void execute(String network, String outputFile);
}