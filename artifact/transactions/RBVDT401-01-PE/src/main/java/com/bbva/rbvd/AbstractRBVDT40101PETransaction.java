package com.bbva.rbvd;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.rbvd.dto.insuranceroyal.refund.ParticipantDTO;
import com.bbva.rbvd.dto.insuranceroyal.refund.RefundCalculateDTO;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractRBVDT40101PETransaction extends AbstractTransaction {

	public AbstractRBVDT40101PETransaction(){
	}


	/**
	 * Return value for input parameter participants
	 */
	protected List<ParticipantDTO> getParticipants(){
		return (List<ParticipantDTO>)this.getParameter("participants");
	}

	/**
	 * Set value for List<RefundCalculateDTO> output parameter data
	 */
	protected void setData(final List<RefundCalculateDTO> field){
		this.addParameter("data", field);
	}
}
