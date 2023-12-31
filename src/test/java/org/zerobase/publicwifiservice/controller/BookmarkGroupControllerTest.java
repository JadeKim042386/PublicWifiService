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
import org.zerobase.publicwifiservice.Fixture.TestDto;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.service.BookmarkGroupService;
import org.zerobase.publicwifiservice.service.PaginationService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DisplayName("View 컨트롤러 - 즐겨찾기 그룹")
@WebMvcTest(BookmarkGroupController.class)
class BookmarkGroupControllerTest {
    @Autowired private MockMvc mvc;
    @MockBean private BookmarkGroupService bookmarkGroupService;
    @MockBean private PaginationService paginationService;

    @DisplayName("즐겨찾기 그룹 조회")
    @Test
    void getBookmarkGroups() throws Exception {
        //given
        given(bookmarkGroupService.getBookmarkGroups(any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of());
        //when
        mvc.perform(
                get("/bookmark_group")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("/bookmark/bookmark_group"))
                .andExpect(model().attributeExists("groups"));
        //then
        then(bookmarkGroupService).should().getBookmarkGroups(any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("즐겨찾기 그룹 저장")
    @Test
    void saveBookmarkGroup() throws Exception {
        //given
        willDoNothing().given(bookmarkGroupService).saveBookmarkGroup(any(BookmarkGroupDto.class));
        //when
        mvc.perform(
                post("/bookmark_group/save")
                        .queryParam("groupName", "group")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bookmark_group"))
                .andExpect(redirectedUrl("/bookmark_group"));
        //then
        then(bookmarkGroupService).should().saveBookmarkGroup(any(BookmarkGroupDto.class));
    }

    @DisplayName("즐겨찾기 그룹 수정")
    @Test
    void updateBookmarkGroup() throws Exception {
        //given
        given(bookmarkGroupService.updateBookmarkGroup(any(BookmarkGroupDto.class))).willReturn(TestDto.getBookmarkGroupDto());
        //when
        mvc.perform(
                        get("/bookmark_group/update")
                                .queryParam("groupId", "1")
                                .queryParam("groupName", "group")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //then
        then(bookmarkGroupService).should().updateBookmarkGroup(any(BookmarkGroupDto.class));
    }

    @DisplayName("즐겨찾기 그룹 삭제")
    @Test
    void deleteBookmarkGroup() throws Exception {
        //given
        willDoNothing().given(bookmarkGroupService).deleteBookmarkGroup(anyLong());
        //when
        mvc.perform(
                get("/bookmark_group/delete/1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //then
        then(bookmarkGroupService).should().deleteBookmarkGroup(anyLong());
    }
}
