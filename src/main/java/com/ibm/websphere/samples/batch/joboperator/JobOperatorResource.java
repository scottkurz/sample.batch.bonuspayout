package com.ibm.websphere.samples.batch.joboperator;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/resource")
public class JobOperatorResource {

    /**
     * The batch JobOperator
     */
    private JobOperator jobOperator;

	@GET
    public String getRequest() {
        return "Batch is in the house";
    }
    
	@GET
    @Path("{jsl}")
    public long start(@PathParam("jsl") String jobXMLName) {

        Properties jobParameters = getJobParameters("jobParameters");

        JobOperator jobOperator = getJobOperator();
        long execId = jobOperator.start(jobXMLName, jobParameters);
        
        return execId;

        /*
        JobInstance jobInstance = jobOperator.getJobInstance(execId);
        JobExecution jobExecution = jobOperator.getJobExecution(execId);

        getResponseWriter().setHttpServletResponse( response )
                           .beginResponse(HttpServletResponse.SC_OK)
                           .println( "start(jobXMLName=" + jobXMLName + ", jobParameters=" + jobParameters + "): Job started!" )
                           .printJobInstance( jobInstance )
                           .printJobExecution( jobExecution )
                           .endResponse();
                           */
    }

	private Properties getJobParameters(String string) {
		return new Properties();
	}
    /**
     * @return the batch JobOperator
     */
    protected JobOperator getJobOperator() {
        return (jobOperator != null) ? jobOperator : (jobOperator = BatchRuntime.getJobOperator());
    }

}
