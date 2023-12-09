package org.zerobase.publicwifiservice.Fixture;

import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.dto.BookmarkDto;
import org.zerobase.publicwifiservice.dto.BookmarkGroupDto;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.dto.response.WifiApiResponse;
import org.zerobase.publicwifiservice.utils.DateTimeUtils;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

public class TestDto {
    public static PublicWifiDto getPublicWifiDto() {
        return PublicWifiDto.of(
                33L,
                "wifi",
                11.1,
                11.1,
                "state",
                "city",
                "detail",
                12.2,
                DateTimeUtils.format(LocalDateTime.now())
        );
    }

    public static WifiApiResponse getWifiApiResponse() {
        return WifiApiResponse.of("", "wifi", 0D, 0D, "state", "city", "detail");
    }

    public static BookmarkGroupDto getBookmarkGroupDto() {
        return BookmarkGroupDto.of(
                1L,
                "group",
                DateTimeUtils.format(LocalDateTime.now()),
                DateTimeUtils.format(LocalDateTime.now())
        );
    }

    public static BookmarkDto getBookmarkDto() {
        return BookmarkDto.of(
                1L,
                getBookmarkGroupDto(),
                getPublicWifiDto(),
                DateTimeUtils.format(LocalDateTime.now())
        );
    }
}
