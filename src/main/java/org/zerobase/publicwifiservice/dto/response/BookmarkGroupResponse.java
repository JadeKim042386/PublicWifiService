package org.zerobase.publicwifiservice.dto.response;

import lombok.Getter;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;

@Getter
public class BookmarkGroupResponse {
    private Long id;
    private String groupName;
    private String createdAt;
    private String modifiedAt;

    protected BookmarkGroupResponse() {
    }

    private BookmarkGroupResponse(Long id, String groupName, String createdAt, String modifiedAt) {
        this.id = id;
        this.groupName = groupName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static BookmarkGroupResponse of(Long id, String groupName, String createdAt, String modifiedAt) {
        return new BookmarkGroupResponse(id, groupName, createdAt, modifiedAt);
    }

    public static BookmarkGroupResponse fromDto(BookmarkGroupDto dto) {
        return BookmarkGroupResponse.of(
                dto.id(),
                dto.groupName(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
