package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.repository.BookmarkGroupRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkGroupService {
    private final BookmarkGroupRepository bookmarkGroupRepository;

    public Page<BookmarkGroupDto> getBookmarkGroups(Pageable pageable) {
        return bookmarkGroupRepository.findAll(pageable).map(BookmarkGroupDto::fromEntity);
    }

    @Transactional
    public void saveBookmarkGroup(BookmarkGroup bookmarkGroup) {
        bookmarkGroupRepository.save(bookmarkGroup);
    }

    @Transactional
    public void updateBookmarkGroup(BookmarkGroupDto bookmarkGroupDto) {
        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.getReferenceById(bookmarkGroupDto.id());
        if (bookmarkGroupDto.groupName() != null) {
            bookmarkGroup.setGroupName(bookmarkGroupDto.groupName());
        }
    }

    @Transactional
    public void deleteBookmarkGroup(Long id) {
        bookmarkGroupRepository.deleteById(id);
    }
}