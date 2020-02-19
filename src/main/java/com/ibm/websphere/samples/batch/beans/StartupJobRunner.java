/*
 * Copyright 2020 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.websphere.samples.batch.beans;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.ibm.websphere.samples.batch.util.BonusPayoutConstants;

@Singleton
@Startup
@RunAs("JOBSTARTER")
public class StartupJobRunner {

    @PostConstruct
    public void initialize() {
        logger.warning("\n\nRunning batch job from the ControllerBean startup EJB\n\n");
        try {
            System.out.println("SKSK: 1");
            JobOperator jobOperator = BatchRuntime.getJobOperator();
            jobOperator.start("BonusPayoutJob", null);
            System.out.println("SKSK: 2");
        } catch (Exception e) {
            System.out.println("SKSK: 3");
            logger.info("Something's wrong with StartupJobRunner!");
            e.printStackTrace();
        }
    }

     static { System.out.println("SKSK: Running with patch developed 2-18-20  of class StartupJobRunner"); }
     private final static Logger logger = Logger.getLogger("test");

    // @Schedule(hour = "*", minute = "*/1", persistent = false)
    // public void beginJob() {

    //     System.out.println("TKTK: 0");
    //     logger.warning("\n\nRunning batch job from the StartupJobRunner\n\n");

    //     try {
    //         System.out.println("TKTK: 1");
    //         JobOperator jobOperator = BatchRuntime.getJobOperator();
    //         jobOperator.start("BonusPayoutJob", null);
    //         System.out.println("TKTK: 2");
    //     } catch (Exception e) {
    //         System.out.println("TKTK: 3");
    //         logger.info("Something's wrong with StartupJobRunner!");
    //         e.printStackTrace();
    //     }
    // }

}

