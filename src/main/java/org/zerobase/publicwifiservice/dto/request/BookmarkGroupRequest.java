package org.zerobase.publicwifiservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;

import javax.validation.constraints.Size;

@Getter
@Setter
public class BookmarkGroupRequest {
    private Long groupId;
    @Size(max = 50)
    private String groupName;

    public BookmarkGroupDto toDto() {
        return BookmarkGroupDto.of(groupId, groupName);
    }
}
