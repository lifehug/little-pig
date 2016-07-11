package com.etherfuse.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.etherfuse.server.resources.DeviceResource;
import com.etherfuse.server.db.DeviceDAO;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;


public class EtherfuseApplication extends Application<EtherfuseConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new EtherfuseApplication().run(args);
    }

    @Override
    public String getName() {
        return "etherfuse";
    }

    @Override
    public void initialize(Bootstrap<EtherfuseConfiguration> bootstrap) {
        //bootstrap.setName("etherfuse-server");
    }

  @Override
  public void run(EtherfuseConfiguration config, Environment environment) {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");
    
    final DeviceDAO dao = jdbi.onDemand(DeviceDAO.class);
    environment.jersey().register(new DeviceResource(dao));
  }

}