package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.exception.BookmarkGroupException;
import org.zerobase.publicwifiservice.exception.ErrorCode;
import org.zerobase.publicwifiservice.repository.BookmarkGroupRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkGroupService {
    private final BookmarkGroupRepository bookmarkGroupRepository;

    public List<BookmarkGroupDto> getBookmarkGroupAll() {
        return bookmarkGroupRepository.findAll().stream().map(BookmarkGroupDto::fromEntity).toList();
    }

    public Page<BookmarkGroupDto> getBookmarkGroups(Pageable pageable) {
        return bookmarkGroupRepository.findAll(pageable).map(BookmarkGroupDto::fromEntity);
    }

    /**
     * 즐겨찾기 그룹 저장
     * @throws BookmarkGroupException 즐겨찾기 그룹 저장 실패
     */
    @Transactional
    public void saveBookmarkGroup(BookmarkGroupDto bookmarkGroupDto) {
        try {
            bookmarkGroupRepository.save(bookmarkGroupDto.toEntity());
        } catch (IllegalArgumentException e) {
            log.error("즐겨찾기 그룹 저장에 실패했습니다. - Name: {}", bookmarkGroupDto.groupName());
            throw new BookmarkGroupException(ErrorCode.BOOKMARK_GROUP_SAVE_FAILED, e);
        } catch (OptimisticLockingFailureException e) {
            log.error("즐겨찾기 그룹 저장 중 Optimistic Lock 충돌이 일어났습니다. - Name: {}", bookmarkGroupDto.groupName());
            throw new BookmarkGroupException(ErrorCode.BOOKMARK_GROUP_SAVE_CONFLICT, e);
        }
    }

    /**
     * 즐겨찾기 그룹 수정
     * @throws BookmarkGroupException 즐겨찾기 그룹 조회 실패
     */
    @Transactional
    public BookmarkGroupDto updateBookmarkGroup(BookmarkGroupDto bookmarkGroupDto) {
        try {
            BookmarkGroup bookmarkGroup = bookmarkGroupRepository.getReferenceById(bookmarkGroupDto.id());
            if (bookmarkGroupDto.groupName() != null) {
                bookmarkGroup.setGroupName(bookmarkGroupDto.groupName());
            }
            bookmarkGroupRepository.flush();
            return BookmarkGroupDto.fromEntity(bookmarkGroup);
        } catch (EntityNotFoundException e) {
            log.error("즐겨찾기 그룹을 찾을 수 없습니다. - id: {}", bookmarkGroupDto.id());
            throw new BookmarkGroupException(ErrorCode.BOOKMARK_GROUP_NOT_FOUND, e);
        }
    }

    /**
     * 즐겨찾기 그룹 삭제
     */
    @Transactional
    public void deleteBookmarkGroup(Long id) {
        try {
            bookmarkGroupRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error("즐겨찾기 그룹 삭제에 실패했습니다. - id: {}", id);
            throw new BookmarkGroupException(ErrorCode.BOOKMARK_GROUP_CANT_DELETE, e);
        }
    }
}
