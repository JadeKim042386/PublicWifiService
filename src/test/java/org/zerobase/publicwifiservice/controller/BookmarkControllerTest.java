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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.zerobase.publicwifiservice.service.BookmarkService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DisplayName("View 컨트롤러 - 즐겨찾기")
@WebMvcTest(BookmarkController.class)
class BookmarkControllerTest {
    @Autowired private MockMvc mvc;
    @MockBean private BookmarkService bookmarkService;

    @DisplayName("즐겨찾기 조회")
    @Test
    void getBookmarks() throws Exception {
        //given
        given(bookmarkService.getBookmarks(any(Pageable.class))).willReturn(Page.empty());
        //when
        mvc.perform(
                get("/bookmark")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/bookmark/bookmark"));
        //then
        then(bookmarkService).should().getBookmarks(any(Pageable.class));
    }

    @DisplayName("즐겨찾기 저장")
    @Test
    void saveBookmark() throws Exception {
        //given
        willDoNothing().given(bookmarkService).saveBookmark(anyLong(), anyLong());
        //when
        mvc.perform(
                get("/bookmark/save")
                        .queryParam("groupId", "1")
                        .queryParam("wifiId", "1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //then
        then(bookmarkService).should().saveBookmark(anyLong(), anyLong());
    }

    @DisplayName("즐겨찾기 삭제")
    @Test
    void deleteBookmark() throws Exception {
        //given
        willDoNothing().given(bookmarkService).deleteBookmark(anyLong());
        //when
        mvc.perform(
                get("/bookmark/delete/1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //then
        then(bookmarkService).should().deleteBookmark(anyLong());
    }
}
