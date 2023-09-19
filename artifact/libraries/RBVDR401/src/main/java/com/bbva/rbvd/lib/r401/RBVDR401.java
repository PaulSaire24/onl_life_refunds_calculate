package com.bbva.rbvd.lib.r401;


import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;

public interface RBVDR401 {
	RefundCalculatedBO executeCalculateService(RefundRequestBO payload, String traceId);

}
