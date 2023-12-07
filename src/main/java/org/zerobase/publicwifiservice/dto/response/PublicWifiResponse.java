package org.zerobase.publicwifiservice.dto.response;

import lombok.Getter;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;

@Getter
public class PublicWifiResponse{
    private Long id;
    private String wifiName;
    private Double latitude;
    private Double longitude;
    private String addrState;
    private String addrCity;
    private String addrDetail;
    private Double distance;
    private String updatedAt;

    protected PublicWifiResponse() {
    }

    private PublicWifiResponse(Long id, String wifiName, Double latitude, Double longitude, String addrState, String addrCity, String addrDetail, Double distance, String updatedAt) {
        this.id = id;
        this.wifiName = wifiName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addrState = addrState;
        this.addrCity = addrCity;
        this.addrDetail = addrDetail;
        this.distance = distance;
        this.updatedAt = updatedAt;
    }

    public static PublicWifiResponse of(Long id, String wifiName, Double latitude, Double longitude, String addrState, String addrCity, String addrDetail, Double distance, String updatedAt) {
        return new PublicWifiResponse(id, wifiName, latitude, longitude, addrState, addrCity, addrDetail, distance, updatedAt);
    }

    public static PublicWifiResponse fromDto(PublicWifiDto dto) {
        return PublicWifiResponse.of(
                dto.id(),
                dto.wifiName(),
                dto.latitude(),
                dto.longitude(),
                dto.addrState(),
                dto.addrCity(),
                dto.addrDetail(),
                dto.distance(),
                dto.updatedAt()
        );
    }
}
