package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.domain.embeded.Address;
import org.zerobase.publicwifiservice.domain.embeded.Location;

public record PublicWifiDto(
        String wifiName,
        Double latitude,
        Double longitude,
        String addrState,
        String addrCity,
        String addrDetail
) {
    public static PublicWifiDto of(String wifiName, Double latitude, Double longitude, String addrState, String addrCity, String addrDetail) {
        return new PublicWifiDto(wifiName, latitude, longitude, addrState, addrCity, addrDetail);
    }

    public PublicWifi toEntity() {
        return PublicWifi.of(
            wifiName,
            Location.of(latitude, longitude),
            Address.of(addrState, addrCity, addrDetail)
        );
    }
}
