package org.zerobase.publicwifiservice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;
import org.zerobase.publicwifiservice.Fixture.TestDto;
import org.zerobase.publicwifiservice.Fixture.TestEntity;
import org.zerobase.publicwifiservice.api.WifiApi;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.exception.ErrorCode;
import org.zerobase.publicwifiservice.exception.PublicWifiException;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ActiveProfiles("test")
@DisplayName("비지니스 로직 - 공공와이파이")
@ExtendWith(MockitoExtension.class)
class PublicWifiServiceTest {

    @InjectMocks private PublicWifiService publicWifiService;
    @Mock private PublicWifiRepository publicWifiRepository;
    @Mock private WifiApi wifiApi;

    @DisplayName("API를 통한 공공와이파이 정보 전체 업데이트")
    @Test
    void getPublicWifiAll() {
        //given
        given(wifiApi.getWifis(0, 0, 0)).willReturn(List.of(TestDto.getWifiApiResponse()));
        given(publicWifiRepository.existsByWifiName(anyString())).willReturn(true);
        PublicWifi publicWifi = TestEntity.getPublicWifi();
        given(publicWifiRepository.saveAll(anyList())).willReturn(List.of(publicWifi));
        //when
        publicWifiService.updatePublicWifiAll();
        //then
        then(wifiApi).should().getWifis(0, 0, 0);
        then(publicWifiRepository).should().existsByWifiName(anyString());
        then(publicWifiRepository).should().saveAll(anyList());
    }

    @DisplayName("[예외 발생] API를 통한 공공와이파이 정보 전체 업데이트")
    @Test
    void getPublicWifiAllWithException() {
        //given
        given(wifiApi.getWifis(0, 0, 0)).willThrow(new PublicWifiException(ErrorCode.PUBLIC_WIFI_CANT_UPDATE, new RestClientException("rest client error")));
        //when
        assertThatThrownBy(() -> publicWifiService.updatePublicWifiAll())
                .isInstanceOf(PublicWifiException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PUBLIC_WIFI_CANT_UPDATE);
        //then
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

    @DisplayName("[예외 발생] 입력한 위도와 경도를 기준으로 가까운 공공와이파이 20개 조회")
    @Test
    void getNearestWifisWithException() {
        //given
        double latitude = 37.2747488;
        double longitude = 127.018885;
        given(publicWifiRepository.findByDistance(latitude, longitude)).willThrow(new PublicWifiException(ErrorCode.PUBLIC_WIFI_NOT_FOUND_NEAREST, new IllegalArgumentException()));
        //when
        assertThatThrownBy(() -> publicWifiService.getNearestWifis(latitude, longitude))
                .isInstanceOf(PublicWifiException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PUBLIC_WIFI_NOT_FOUND_NEAREST);
        //then
    }

    @DisplayName("와이파이 상세 정보 조회")
    @Test
    void getWifi() {
        //given
        Long id = 1L;
        Double distance = 0.1827;
        PublicWifi publicWifi = TestEntity.getPublicWifi();
        publicWifi.setUpdatedAt(LocalDateTime.now());
        given(publicWifiRepository.findById(anyLong())).willReturn(Optional.of(publicWifi));
        //when
        publicWifiService.getWifi(id, distance);
        //then
        then(publicWifiRepository).should().findById(anyLong());
    }

    @DisplayName("[예외 발생] 와이파이 상세 정보 조회")
    @Test
    void getWifiWithException() {
        //given
        Long id = 1L;
        Double distance = 0.1827;
        PublicWifi publicWifi = TestEntity.getPublicWifi();
        publicWifi.setUpdatedAt(LocalDateTime.now());
        given(publicWifiRepository.findById(anyLong())).willThrow(new PublicWifiException(ErrorCode.PUBLIC_WIFI_NOT_FOUND, new IllegalArgumentException()));
        //when
        assertThatThrownBy(() -> publicWifiService.getWifi(id, distance))
                .isInstanceOf(PublicWifiException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PUBLIC_WIFI_NOT_FOUND);
        ;
        //then
    }
}
