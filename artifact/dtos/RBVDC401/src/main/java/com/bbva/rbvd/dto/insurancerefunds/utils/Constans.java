package com.bbva.rbvd.dto.insurancerefunds.utils;

import java.time.ZoneId;

public class Constans {
    public static final String PERCENTAGE = "PERCENTAGE";
    public static final String PRODUCTO = "VIDADINAMICO";
    public static final ZoneId ZONE_ID = ZoneId.of("GMT");

    public static final class Headers {
        private Headers() {
        }
        public static final String AUTHORIZATION = "Authorization";
        public static final String APPLICATION = "application";
        public static final String JSON = "json";
        public static final String AMZ_DATE = "X-Amz-Date";
        public static final String API_KEY = "x-api-key";
        public static final String TRACE_ID = "traceId";

    }
}
