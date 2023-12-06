package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerobase.publicwifiservice.dto.PublicWifiLogDto;
import org.zerobase.publicwifiservice.repository.PublicWifiLogRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicWifiLogService {
    private final PublicWifiLogRepository publicWifiLogRepository;

    public List<PublicWifiLogDto> getWifiLogs() {
        //TODO: 페이지네이션 적용 (pagesize=25, sort=createdAt)
        return List.of();
    }

    public void deleteWifiLog(Long id) {
        return;
    }
}
