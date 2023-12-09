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
import org.zerobase.publicwifiservice.Fixture.TestEntity;
import org.zerobase.publicwifiservice.domain.Bookmark;
import org.zerobase.publicwifiservice.repository.BookmarkGroupRepository;
import org.zerobase.publicwifiservice.repository.BookmarkRepository;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ActiveProfiles("test")
@DisplayName("비지니스 로직 - 북마크 그룹")
@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {
    @InjectMocks private BookmarkService bookmarkService;
    @Mock private BookmarkRepository bookmarkRepository;
    @Mock private BookmarkGroupRepository bookmarkGroupRepository;
    @Mock private PublicWifiRepository publicWifiRepository;

    @DisplayName("북마크 조회")
    @Test
    void getBookmarks() {
        //given
        Pageable pageable = PageRequest.of(1, 25, Sort.by(Sort.Direction.DESC, "createdAt"));
        given(bookmarkRepository.findAll(any(Pageable.class))).willReturn(Page.empty());
        //when
        bookmarkService.getBookmarks(pageable);
        //then
        then(bookmarkRepository).should().findAll(any(Pageable.class));
    }

    @DisplayName("북마크 저장")
    @Test
    void saveBookmark() {
        //given
        Bookmark bookmark = TestEntity.getBookmark();
        given(bookmarkGroupRepository.getReferenceById(anyLong())).willReturn(TestEntity.getBookmarkGroup());
        given(publicWifiRepository.getReferenceById(anyLong())).willReturn(TestEntity.getPublicWifi());
        given(bookmarkRepository.save(any(Bookmark.class))).willReturn(bookmark);
        //when
        bookmarkService.saveBookmark(1L, 1L);
        //then
        then(bookmarkGroupRepository).should().getReferenceById(anyLong());
        then(publicWifiRepository).should().getReferenceById(anyLong());
        then(bookmarkRepository).should().save(any(Bookmark.class));
    }

    @DisplayName("북마크 삭제")
    @Test
    void deleteBookmark() {
        //given
        Long id = 1L;
        willDoNothing().given(bookmarkRepository).deleteById(id);
        //when
        bookmarkService.deleteBookmark(id);
        //then
        then(bookmarkRepository).should().deleteById(id);
    }
}
