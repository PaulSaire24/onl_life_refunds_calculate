package com.bbva.rbvd.lib.r402;

import com.bbva.pisd.dto.insurance.amazon.SignatureAWS;
import com.bbva.rbvd.dto.insurancerefunds.rimac.RefundCalculateResponseBO;


public interface RBVDR402 {

 RefundCalculateResponseBO executeCalculateService(SignatureAWS signatureAWS, String requestJson);

}
