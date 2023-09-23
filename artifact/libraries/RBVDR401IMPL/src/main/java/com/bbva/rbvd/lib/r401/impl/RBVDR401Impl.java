package com.bbva.rbvd.lib.r401.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RBVDR401Impl extends RBVDR401Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(RBVDR401Impl.class);

	@Override
	public String executeHolaMundo(boolean param) {

		if(param){
			return "Hola Mundo";
		}else{
			return "no corre";
		}

	}
}
