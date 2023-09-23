package com.bbva.rbvd;

import com.bbva.rbvd.lib.r401.RBVDR401;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RBVDT40101PETransaction extends AbstractRBVDT40101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDT40101PETransaction.class);
	@Override
	public void execute() {
		RBVDR401 rbvdR401 = this.getServiceLibrary(RBVDR401.class);
		boolean ind = false;
		if(Objects.nonNull(this.getInd())){
			ind = this.getInd();
		}

		String param = rbvdR401.executeHolaMundo(ind);
		this.setFrase(param);

	}

}
