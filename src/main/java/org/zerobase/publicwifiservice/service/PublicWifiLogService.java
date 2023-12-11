package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.PublicWifiLog;
import org.zerobase.publicwifiservice.domain.embeded.Location;
import org.zerobase.publicwifiservice.dto.PublicWifiLogDto;
import org.zerobase.publicwifiservice.exception.ErrorCode;
import org.zerobase.publicwifiservice.exception.PublicWifiLogException;
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

    /**
     * 와이파이 검색 기록 저장
     * @param latitude 위도
     * @param longitude 경도
     * @throws PublicWifiLogException 와이파이 검색 기록 저장 실패
     */
    @Transactional
    public void saveWifiLog(Double latitude, Double longitude) {
        try {
            publicWifiLogRepository.save(
                    PublicWifiLog.of(
                            Location.of(
                                    MathUtils.roundToNDecimalPlaces(latitude, 4),
                                    MathUtils.roundToNDecimalPlaces(longitude, 4)
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            log.error("와이파이 검색 기록 저장에 살패했습니다. - 위도(latitude): {}, 경도(longitude): {}", latitude, longitude);
            throw new PublicWifiLogException(ErrorCode.PUBLIC_WIFI_LOG_SAVE_FAILED, e);
        } catch (OptimisticLockingFailureException e) {
            log.error("와이파이 검색 기록 저장 중 Optimistic Lock 충돌이 일어났습니다. - 위도(latitude): {}, 경도(longitude): {}", latitude, longitude);
            throw new PublicWifiLogException(ErrorCode.PUBLIC_WIFI_LOG_SAVE_CONFLICT, e);
        }
    }

    @Transactional
    public void deleteWifiLog(Long id) {
        try {
            publicWifiLogRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error("와이파이 검색기록 삭제에 실패했습니다. - id: {}", id);
            throw new PublicWifiLogException(ErrorCode.PUBLIC_WIFI_LOG_CANT_DELETE, e);
        }
    }
}
