package com.ibm.websphere.samples.batch.joboperator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/resource")
public class JobOperatorResource {

    @GET
    public String getRequest() {
        return "Batch is in the house";
    }
}
