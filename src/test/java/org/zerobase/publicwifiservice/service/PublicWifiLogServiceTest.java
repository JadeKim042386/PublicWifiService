package org.zerobase.publicwifiservice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerobase.publicwifiservice.Fixture.TestEntity;
import org.zerobase.publicwifiservice.domain.PublicWifiLog;
import org.zerobase.publicwifiservice.exception.ErrorCode;
import org.zerobase.publicwifiservice.exception.PublicWifiLogException;
import org.zerobase.publicwifiservice.repository.PublicWifiLogRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ActiveProfiles("test")
@DisplayName("비지니스 로직 - 와이파이 검색 기록")
@ExtendWith(MockitoExtension.class)
class PublicWifiLogServiceTest {

    @InjectMocks private PublicWifiLogService publicWifiLogService;
    @Mock private PublicWifiLogRepository publicWifiLogRepository;

    @DisplayName("전체 검색 기록 조회")
    @Test
    void getPublicWifiLogs() {
        //given
        Pageable pageable = PageRequest.of(1, 25, Sort.by(Sort.Direction.DESC, "createdAt"));
        given(publicWifiLogRepository.findAll(any(Pageable.class))).willReturn(Page.empty());
        //when
        publicWifiLogService.getWifiLogs(pageable);
        //then
        then(publicWifiLogRepository).should().findAll(any(Pageable.class));
    }

    @DisplayName("기록 삭제 - 1개")
    @Test
    void deletePublicWifiLog() {
        //given
        Long id = 1L;
        willDoNothing().given(publicWifiLogRepository).deleteById(id);
        //when
        publicWifiLogService.deleteWifiLog(id);
        //then
        then(publicWifiLogRepository).should().deleteById(id);
    }

    @DisplayName("[예외 발생] 기록 삭제 - 1개")
    @Test
    void deletePublicWifiLogWithException() {
        //given
        Long id = 1L;
        willThrow(new PublicWifiLogException(ErrorCode.PUBLIC_WIFI_LOG_CANT_DELETE, new IllegalArgumentException()))
                .given(publicWifiLogRepository).deleteById(id);
        //when
        assertThatThrownBy(() -> publicWifiLogService.deleteWifiLog(id))
                .isInstanceOf(PublicWifiLogException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PUBLIC_WIFI_LOG_CANT_DELETE);
        //then
    }

    @DisplayName("기록 저장 - 1개")
    @Test
    void savePublicWifiLog() {
        //given
        PublicWifiLog publicWifiLog = TestEntity.getPublicWifiLog();
        given(publicWifiLogRepository.save(any(PublicWifiLog.class))).willReturn(publicWifiLog);
        //when
        publicWifiLogService.saveWifiLog(
                publicWifiLog.getLocation().getLatitude(),
                publicWifiLog.getLocation().getLongitude()
                );
        //then
        then(publicWifiLogRepository).should().save(any(PublicWifiLog.class));
    }

    @DisplayName("[예외 발생] 기록 저장 - 1개")
    @Test
    void savePublicWifiLogWithException() {
        //given
        PublicWifiLog publicWifiLog = TestEntity.getPublicWifiLog();
        given(publicWifiLogRepository.save(any(PublicWifiLog.class)))
                .willThrow(new PublicWifiLogException(ErrorCode.PUBLIC_WIFI_LOG_SAVE_FAILED, new IllegalArgumentException()));
        //when
        assertThatThrownBy(() -> publicWifiLogService.saveWifiLog(
                publicWifiLog.getLocation().getLatitude(),
                publicWifiLog.getLocation().getLongitude()
        ))
                .isInstanceOf(PublicWifiLogException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PUBLIC_WIFI_LOG_SAVE_FAILED);
        //then
    }
}
