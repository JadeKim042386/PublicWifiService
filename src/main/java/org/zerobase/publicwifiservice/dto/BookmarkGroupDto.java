package org.zerobase.publicwifiservice.dto;

import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.utils.DateTimeUtils;

public record BookmarkGroupDto(
        Long id,
        String groupName,
        String createdAt,
        String modifiedAt
) {
    public static BookmarkGroupDto of(Long id, String groupName, String createdAt, String modifiedAt) {
        return new BookmarkGroupDto(id, groupName, createdAt, modifiedAt);
    }

    public static BookmarkGroupDto fromEntity(BookmarkGroup entity) {
        return BookmarkGroupDto.of(
                entity.getId(),
                entity.getGroupName(),
                DateTimeUtils.format(entity.getCreatedAt()),
                DateTimeUtils.format(entity.getModifiedAt())
        );
    }
}
