package org.zerobase.publicwifiservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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

    @Test
    void findNearestWifi() throws Exception {
        //given
        given(publicWifiService.getNearestWifis(anyDouble(), anyDouble())).willReturn(List.of());
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
        then(publicWifiService).should().getNearestWifis(anyDouble(), anyDouble());
    }
}