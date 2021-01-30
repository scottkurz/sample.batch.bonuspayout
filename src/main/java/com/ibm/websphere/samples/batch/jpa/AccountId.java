package com.ibm.websphere.samples.batch.jpa;

import java.io.Serializable;

public class AccountId implements Serializable {

    private static final long serialVersionUID = -6581786309057723712L;

    public AccountId() {
		super();
	}

    private int accountNumber;

    private int instanceId;

    public int getInstanceId() {
        return instanceId;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public AccountId(int accountNumber, int instanceId) {
        this.accountNumber = accountNumber;
        this.instanceId = instanceId;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        AccountId accountId = (AccountId) object;
        return accountNumber == accountId.accountNumber &&
                instanceId == accountId.instanceId;
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), accountNumber, instanceId);
    }


}