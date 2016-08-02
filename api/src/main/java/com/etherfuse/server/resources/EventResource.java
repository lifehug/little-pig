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
import java.lang.Integer;
import java.sql.Timestamp;
import java.util.Date;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    private final EventDAO eventDAO;

    public EventResource(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @GET
    @UnitOfWork
    public List<Event> getEvents(@QueryParam("start") Optional<Integer> start, @QueryParam("end") Optional<Integer> end, @QueryParam("timeStart") Optional<Long> timeStart, @QueryParam("timeEnd") Optional<Long> timeEnd) {

        Timestamp begining = null;
        Timestamp ending = null; 

        if(!timeStart.isPresent()){
            begining = Timestamp.valueOf("1900-01-01 00:00:00.000");
        } else{
            begining = new Timestamp(timeStart.get());
        }

        if(!timeEnd.isPresent()){
            Date date = new Date();
            ending = new Timestamp(date.getTime());
        } else {
            ending = new Timestamp(timeEnd.get());
        }


        if(end.isPresent()){
            return eventDAO.findAllWithPaging(start.or(0), end.get(), begining, ending); 
        } else{
            return eventDAO.findAll(begining, ending);
        }

    }

    @GET
    @Path("/{sid}/{cid}")
    @UnitOfWork
    public Event getEventByID(@PathParam("sid") LongParam sid, @PathParam("cid") LongParam cid, @QueryParam("start") Optional<Integer> start, @QueryParam("end") Optional<Integer> end){

        if(end.isPresent()){
            return eventDAO.findEventByIDWithPaging(sid.get(), sid.get(), start.or(0), end.get());         
        } else{
            return eventDAO.findEventByID(sid.get(), sid.get());
        }

    }

    @GET
    @Path("/device/{hostname}")
    @UnitOfWork
    public List<Event> getEventsByDevice(@PathParam("hostname") NonEmptyStringParam hostname, @QueryParam("start") Optional<Integer> start, @QueryParam("end") Optional<Integer> end, @QueryParam("timeStart") Optional<Long> timeStart, @QueryParam("timeEnd") Optional<Long> timeEnd){

        Timestamp begining = null;
        Timestamp ending = null; 

        if(!timeStart.isPresent()){
            begining = Timestamp.valueOf("1900-01-01 00:00:00.000");
        } else{
            begining = new Timestamp(timeStart.get());
        }

        if(!timeEnd.isPresent()){
            Date date = new Date();
            ending = new Timestamp(date.getTime());
        } else {
            ending = new Timestamp(timeEnd.get());
        }

        Optional<String> val = hostname.get();
        if(end.isPresent()){
            return eventDAO.findEventsByDeviceWithPaging(val.get(), start.or(0), end.get(), begining, ending); 
        } else{
            return eventDAO.findEventsByDevice(val.get(), begining, ending);
        }

    }           

    @GET
    @Path("/profile/{id}")
    @UnitOfWork
    public List<Event> getEventsByProfile(@PathParam("id") IntParam id, @QueryParam("start") Optional<Integer> start, @QueryParam("end") Optional<Integer> end, @QueryParam("timeStart") Optional<Long> timeStart, @QueryParam("timeEnd") Optional<Long> timeEnd){
        
        Timestamp begining = null;
        Timestamp ending = null; 

        if(!timeStart.isPresent()){
            begining = Timestamp.valueOf("1900-01-01 00:00:00.000");
        } else{
            begining = new Timestamp(timeStart.get());
        }

        if(!timeEnd.isPresent()){
            Date date = new Date();
            ending = new Timestamp(date.getTime());
        } else {
            ending = new Timestamp(timeEnd.get());
        }
        
        if(start.isPresent() && end.isPresent()){
            return eventDAO.findEventsByProfileWithPaging(id.get(), start.or(0), end.get(), begining, ending);
        } else{
            return eventDAO.findEventsByProfile(id.get(), begining, ending); 
        }

    }

}