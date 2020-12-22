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

import javax.annotation.security.RunAs;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
@RunAs("JOBSTARTER")
public class StartupJobRunner {

    //@Schedule(hour = "*", minute = "*", second = "*/20", persistent = false)
    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void initialize() {
        System.out.println("\n\nRunning batch job from the ControllerBean startup EJB.\nSee batch job logs for results.\n\n");
        try {
            JobOperator jobOperator = BatchRuntime.getJobOperator();
            jobOperator.start("BonusPayoutJob", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}