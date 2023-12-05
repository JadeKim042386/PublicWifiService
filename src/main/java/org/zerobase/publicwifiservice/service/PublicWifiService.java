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

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublicWifiService {
    private final WifiApi wifiApi;
    private final PublicWifiRepository publicWifiRepository;

    @Transactional
    public void updatePublicWifiAll() {
        List<PublicWifi> wifis = wifiApi.getWifis(0, 0, 0)
                .stream().map(WifiApiResponse::toEntity).toList();
        LinkedList<PublicWifi> notExistWifis = new LinkedList<>();
        wifis.forEach(wifi -> {
            //이미 존재하는 와이파이일 경우 Update (데이터가 다를 경우만)
            if (publicWifiRepository.existsByWifiName(wifi.getWifiName())) {
                PublicWifi publicWifi = publicWifiRepository.getReferenceByWifiName(wifi.getWifiName());
                if (wifi.getLocation() != publicWifi.getLocation()) {
                    publicWifi.setLocation(wifi.getLocation());
                }
                if (wifi.getAddress() != publicWifi.getAddress()) {
                    publicWifi.setAddress(wifi.getAddress());
                }
            } else {
                notExistWifis.add(wifi);
            }
        });
        //존재하지 않은 와이파이들은 모두 저장
        publicWifiRepository.saveAll(notExistWifis);

    }

    public List<PublicWifiDto> getNearestWifis(double latitude, double longitude) {
        return publicWifiRepository.findByDistance(latitude, longitude)
                .stream().map(PublicWifiDto::fromEntity).toList();
    }
}
