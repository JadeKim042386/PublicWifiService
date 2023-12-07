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
import org.zerobase.publicwifiservice.utils.MathUtils;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublicWifiLogService {
    private final PublicWifiLogRepository publicWifiLogRepository;

    public Page<PublicWifiLogDto> getWifiLogs(Pageable pageable) {
        return publicWifiLogRepository.findAll(pageable)
                .map(PublicWifiLogDto::fromEntity);
    }

    @Transactional
    public void saveWifiLog(Double latitude, Double longitude) {
        publicWifiLogRepository.save(
                PublicWifiLog.of(
                        Location.of(
                                MathUtils.roundToNDecimalPlaces(latitude, 4),
                                MathUtils.roundToNDecimalPlaces(longitude, 4)
                        )
                )
        );
    }

    @Transactional
    public void deleteWifiLog(Long id) {
        publicWifiLogRepository.deleteById(id);
    }
}
