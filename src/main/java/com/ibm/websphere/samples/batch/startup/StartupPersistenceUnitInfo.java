package com.ibm.websphere.samples.batch.startup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.eclipse.persistence.jpa.PersistenceProvider;

import com.ibm.websphere.samples.batch.util.BonusPayoutUtils;

public class StartupPersistenceUnitInfo implements PersistenceUnitInfo {
    
    public static String JPA_VERSION = "2.2";
    private String persistenceUnitName;
    private PersistenceUnitTransactionType transactionType
      = PersistenceUnitTransactionType.JTA;
    
    private List<String> managedClassNames;
    private List<String> mappingFileNames = Collections.<String> emptyList();
    private Properties properties;
    
    public StartupPersistenceUnitInfo(
      String persistenceUnitName, List<String> managedClassNames, Properties properties) {
        this.persistenceUnitName = persistenceUnitName;
        this.managedClassNames = managedClassNames;
        this.properties = properties;
    }

	@Override
	public String getPersistenceUnitName() {
		// TODO Auto-generated method stub
		return persistenceUnitName;
	}

	@Override
	public String getPersistenceProviderClassName() {
        return PersistenceProvider.class.getName();
    }

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		// TODO Auto-generated method stub
		return transactionType;
	}

	@Override
	public DataSource getJtaDataSource() {
		try {
			return BonusPayoutUtils.lookupDataSource("jdbc/batch");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public DataSource getNonJtaDataSource() {
		try {
			return BonusPayoutUtils.lookupDataSource("jdbc/batch");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> getMappingFileNames() {
		// TODO Auto-generated method stub
		return mappingFileNames;
	}

	@Override
	public List<URL> getJarFileUrls() {
        return Collections.emptyList();
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		URL retVal = null;
		try {
			retVal = new URL("http", "dummy.com", "a.out");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return retVal;
	}

	@Override
	public List<String> getManagedClassNames() {
		return managedClassNames;
	}

	@Override
	public boolean excludeUnlistedClasses() {
		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
        return SharedCacheMode.NONE;
	}

	@Override
	public ValidationMode getValidationMode() {
        return ValidationMode.NONE;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
        return JPA_VERSION;
	}

	@Override
	public ClassLoader getClassLoader() {
		return StartupPersistenceUnitInfo.class.getClassLoader();
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {
		// NO-OP
	}

	@Override
	public ClassLoader getNewTempClassLoader() {
        throw new UnsupportedOperationException();
    }

}