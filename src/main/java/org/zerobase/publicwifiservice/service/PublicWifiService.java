package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.api.WifiApi;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

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

    @Transactional
    public void updatePublicWifiAll() {
        Set<PublicWifi> wifis = wifiApi.getWifis(0, 0, 0)
                .stream().map(WifiApiResponse::toEntity).collect(Collectors.toSet());
        //존재하지 않은 와이파이들을 모두 저장 (변경분만 저장)
        publicWifiRepository.saveAll(
                wifis.stream().filter(
                        wifi -> !publicWifiRepository.existsByWifiName(wifi.getWifiName())
                ).toList()
        );
    }

    public List<PublicWifiDto> getNearestWifis(double latitude, double longitude) {
        return publicWifiRepository.findByDistance(latitude, longitude)
                .stream().map(PublicWifiDto::fromEntity).toList();
    }
}
