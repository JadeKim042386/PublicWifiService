package org.zerobase.publicwifiservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.Bookmark;
import org.zerobase.publicwifiservice.dto.BookmarkDto;
import org.zerobase.publicwifiservice.exception.BookmarkException;
import org.zerobase.publicwifiservice.exception.ErrorCode;
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

    /**
     * 즐겨찾기 저장
     * @param bookmarkGroupId 즐겨찾기 그룹 ID
     * @param publicWifiId 공공와이파이 ID
     * @throws BookmarkException 즐겨찾기 저장 실패
     */
    @Transactional
    public void saveBookmark(Long bookmarkGroupId, Long publicWifiId) {
        try {
            bookmarkRepository.save(
                    Bookmark.of(
                            bookmarkGroupRepository.getReferenceById(bookmarkGroupId),
                            publicWifiRepository.getReferenceById(publicWifiId)
                    )
            );
        } catch (IllegalArgumentException e) {
            log.error("즐겨찾기 저장에 실패했습니다. - 그룹 id: {}, 와이파이 id: {}", bookmarkGroupId, publicWifiId);
            throw new BookmarkException(ErrorCode.BOOKMARK_SAVE_FAILED, e);
        } catch (OptimisticLockingFailureException e) {
            log.error("즐겨찾기 저장 중 Optimistic Lock 충돌이 일어났습니다. - 그룹 id: {}, 와이파이 id: {}", bookmarkGroupId, publicWifiId);
            throw new BookmarkException(ErrorCode.BOOKMARK_SAVE_CONFLICT, e);
        }

    }

    /**
     * 즐겨찾기 삭제
     * @throws BookmarkException 즐겨찾기 삭제 실패
     */
    @Transactional
    public void deleteBookmark(Long id) {
        try {
            bookmarkRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error("즐겨찾기 삭제에 실패했습니다. - id: {}", id);
            throw new BookmarkException(ErrorCode.BOOKMARK_CANT_DELETE, e);
        }
    }
}
