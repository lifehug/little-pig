package com.etherfuse.server.resources;

import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import io.dropwizard.jersey.params.IntParam;

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
    @Path("/owner/{id}")
    @UnitOfWork
    public List<Device> getDeviceByOwnerID(@PathParam("id") NonEmptyStringParam id){
        Optional<String> val = id.get();
        return deviceDAO.findDevicesByProfileId(val.get()); 

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

    @PUT
    @Path("/assign/{id}/{hostname}")
    @UnitOfWork
    public Response updateProfile(@PathParam("id") IntParam id, @PathParam("hostname") NonEmptyStringParam hostname){
        
        Optional<String> val = hostname.get();
        int update = deviceDAO.assign(id.get(), val.get());

        if( update == 0 ){
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return Response.created(UriBuilder.fromResource(ProfileResource.class).build(update)).build();

    } 

    @PUT
    @Path("/unassign/{hostname}")
    @UnitOfWork
    public Response updateProfile( @PathParam("hostname") NonEmptyStringParam hostname){
        
        Optional<String> val = hostname.get();
        int update = deviceDAO.assign(0, val.get());

        if( update == 0 ){
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return Response.created(UriBuilder.fromResource(ProfileResource.class).build(update)).build();

    } 

}