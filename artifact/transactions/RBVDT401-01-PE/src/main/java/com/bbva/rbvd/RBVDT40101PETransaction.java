package com.bbva.rbvd;

import com.bbva.rbvd.lib.r401.RBVDR401;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transacción de cálculo de la información de devolución y número de plazos de contratación.
 *
 */
public class RBVDT40101PETransaction extends AbstractRBVDT40101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDT40101PETransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		RBVDR401 rbvdR401 = this.getServiceLibrary(RBVDR401.class);
		// TODO - Implementation of business logic
	}

}
