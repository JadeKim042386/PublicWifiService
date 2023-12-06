package org.zerobase.publicwifiservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.zerobase.publicwifiservice.repository.PublicWifiLogRepository;

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
        //when
        publicWifiLogService.getWifiLogs();
        //then
    }

    @DisplayName("기록 삭제 - 1개")
    @Test
    void deletePublicWifiLog() {
        //given
        Long id = 1L;
        //when
        publicWifiLogService.deleteWifiLog(id);
        //then
    }



}