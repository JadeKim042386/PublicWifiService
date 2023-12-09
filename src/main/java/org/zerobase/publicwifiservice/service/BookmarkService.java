package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.Bookmark;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.dto.BookmarkDto;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.repository.BookmarkGroupRepository;
import org.zerobase.publicwifiservice.repository.BookmarkRepository;
import org.zerobase.publicwifiservice.repository.PublicWifiRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkGroupRepository bookmarkGroupRepository;
    private final PublicWifiRepository publicWifiRepository;

    public Page<BookmarkDto> getBookmarks(Pageable pageable) {
        return bookmarkRepository.findAll(pageable).map(BookmarkDto::fromEntity);
    }

    @Transactional
    public void saveBookmark(Long bookmarkGroupId, Long publicWifiId) {
        bookmarkRepository.save(
                    Bookmark.of(
                    bookmarkGroupRepository.getReferenceById(bookmarkGroupId),
                    publicWifiRepository.getReferenceById(publicWifiId)
            )
        );
    }

    @Transactional
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
