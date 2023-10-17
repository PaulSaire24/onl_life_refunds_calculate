package com.bbva.rbvd.dto.insurancerefunds.utils;

import com.bbva.apx.exception.business.BusinessException;

public class Validation {

    public static BusinessException build(Errors error) {
        return new BusinessException(error.getAdviceCode(), error.isRollback(), error.getMessage());
    }
}
