package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.Bookmark;
import org.zerobase.publicwifiservice.utils.DateTimeUtils;

public record BookmarkDto(
        Long id,
        BookmarkGroupDto bookmarkGroup,
        PublicWifiDto publicWifi,
        String createdAt
) {
    public static BookmarkDto of(Long id, BookmarkGroupDto bookmarkGroup, PublicWifiDto publicWifi, String createdAt) {
        return new BookmarkDto(id, bookmarkGroup, publicWifi, createdAt);
    }

    public static BookmarkDto fromEntity(Bookmark bookmark) {
        return BookmarkDto.of(
                bookmark.getId(),
                BookmarkGroupDto.fromEntity(bookmark.getBookmarkGroup()),
                PublicWifiDto.fromEntity(bookmark.getPublicWifi()),
                DateTimeUtils.format(bookmark.getCreatedAt())
        );
    }

    public Bookmark toEntity() {
        return Bookmark.of(
                bookmarkGroup.toEntity(),
                publicWifi.toEntity()
        );
    }
}
