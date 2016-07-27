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
import io.dropwizard.hibernate.UnitOfWork;
import com.etherfuse.server.db.EventDAO;
import io.dropwizard.hibernate.UnitOfWork;
import com.etherfuse.server.core.Event;
import io.dropwizard.jersey.params.NonEmptyStringParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.jersey.params.IntParam;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    private final EventDAO eventDAO;

    public EventResource(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @GET
    @UnitOfWork
    public List<Event> getEvents() {
        return eventDAO.findAll();
    }

    @GET
    @Path("/{sid}/{cid}")
    @UnitOfWork
    public Event getEventByID(@PathParam("sid") LongParam sid, @PathParam("cid") LongParam cid ){
        return eventDAO.findEventByID(sid.get(), sid.get()); 
    }

    @GET
    @Path("/device/{hostname}")
    @UnitOfWork
    public List<Event> getEventsByDevice(@PathParam("hostname") NonEmptyStringParam hostname){

        Optional<String> val = hostname.get();
        return eventDAO.findEventsByDevice(val.get());

    }           

    @GET
    @Path("/profile/{id}")
    @UnitOfWork
    public List<Event> getEventsByProfile(@PathParam("id") IntParam id){
        return eventDAO.findEventsByProfile(id.get()); 
    }

}