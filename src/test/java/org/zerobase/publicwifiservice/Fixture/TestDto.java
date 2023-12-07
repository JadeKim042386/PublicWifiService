package org.zerobase.publicwifiservice.Fixture;

import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;
import org.zerobase.publicwifiservice.utils.DateTimeUtils;

import java.time.LocalDateTime;

public class TestDto {
    public static PublicWifiDto getPublicWifiDto() {
        return PublicWifiDto.of(
                33L,
                "wifi",
                11.1,
                11.1,
                "state",
                "city",
                "detail",
                12.2,
                DateTimeUtils.format(LocalDateTime.now())
        );
    }

    public static WifiApiResponse getWifiApiResponse() {
        return WifiApiResponse.of("", "wifi", 0D, 0D, "state", "city", "detail");
    }
}
