package org.zerobase.publicwifiservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookmarkRequest {
    @NotNull
    private Long groupId;
    @NotNull
    private Long wifiId;
}
