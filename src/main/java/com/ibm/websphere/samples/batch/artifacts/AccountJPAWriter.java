package com.ibm.websphere.samples.batch.artifacts;

/*
 * Copyright 2019 International Business Machines Corp.
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


import java.util.List;

import javax.annotation.Resource;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ibm.websphere.samples.batch.beans.AccountDataObject;

@Dependent
@Component
public class AccountJPAWriter extends AbstractItemWriter {

	@PersistenceContext(unitName = "BonusPayoutPU")
	EntityManager entityManager;

    @Inject
    private JobContext jobCtx;

//    @Resource
//    private PlatformTransactionManager transactionManager;

	/**
	 * Write a list of objects into a database using JPA
	 */
	public void writeItems(List<java.lang.Object> arg0) {
		for (int i = 0; i < arg0.size(); i++) {
			AccountDataObject ado = (AccountDataObject)arg0.get(i);
            ado.setInstanceId(jobCtx.getInstanceId());
			//entityManager.persist(ado);
		}

    //lookupTran();
    }

	/*
   private void lookupTran() {
       try {
    	   TransactionDefinition def = new DefaultTransactionDefinition();
           TransactionStatus status = transactionManager.getTransaction(def);
    System.out.println("Transaction status = "+status);
       } catch (Exception e) {
              System.out.println("SKSK: caught exception and printing stack trace for " + e );
                e.printStackTrace();
                throw new RuntimeException(e);
       }
    }
    */


}

