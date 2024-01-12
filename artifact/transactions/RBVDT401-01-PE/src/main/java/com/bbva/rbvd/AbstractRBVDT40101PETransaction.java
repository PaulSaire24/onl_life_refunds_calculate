package com.bbva.rbvd;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.rbvd.dto.lifeinsrc.seguro.PayrollDTO;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractRBVDT40101PETransaction extends AbstractTransaction {

	public AbstractRBVDT40101PETransaction(){
	}


	/**
	 * Return value for input parameter payroll
	 */
	protected List<PayrollDTO> getPayroll(){
		return (List<PayrollDTO>)this.getParameter("payroll");
	}

	/**
	 * Set value for String output parameter Location
	 */
	protected void setLocation(final String field){
		this.addParameter("Location", field);
	}
}
