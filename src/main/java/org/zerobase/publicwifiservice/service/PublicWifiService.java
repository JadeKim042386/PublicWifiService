package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.zerobase.publicwifiservice.api.WifiApi;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;
import org.zerobase.publicwifiservice.exception.ErrorCode;
import org.zerobase.publicwifiservice.exception.PublicWifiException;
import org.zerobase.publicwifiservice.repository.PublicWifiJdbcRepository;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublicWifiService {
    private final WifiApi wifiApi;
    private final PublicWifiRepository publicWifiRepository;
    private final PublicWifiJdbcRepository publicWifiJdbcRepository;

    /**
     * API 요청으로 전체 와이파이 정보 업데이트
     * @throws PublicWifiException 전체 와이파이 정보 조회 실패
     */
    @Transactional
    public void updatePublicWifiAll() {
        try {
            //API 요청으로 전체 와이파이 정보 조회
            Set<PublicWifi> ApiWifis = wifiApi.getWifis(0, 0, 0)
                    .stream().map(WifiApiResponse::toEntity).collect(Collectors.toSet());
            //DB에 저장되어있는 전체 와이파이 정보 조회
            Set<String> savedWifiNames = new HashSet<>(publicWifiRepository.findAllWifiName());
            //존재하지 않은 와이파이들을 모두 저장 (변경분만 저장)
            List<PublicWifi> newWifis = new ArrayList<>();
            ApiWifis.forEach(wifi -> {
                if (!savedWifiNames.contains(wifi.getWifiName())) {
                    newWifis.add(wifi);
                }
            });
            publicWifiJdbcRepository.saveAll(newWifis);
        } catch (RestClientException e) {
            throw new PublicWifiException(ErrorCode.PUBLIC_WIFI_CANT_UPDATE, e);
        }

    }

    /**
     * 가까운 와이파이 정보 조회
     * @param latitude 위도
     * @param longitude 경도
     * @throws PublicWifiException 가까운 와이파이 정보 조회 실패
     */
    public List<PublicWifiDto> getNearestWifis(double latitude, double longitude) {
        try {
            return publicWifiRepository.findByDistance(latitude, longitude)
                    .stream().map(entity -> PublicWifiDto.fromEntity(entity, latitude, longitude))
                    .toList();
        } catch (IllegalArgumentException e) {
            log.error("가까운 와이파이 정보를 찾을 수 없습니다. - 위도(latitude): {}, 경도(longitude): {}", latitude, longitude);
            throw new PublicWifiException(ErrorCode.PUBLIC_WIFI_NOT_FOUND_NEAREST, e);
        }
    }

    /**
     * 와이파이 정보 조회
     * @param id
     * @param distance 가까운 와이파이 조회시 계산된 거리값
     * @throws PublicWifiException 와이파이 정보 조회 실패
     */
    public PublicWifiDto getWifi(Long id, Double distance) {
        try {
            return publicWifiRepository.findById(id)
                    .map(entity -> PublicWifiDto.fromEntity(entity, distance))
                    .orElseThrow(EntityNotFoundException::new);
        } catch (IllegalArgumentException e) {
            log.error("와이파이 정보를 찾을 수 없습니다. - id: {}", id);
            throw new PublicWifiException(ErrorCode.PUBLIC_WIFI_NOT_FOUND, e);
        }
    }
}
