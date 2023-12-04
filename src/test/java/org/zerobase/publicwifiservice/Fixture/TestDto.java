package org.zerobase.publicwifiservice.Fixture;

import org.zerobase.publicwifiservice.dto.PublicWifiDto;

public class TestDto {
    public static PublicWifiDto getPublicWifiDto() {
        return PublicWifiDto.of("wifi", 11.1, 11.1, "state", "city", "detail");
    }
}
