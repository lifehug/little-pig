package com.etherfuse.server.resources;

import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import io.dropwizard.hibernate.UnitOfWork;
import com.etherfuse.server.db.ProfileDAO;
import io.dropwizard.hibernate.UnitOfWork;
import com.etherfuse.server.core.Profile;
import io.dropwizard.jersey.params.NonEmptyStringParam;
import io.dropwizard.jersey.params.IntParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private final ProfileDAO profileDAO;
    private final AtomicLong counter;

    public ProfileResource(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
        this.counter = new AtomicLong();
    }

    @GET
    @UnitOfWork
    public List<Profile> getProfiles() {
        return profileDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Profile getDeviceByID(@PathParam("id") IntParam id){
        return profileDAO.findProfileByID(id.get()); 

    }
     
    @POST
    @UnitOfWork
    public Response createProfile(Profile p){
        
        int ids = p.getId();
        String first = p.getFirstname();
        String last = p.getLastname();
        String email = p.getEmail();
        int res = profileDAO.createProfile(ids, first, last, email);

        if( res == 0){
            throw new WebApplicationException(Status.BAD_REQUEST);
        }

        return Response.created(UriBuilder.fromResource(ProfileResource.class).build(ids, first, last, email, res)).entity(new Profile(ids, first, last, email)).build();
    }  

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Response updateProfile(@PathParam("id") IntParam id, Profile p){
        
        int ids = id.get();
        String first = p.getFirstname();
        String last = p.getLastname();
        String email = p.getEmail(); 
        int update = profileDAO.updateProfile(ids, first, last, email);

        if( update == 0 ){
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return Response.created(UriBuilder.fromResource(ProfileResource.class).build(ids, first, last, email, update)).entity(new Profile(ids, first, last, email)).build();

    } 

}