package org.zerobase.publicwifiservice.dto.response;

import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.domain.embeded.Address;
import org.zerobase.publicwifiservice.domain.embeded.Location;

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

    public PublicWifi toEntity() {
        return PublicWifi.of(
                AP_NAME,
                Location.of(LAT, LON),
                Address.of(ADDR_STATE, ADDR_CITY, ADDR_DETAIL)
        );
    }
}
