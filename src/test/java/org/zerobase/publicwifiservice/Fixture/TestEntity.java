package org.zerobase.publicwifiservice.Fixture;

import org.zerobase.publicwifiservice.domain.Bookmark;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.domain.PublicWifiLog;
import org.zerobase.publicwifiservice.domain.embeded.Address;
import org.zerobase.publicwifiservice.domain.embeded.Location;

public class TestEntity {
    public static PublicWifi getPublicWifi() {
        return PublicWifi.of("wifi", getLocation(), getAddress());
    }

    public static PublicWifiLog getPublicWifiLog() {
        return PublicWifiLog.of(getLocation());
    }

    public static BookmarkGroup getBookmarkGroup() {
        return BookmarkGroup.of("group");
    }

    public static Bookmark getBookmark(BookmarkGroup bookmarkGroup) {
        return Bookmark.of("bookmark", bookmarkGroup);
    }

    private static Location getLocation() {
        return Location.of(11.1, 11.1);
    }

    private static Address getAddress() {
        return Address.of("state", "city", "detail");
    }
}
