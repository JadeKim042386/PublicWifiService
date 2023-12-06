package org.zerobase.publicwifiservice.dto.response;

import lombok.Getter;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;

@Getter
public class PublicWifiResponse{
    private String wifiName;
    private Double latitude;
    private Double longitude;
    private String addrState;
    private String addrCity;
    private String addrDetail;

    protected PublicWifiResponse() {
    }

    private PublicWifiResponse(String wifiName, Double latitude, Double longitude, String addrState, String addrCity, String addrDetail) {
        this.wifiName = wifiName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addrState = addrState;
        this.addrCity = addrCity;
        this.addrDetail = addrDetail;
    }

    public static PublicWifiResponse of(String wifiName, Double latitude, Double longitude, String addrState, String addrCity, String addrDetail) {
        return new PublicWifiResponse(wifiName, latitude, longitude, addrState, addrCity, addrDetail);
    }

    public static PublicWifiResponse fromDto(PublicWifiDto dto) {
        return PublicWifiResponse.of(
                dto.wifiName(),
                dto.latitude(),
                dto.longitude(),
                dto.addrState(),
                dto.addrCity(),
                dto.addrDetail()
        );
    }
}
