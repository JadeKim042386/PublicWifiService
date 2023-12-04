package org.zerobase.publicwifiservice.dto;

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
}
