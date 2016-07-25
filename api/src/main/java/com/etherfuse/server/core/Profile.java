package com.etherfuse.server.core;

import java.util.Objects;

public class Profile{

  private int id;
  private String firstname;
  private String lastname;
  private String email;
  
  public Profile() {}

  public Profile(int id, String firstname, String lastname, String email){
    
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;

  }

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
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

  public String getEmail(){
    return email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public boolean equals(Object o) {

     if (o == this) return true;
     if (!(o instanceof Profile)) {
         return false;
     }
     Profile profile = (Profile) o;
     return id == profile.id &&
             Objects.equals(firstname, profile.firstname) &&
             Objects.equals(lastname, profile.lastname);
  }

  public int hashCode(){
    return Objects.hash(id, firstname, lastname);
  }

  public String toString(){
    return id + ":" + firstname + ":" + lastname;
  }
  
}