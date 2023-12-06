package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.PublicWifiLog;
import org.zerobase.publicwifiservice.utils.DateTimeUtils;

public record PublicWifiLogDto(
        Long id,
        Double latitude,
        Double longitude,
        String createdAt
) {
    public static PublicWifiLogDto of(Long id, Double latitude, Double longitude, String createdAt) {
        return new PublicWifiLogDto(id, latitude, longitude, createdAt);
    }

    public static PublicWifiLogDto fromEntity(PublicWifiLog entity) {
        return PublicWifiLogDto.of(
                entity.getId(),
                entity.getLocation().getLatitude(),
                entity.getLocation().getLongitude(),
                DateTimeUtils.format(entity.getCreatedAt())
        );
    }
}
