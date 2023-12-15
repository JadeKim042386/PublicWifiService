package org.zerobase.publicwifiservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zerobase.publicwifiservice.Fixture.TestDto;
import org.zerobase.publicwifiservice.service.BookmarkGroupService;
import org.zerobase.publicwifiservice.service.PublicWifiLogService;
import org.zerobase.publicwifiservice.service.PublicWifiService;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DisplayName("View 컨트롤러 - Wifi 조회 및 업데이트")
@WebMvcTest(PublicWifiController.class)
class PublicWifiControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private PublicWifiService publicWifiService;
    @MockBean private PublicWifiLogService publicWifiLogService;
    @MockBean private BookmarkGroupService bookmarkGroupService;

    @DisplayName("와이파이 전체 정보 업데이트")
    @Test
    void updateAllByApi() throws Exception {
        //given
        willDoNothing().given(publicWifiService).updatePublicWifiAll();
        //when
        mvc.perform(
                get("/public_wifi/apiUpdateAll")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //then
        then(publicWifiService).should().updatePublicWifiAll();
    }

    @DisplayName("가까운 와이파이 정보 조회")
    @Test
    void findNearestWifi() throws Exception {
        //given
        given(publicWifiService.getNearestWifis(anyDouble(), anyDouble(), any(Pageable.class))).willReturn(List.of());
        willDoNothing().given(publicWifiLogService).saveWifiLog(anyDouble(), anyDouble());
        //when
        mvc.perform(
                post("/public_wifi/findNearestWifi")
                        .queryParam("latitude", "37.7")
                        .queryParam("longitude", "37.7")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
        //then
        then(publicWifiService).should().getNearestWifis(anyDouble(), anyDouble(), any(Pageable.class));
        then(publicWifiLogService).should().saveWifiLog(anyDouble(), anyDouble());
    }

    @DisplayName("와이파이 상세정보 조회")
    @Test
    void detailPublicWifi() throws Exception {
        //given
        given(publicWifiService.getWifi(anyLong(), anyDouble())).willReturn(TestDto.getPublicWifiDto());
        given(bookmarkGroupService.getBookmarkGroupAll()).willReturn(List.of());
        //when
        mvc.perform(
                get("/public_wifi/detail/1")
                        .queryParam("distance", "0.1827")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/public_wifi/detail"))
                .andExpect(model().attributeExists("publicWifi"));
        //then
        then(publicWifiService).should().getWifi(anyLong(), anyDouble());
        then(bookmarkGroupService).should().getBookmarkGroupAll();
    }
}
