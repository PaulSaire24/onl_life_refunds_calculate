package com.bbva.rbvd.lib.r402;

import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundCalculatedBO;
import com.bbva.rbvd.dto.insuranceroyal.rimac.calculate.RefundRequestBO;

public interface RBVDR402 {

 RefundCalculatedBO executeCalculateService(RefundRequestBO payload, String traceId);

}
