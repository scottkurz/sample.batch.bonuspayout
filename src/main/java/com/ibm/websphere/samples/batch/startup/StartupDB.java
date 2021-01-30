package com.ibm.websphere.samples.batch.startup;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class StartupDB {

	static private boolean dbCreated = false;
	static private EntityManager em;
	
	public static void setupDB() {
		if (!dbCreated) {

			Properties props = new Properties();
			props.setProperty("eclipselink.ddl-generation","drop-and-create-tables");
			props.setProperty("eclipselink.ddl-generation.output-mode","both");

			List<String> entities = Arrays.asList("com.ibm.websphere.samples.batch.beans.AccountDataObject", "com.ibm.websphere.samples.batch.beans.AccountID");
			org.eclipse.persistence.jpa.PersistenceProvider jpa = 
					new org.eclipse.persistence.jpa.PersistenceProvider();
			System.out.println("SKSK: jpa provider =  " + jpa);
			
			StartupPersistenceUnitInfo info = 
					new StartupPersistenceUnitInfo("dynamic-unit", entities , props);
			
			EntityManagerFactory emf = jpa.createContainerEntityManagerFactory(info, props);

			em = emf.createEntityManager();
			dbCreated = true;
		}
	}
}
