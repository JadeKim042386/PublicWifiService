package org.zerobase.publicwifiservice.dto.response;

public record WifiApiResponse (
    String RN,
    String AP_NAME,
    Double LAT,
    Double LON,
    String ADDR_STATE,
    String ADDR_CITY,
    String ADDR_DETAIL
) {
    public static WifiApiResponse of(String RN, String AP_NAME, Double LAT, Double LON, String ADDR_STATE, String ADDR_CITY, String ADDR_DETAIL) {
        return new WifiApiResponse(RN, AP_NAME, LAT, LON, ADDR_STATE, ADDR_CITY, ADDR_DETAIL);
    }
}
