package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.domain.embeded.Address;
import org.zerobase.publicwifiservice.domain.embeded.Location;
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

    /**
     * 가까운 와이파이 조회시 사용
     */
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

    /**
     * 와이파이 상세정보 조회시 사용
     */
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

    /**
     * 즐겨찾기 추가/조회시 사용
     */
    public static PublicWifiDto fromEntity(PublicWifi entity) {
        return PublicWifiDto.of(
                entity.getId(),
                entity.getWifiName(),
                entity.getLocation().getLatitude(),
                entity.getLocation().getLongitude(),
                entity.getAddress().getAddrState(),
                entity.getAddress().getAddrCity(),
                entity.getAddress().getAddrDetail(),
                null,
                DateTimeUtils.format(entity.getUpdatedAt())
        );
    }

    public PublicWifi toEntity() {
        return PublicWifi.of(
                wifiName,
                Location.of(latitude, longitude),
                Address.of(addrState, addrCity, addrDetail)
        );
    }
}
