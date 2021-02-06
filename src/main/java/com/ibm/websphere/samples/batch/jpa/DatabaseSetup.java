package com.ibm.websphere.samples.batch.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.jpa.PersistenceProvider;

import com.ibm.websphere.samples.batch.util.BonusPayoutUtils;

public class DatabaseSetup {

	static private boolean dbCreated = false;
	
	public static void setupDB() {

		if (!dbCreated) {

			System.out.println("SKSK: yeah time to setup the DB   ");

			Properties props = new Properties();
			props.setProperty("eclipselink.ddl-generation","drop-and-create-tables");
			props.setProperty("eclipselink.ddl-generation.output-mode","both");
			props.setProperty("javax.persistence.schema-generation.create-database-schemas", "true");

			List<String> entities = Arrays.asList("com.ibm.websphere.samples.batch.jpa.AccountDataObject",
					"com.ibm.websphere.samples.batch.jpa.AccountID");
			PersistenceProvider jpa = new PersistenceProvider();
			
			DataSource jtaDS = null;
			DataSource nonjtaDS = null;
			try {
				jtaDS = BonusPayoutUtils.lookupDataSource("jdbc/batch");
				nonjtaDS = BonusPayoutUtils.lookupDataSource("jdbc/batchNonJta");
			} catch (NamingException e) {
				throw new RuntimeException(e);
			}

			BatchPersistenceUnitInfo info = 
					new BatchPersistenceUnitInfo("dynamic-unit", jtaDS, entities , props);
			info.setNonjtaDS(nonjtaDS);
			
			System.out.println("SKSK: jpa provider =  " + jpa + ", PUInfo = " + info);
			EntityManagerFactory emf = jpa.createContainerEntityManagerFactory(info, props);

			// Force table create
			EntityManager em = emf.createEntityManager();
			em.close();

			dbCreated = true;
		}
	}
}
