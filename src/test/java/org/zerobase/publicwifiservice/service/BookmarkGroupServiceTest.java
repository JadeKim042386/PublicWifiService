package org.zerobase.publicwifiservice.service;

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
import org.zerobase.publicwifiservice.Fixture.TestDto;
import org.zerobase.publicwifiservice.Fixture.TestEntity;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.repository.BookmarkGroupRepository;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ActiveProfiles("test")
@DisplayName("비지니스 로직 - 즐겨찾기 그룹")
@ExtendWith(MockitoExtension.class)
class BookmarkGroupServiceTest {
    @InjectMocks private BookmarkGroupService bookmarkGroupService;
    @Mock private BookmarkGroupRepository bookmarkGroupRepository;

    @DisplayName("즐겨찾기 그룹 조회")
    @Test
    void getBookmarkGroups() {
        //given
        Pageable pageable = PageRequest.of(1, 25, Sort.by(Sort.Direction.DESC, "createdAt"));
        given(bookmarkGroupRepository.findAll(any(Pageable.class))).willReturn(Page.empty());
        //when
        bookmarkGroupService.getBookmarkGroups(pageable);
        //then
        then(bookmarkGroupRepository).should().findAll(any(Pageable.class));
    }

    @DisplayName("즐겨찾기 그룹 추가(저장)")
    @Test
    void saveBookmarkGroup() {
        //given
        BookmarkGroup bookmarkGroup = TestEntity.getBookmarkGroup();
        given(bookmarkGroupRepository.save(any(BookmarkGroup.class))).willReturn(bookmarkGroup);
        //when
        bookmarkGroupService.saveBookmarkGroup(TestDto.getBookmarkGroupDto());
        //then
        then(bookmarkGroupRepository).should().save(any(BookmarkGroup.class));
    }

    @DisplayName("즐겨찾기 그룹 수정")
    @Test
    void updateBookmarkGroup() {
        //given
        BookmarkGroupDto bookmarkGroupDto = TestDto.getBookmarkGroupDto();
        BookmarkGroup bookmarkGroup = TestEntity.getBookmarkGroup();
        bookmarkGroup.setCreatedAt(LocalDateTime.now());
        given(bookmarkGroupRepository.getReferenceById(anyLong())).willReturn(bookmarkGroup);
        willDoNothing().given(bookmarkGroupRepository).flush();
        //when
        bookmarkGroupService.updateBookmarkGroup(bookmarkGroupDto);
        //then
        then(bookmarkGroupRepository).should().getReferenceById(anyLong());
        then(bookmarkGroupRepository).should().flush();
    }

    @DisplayName("즐겨찾기 그룹 삭제")
    @Test
    void deleteBookmarkGroup() {
        //given
        Long id = 1L;
        willDoNothing().given(bookmarkGroupRepository).deleteById(anyLong());
        //when
        bookmarkGroupService.deleteBookmarkGroup(id);
        //then
        then(bookmarkGroupRepository).should().deleteById(anyLong());
    }
}
