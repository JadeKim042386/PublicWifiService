package org.zerobase.publicwifiservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zerobase.publicwifiservice.service.PublicWifiLogService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DisplayName("View 컨트롤러 - Wifi 검색 로그 조회 및 삭제 요청")
@WebMvcTest(PublicWifiLogController.class)
class PublicWifiLogControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PublicWifiLogService publicWifiLogService;

    @Test
    void getWifiLogs() throws Exception {
        //given
        given(publicWifiLogService.getWifiLogs(any(Pageable.class))).willReturn(Page.empty());
        //when
        mvc.perform(
                get("/wifi_log")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/logs/wifi_log"))
                .andExpect(model().attributeExists("logs"));
        //then
        then(publicWifiLogService).should().getWifiLogs(any(Pageable.class));
    }

    @Test
    void deleteWifiLog() throws Exception {
        //given
        Long id = 1L;
        willDoNothing().given(publicWifiLogService).deleteWifiLog(id);
        //when
        mvc.perform(
                get("/wifi_log/delete/1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //then
        then(publicWifiLogService).should().deleteWifiLog(id);
    }
}
