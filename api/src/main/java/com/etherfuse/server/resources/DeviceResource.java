package com.etherfuse.server.resources;

import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import io.dropwizard.hibernate.UnitOfWork;
import com.etherfuse.server.db.DeviceDAO;
import io.dropwizard.hibernate.UnitOfWork;
import com.etherfuse.server.core.Device;
import io.dropwizard.jersey.params.NonEmptyStringParam;

@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResource {

    private final DeviceDAO deviceDAO;
    private final AtomicLong counter;

    public DeviceResource(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
        this.counter = new AtomicLong();
    }

    @GET
    @UnitOfWork
    public List<Device> getDevices() {
        return deviceDAO.findAll();
    }

    @GET
    @Path("/hostname/{id}")
    @UnitOfWork
    public Device getDeviceByHostname(@PathParam("id") NonEmptyStringParam id){
        Optional<String> val = id.get();
        return deviceDAO.findNameByHostname(val.get()); 

    }    

    @GET
    @Path("/mac/{id}")
    @UnitOfWork
    public Device getDeviceByMAC(@PathParam("id") NonEmptyStringParam id){
        Optional<String> val = id.get();
        return deviceDAO.findNameByMAC(val.get());
    }  

    @GET
    @Path("/ip/{id}")
    @UnitOfWork
    public Device getDeviceByIPAddress(@PathParam("id") NonEmptyStringParam id){
        Optional<String> val = id.get();
        return deviceDAO.findNameByIPAddress(val.get());
    } 

}