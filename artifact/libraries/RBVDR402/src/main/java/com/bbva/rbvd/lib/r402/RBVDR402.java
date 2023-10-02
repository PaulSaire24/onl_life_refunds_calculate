package com.bbva.rbvd.lib.r402;

import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundRequestBO;

public interface RBVDR402 {

 RefundCalculateResponseBO executeCalculateService(RefundRequestBO payload, String traceId);

}
