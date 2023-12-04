package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.api.WifiApi;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublicWifiService {
    private final WifiApi wifiApi;
    private final PublicWifiRepository publicWifiRepository;

    public List<PublicWifiDto> getPublicWifiAll() {
        return List.of();
    }

    @Transactional
    public void savePublicWifi(List<PublicWifiDto> publicWifiDtos) {
        return;
    }

    public List<PublicWifiDto> getNearestWifis(double latitude, double longitude) {
        return List.of();
    }
}
