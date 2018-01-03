package com.btn.bolt.application;

import com.btn.bolt.data.Controller;
import com.btn.bolt.data.user.User;
import com.btn.bolt.data.user.UserController;
import com.btn.bolt.data.user.UserDAO;
import com.btn.bolt.resources.*;
import com.codahale.metrics.health.HealthCheck;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.HashMap;

public class BoltApplication extends io.dropwizard.Application<BoltConfiguration> {

    public static void main(String[] args) throws Exception {
        new BoltApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<BoltConfiguration> bootstrap) {
        super.initialize(bootstrap);

        bootstrap.getObjectMapper().registerModule(new JodaModule());
        bootstrap.getObjectMapper().setDateFormat(new ISO8601DateFormat());
        bootstrap.getObjectMapper().getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);

        bootstrap.getObjectMapper().enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        bootstrap.getObjectMapper().enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

    }

    @Override
    public void run(BoltConfiguration config, Environment environment) throws Exception {

        //Registering a default health check for the app
        environment.healthChecks().register("status", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");

        registerResources(environment, jdbi);
    }


    private void registerResources(Environment environment, DBI jdbi) {
        UserController userController = new UserController(jdbi.onDemand(UserDAO.class));

        registerResource(environment, new UserResource(userController));
    }

    private void registerResource(Environment environment, Resource resource) {
        environment.jersey().register(resource);
    }
}
