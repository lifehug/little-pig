package com.etherfuse.server.core;

import java.sql.Timestamp;
import java.util.Objects;

public class Event{
  
  private int id;
  private Long ipSrc;
  private Long ipDst; 
  private String firstname; 
  private String lastname;
  private String device;
  private Timestamp timestamp;
  private Long sid;
  private Long cid;

  public Event() {}

  public Event(int id, Long ipSrc, Long ipDst, String firstname, String lastname, String device, Timestamp timestamp, Long sid, Long cid){

    this.id = id;
    this.ipSrc = ipSrc;
    this.ipDst = ipDst; 
    this.firstname = firstname;
    this.lastname = lastname;
    this.device = device;
    this.timestamp = timestamp;
    this.sid = sid;
    this.cid = cid;

  }
  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public Long getIpSrc(){
    return ipSrc;
  }

  public void setIpSrc(Long ipSrc){
    this.ipSrc = ipSrc;
  }

  public Long getIpDst(){
    return ipSrc;
  }

  public void setIpDst(Long ipDst){
    this.ipDst = ipDst;
  }

  public String getFirstname(){
    return firstname;
  }

  public void setFirstname(String firstname){
    this.firstname = firstname;
  }

  public String getLastname(){
    return lastname;
  }

  public void setLastname(String lastname){
    this.lastname = lastname;
  }

  public String getDevice(){
    return device;
  }

  public void setDevice(String device){
    this.device = device;
  }

  public Timestamp getTimestamp(){
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp){
    this.timestamp = timestamp;
  }

  public Long getSid(){
    return sid;
  }

  public void setSidt(Long sid){
    this.sid = sid;
  }

  public Long getCid(){
    return cid;
  }

  public void setCid(Long cid){
    this.cid = cid;
  }

  public boolean equals(Object o) {

     if (o == this) return true;
     if (!(o instanceof Event)) {
         return false;
     }
     
     Event device = (Event) o;
     return cid.equals(device.cid) && sid.equals(device.sid);
  }

  public int hashCode(){
    return Objects.hash(sid, cid);
  }

  public String toString(){
    return cid + ":" + sid;
  }

}