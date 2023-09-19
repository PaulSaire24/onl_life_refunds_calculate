package com.bbva.rbvd.lib.r402.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.rbvd.lib.r401.RBVDR401;
import com.bbva.rbvd.lib.r402.RBVDR402;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class RBVDR402Abstract extends AbstractLibrary implements RBVDR402 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected RBVDR401 rbvdR401;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param rbvdR401 the this.rbvdR401 to set
	*/
	public void setRbvdR401(RBVDR401 rbvdR401) {
		this.rbvdR401 = rbvdR401;
	}

}