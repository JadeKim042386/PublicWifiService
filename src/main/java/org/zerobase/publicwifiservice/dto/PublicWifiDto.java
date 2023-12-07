package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.utils.DateTimeUtils;
import org.zerobase.publicwifiservice.utils.MathUtils;

public record PublicWifiDto(
        Long id,
        String wifiName,
        Double latitude,
        Double longitude,
        String addrState,
        String addrCity,
        String addrDetail,
        Double distance,
        String updatedAt
) {
    public static PublicWifiDto of(Long id, String wifiName, Double latitude, Double longitude, String addrState, String addrCity, String addrDetail, Double distance, String updatedAt) {
        return new PublicWifiDto(id, wifiName, latitude, longitude, addrState, addrCity, addrDetail, distance, updatedAt);
    }

    public static PublicWifiDto fromEntity(PublicWifi entity, double latitude, double longitude) {
        return PublicWifiDto.of(
                entity.getId(),
                entity.getWifiName(),
                entity.getLocation().getLatitude(),
                entity.getLocation().getLongitude(),
                entity.getAddress().getAddrState(),
                entity.getAddress().getAddrCity(),
                entity.getAddress().getAddrDetail(),
                MathUtils.calculateDistance(
                        entity.getLocation().getLatitude(),
                        entity.getLocation().getLongitude(),
                        latitude,
                        longitude
                ),
                DateTimeUtils.format(entity.getUpdatedAt())
        );
    }

    public static PublicWifiDto fromEntity(PublicWifi entity, double distance) {
        return PublicWifiDto.of(
                entity.getId(),
                entity.getWifiName(),
                entity.getLocation().getLatitude(),
                entity.getLocation().getLongitude(),
                entity.getAddress().getAddrState(),
                entity.getAddress().getAddrCity(),
                entity.getAddress().getAddrDetail(),
                distance,
                DateTimeUtils.format(entity.getUpdatedAt())
        );
    }
}
