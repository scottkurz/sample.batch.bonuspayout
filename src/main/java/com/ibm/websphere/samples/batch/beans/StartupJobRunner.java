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

import java.util.Properties;

import javax.annotation.security.RunAs;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
@RunAs("JOBSTARTER")
public class StartupJobRunner {

	static private boolean dbCreated = false;

	@Inject
	@ConfigProperty(name = "autoStartBatch")
	private boolean autoStartBatch;

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

	//@Schedule(hour = "*", minute = "*", second = "*/20", persistent = false)
	//@Schedule(hour = "*", minute = "*/1", persistent = false)
	@Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
	public void initialize() {
		if (autoStartBatch) {
			try {
				System.out.println("\n\nRunning batch job from the StartupJobRunner EJB.\nSee batch job logs for results.\n\n");
				JobOperator jobOperator = BatchRuntime.getJobOperator();
				jobOperator.start("BonusPayoutJob", null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
