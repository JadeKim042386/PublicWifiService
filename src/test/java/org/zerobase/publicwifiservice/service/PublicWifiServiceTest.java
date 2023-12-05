package org.zerobase.publicwifiservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.zerobase.publicwifiservice.Fixture.TestDto;
import org.zerobase.publicwifiservice.Fixture.TestEntity;
import org.zerobase.publicwifiservice.api.WifiApi;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ActiveProfiles("test")
@DisplayName("비지니스 로직 - 공공와이파이")
@ExtendWith(MockitoExtension.class)
class PublicWifiServiceTest {

    @InjectMocks private PublicWifiService publicWifiService;
    @Mock private PublicWifiRepository publicWifiRepository;
    @Mock private WifiApi wifiApi;

    @DisplayName("공공와이파이 정보 전체 조회")
    @Test
    void getPublicWifiAll() {
        //given
        given(wifiApi.getWifis(0, 0, 0)).willReturn(List.of(TestDto.getWifiApiResponse()));
        //when
        List<PublicWifiDto> publicWifis = publicWifiService.getPublicWifiAll();
        //then
        then(wifiApi).should().getWifis(0, 0, 0);
    }

    @DisplayName("공공와이파이 정보 여러 개 저장")
    @Test
    void savePublicWifis() {
        //given
        PublicWifiDto publicWifiDto = TestDto.getPublicWifiDto();
        PublicWifi publicWifi = TestEntity.getPublicWifi();
        given(publicWifiRepository.saveAll(anyList())).willReturn(List.of(publicWifi));
        //when
        publicWifiService.savePublicWifis(List.of(publicWifiDto));
        //then
        then(publicWifiRepository).should().saveAll(anyList());
    }

    @DisplayName("입력한 위도와 경도를 기준으로 가까운 공공와이파이 20개 조회")
    @Test
    void getNearestWifis() {
        //given
        double latitude = 37.2747488;
        double longitude = 127.018885;
        given(publicWifiRepository.findByDistance(latitude, longitude)).willReturn(List.of());
        //when
        List<PublicWifiDto> wifids = publicWifiService.getNearestWifis(latitude, longitude);
        //then
        then(publicWifiRepository).should().findByDistance(latitude, longitude);
    }
}