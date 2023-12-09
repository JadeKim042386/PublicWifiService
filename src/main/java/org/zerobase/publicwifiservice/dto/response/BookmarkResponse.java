package org.zerobase.publicwifiservice.dto.response;

import lombok.Getter;
import org.zerobase.publicwifiservice.dto.BookmarkDto;

@Getter
public class BookmarkResponse {
    private Long id;
    private String groupName;
    private String wifiName;
    private String createdAt;

    protected BookmarkResponse() {
    }

    private BookmarkResponse(Long id, String groupName, String wifiName, String createdAt) {
        this.id = id;
        this.groupName = groupName;
        this.wifiName = wifiName;
        this.createdAt = createdAt;
    }

    public static BookmarkResponse of(Long id, String groupName, String wifiName, String createdAt) {
        return BookmarkResponse.of(id, groupName, wifiName, createdAt);
    }

    public static BookmarkResponse fromDto(BookmarkDto dto) {
        return BookmarkResponse.of(
                dto.id(),
                dto.bookmarkGroup().groupName(),
                dto.publicWifi().wifiName(),
                dto.createdAt()
        );
    }
}
