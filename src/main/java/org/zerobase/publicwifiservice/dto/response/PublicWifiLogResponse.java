package org.zerobase.publicwifiservice.dto.response;

import org.zerobase.publicwifiservice.dto.PublicWifiLogDto;

public record PublicWifiLogResponse(
        Long id,
        Double latitude,
        Double longitude,
        String createdAt
) {

    public static PublicWifiLogResponse of(Long id, Double latitude, Double longitude, String createdAt) {
        return new PublicWifiLogResponse(id, latitude, longitude, createdAt);
    }

    public static PublicWifiLogResponse fromDto(PublicWifiLogDto dto) {
        return PublicWifiLogResponse.of(
                dto.id(),
                dto.latitude(),
                dto.longitude(),
                dto.createdAt()
        );
    }
}
