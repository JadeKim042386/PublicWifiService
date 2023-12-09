package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.dto.BookmarkDto;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.repository.BookmarkGroupRepository;
import org.zerobase.publicwifiservice.repository.BookmarkRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public Page<BookmarkDto> getBookmarks(Pageable pageable) {
        return bookmarkRepository.findAll(pageable).map(BookmarkDto::fromEntity);
    }

    @Transactional
    public void saveBookmark(BookmarkDto bookmarkDto) {
        bookmarkRepository.save(bookmarkDto.toEntity());
    }

    @Transactional
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
