package org.zerobase.publicwifiservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class WifiApiTest {
    @Autowired private WifiApi api;

    @Test
    void getWifi() {
        //given
        double lat = 37.2747488;
        double lon = 127.018885;
        double dist = 0.5;
        //when
        List<WifiApiResponse> responses = api.getWifis(lat, lon, dist);
        //then
        assertThat(responses.size()).isGreaterThan(0);
    }
}
