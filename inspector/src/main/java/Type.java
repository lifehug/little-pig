package com.etherfuse.inspector;

public interface Type{
  public Discovery parse() throws Exception;
  public void execute(String network) throws Exception;
}