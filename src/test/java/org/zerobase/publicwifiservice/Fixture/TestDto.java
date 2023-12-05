package org.zerobase.publicwifiservice.Fixture;

import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;

public class TestDto {
    public static PublicWifiDto getPublicWifiDto() {
        return PublicWifiDto.of("wifi", 11.1, 11.1, "state", "city", "detail");
    }

    public static WifiApiResponse getWifiApiResponse() {
        return WifiApiResponse.of("", "wifi", 0D, 0D, "state", "city", "detail");
    }
}
