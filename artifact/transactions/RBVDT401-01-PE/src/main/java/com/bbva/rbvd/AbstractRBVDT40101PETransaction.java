package com.bbva.rbvd;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.rbvd.dto.lifeinsrc.seguro.BusinessAgentDTO;
import com.bbva.rbvd.dto.lifeinsrc.seguro.ContactDetailsDTO;
import com.bbva.rbvd.dto.lifeinsrc.seguro.DataDTO;
import com.bbva.rbvd.dto.lifeinsrc.seguro.EmployessDTO;
import com.bbva.rbvd.dto.lifeinsrc.seguro.ProductDTO;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractRBVDT40101PETransaction extends AbstractTransaction {

	public AbstractRBVDT40101PETransaction(){
	}


	/**
	 * Return value for input parameter employess
	 */
	protected EmployessDTO getEmployess(){
		return (EmployessDTO)this.getParameter("employess");
	}

	/**
	 * Return value for input parameter product
	 */
	protected ProductDTO getProduct(){
		return (ProductDTO)this.getParameter("product");
	}

	/**
	 * Return value for input parameter contactDetails
	 */
	protected ContactDetailsDTO getContactdetails(){
		return (ContactDetailsDTO)this.getParameter("contactDetails");
	}

	/**
	 * Return value for input parameter businessAgent
	 */
	protected BusinessAgentDTO getBusinessagent(){
		return (BusinessAgentDTO)this.getParameter("businessAgent");
	}

	/**
	 * Set value for DataDTO output parameter data
	 */
	protected void setData(final DataDTO field){
		this.addParameter("data", field);
	}
}
