package com.bbva.rbvd;

import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractRBVDT40101PETransaction extends AbstractTransaction {

	public AbstractRBVDT40101PETransaction(){
	}


	/**
	 * Return value for input parameter ind
	 */
	protected Boolean getInd(){
		return (Boolean)this.getParameter("ind");
	}

	/**
	 * Set value for String output parameter frase
	 */
	protected void setFrase(final String field){
		this.addParameter("frase", field);
	}
}
