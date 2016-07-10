package com.etherfuse.server.resources;

import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import com.etherfuse.server.db.DeviceDAO;

@Path("/devices")
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
    public List<DeviceDAO> getDevices() {
        return deviceDAO.findAll();
    }

}