package org.zerobase.publicwifiservice.dto.response;

import lombok.Getter;
import org.zerobase.publicwifiservice.dto.PublicWifiLogDto;

@Getter
public class PublicWifiLogResponse{
    private Long id;
    private Double latitude;
    private Double longitude;
    private String createdAt;

    protected PublicWifiLogResponse() {
    }

    private PublicWifiLogResponse(Long id, Double latitude, Double longitude, String createdAt) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
    }

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
