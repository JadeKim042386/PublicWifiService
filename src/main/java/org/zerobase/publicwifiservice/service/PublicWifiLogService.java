package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.PublicWifiLog;
import org.zerobase.publicwifiservice.domain.embeded.Location;
import org.zerobase.publicwifiservice.dto.PublicWifiLogDto;
import org.zerobase.publicwifiservice.repository.PublicWifiLogRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublicWifiLogService {
    private final PublicWifiLogRepository publicWifiLogRepository;

    public Page<PublicWifiLogDto> getWifiLogs(Pageable pageable) {
        //TODO: 페이지네이션 적용 (pagesize=25, sort=createdAt DESC)
        return publicWifiLogRepository.findAll(pageable)
                .map(PublicWifiLogDto::fromEntity);
    }

    @Transactional
    public void saveWifiLog(Double latitude, Double longitude) {
        publicWifiLogRepository.save(
                PublicWifiLog.of(Location.of(latitude, longitude))
        );
    }

    @Transactional
    public void deleteWifiLog(Long id) {
        publicWifiLogRepository.deleteById(id);
    }
}
