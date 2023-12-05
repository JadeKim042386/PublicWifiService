package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.PublicWifi;

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

    public static PublicWifiDto fromEntity(PublicWifi entity) {
        return PublicWifiDto.of(
                entity.getWifiName(),
                entity.getLocation().getLatitude(),
                entity.getLocation().getLongitude(),
                entity.getAddress().getAddrState(),
                entity.getAddress().getAddrCity(),
                entity.getAddress().getAddrDetail()
        );
    }
}
