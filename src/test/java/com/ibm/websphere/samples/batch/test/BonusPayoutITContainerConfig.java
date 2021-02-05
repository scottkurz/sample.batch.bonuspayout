// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package com.ibm.websphere.samples.batch.test;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class BonusPayoutITContainerConfig implements SharedContainerConfiguration {

    private static Network network = Network.newNetwork();
       
	@Container
	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>()
					.withNetworkAliases("testpostgres")
				    .withCommand("postgres -c max_prepared_transactions=30")
					.withDatabaseName("testdb");
	

    @Container
    public static ApplicationContainer inventory = new ApplicationContainer()
                    .withAppContextRoot("/batch-bonuspayout-application")
                    .withExposedPorts(9080)
 //                   .waitingFor(
 //                           Wait.forLogMessage(".*CWWKF0011I.*\\n", 1)
 //                       )
//                    .withExposedPorts(9080, 9443)
                    .withEnv("POSTGRES_HOSTNAME", "testpostgres")
                    .withEnv("POSTGRES_PORT", "5432")
                    .withReadinessPath("/health/ready")
                    .dependsOn(postgres)
                    .withNetwork(network);
}