package dev.appsody.starter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import com.ibm.websphere.samples.batch.beans.StartupJobRunner;

@Path("/resource")
@RequestScoped
public class StarterResource {

    @Inject
    StartupJobRunner runner;

    @GET
    public String getRequest() {

        runner.init();

        return "Scheduled jobs";
    }
}
