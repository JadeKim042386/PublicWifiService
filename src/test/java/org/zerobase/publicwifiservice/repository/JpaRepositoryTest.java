package org.zerobase.publicwifiservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.zerobase.publicwifiservice.Fixture.TestEntity;
import org.zerobase.publicwifiservice.domain.Bookmark;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.domain.PublicWifiLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Jpa Repository 테스트")
public class JpaRepositoryTest {

    @ActiveProfiles("test")
    @DisplayName("공공와이파이 JPA 테스트")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Nested
    public class PublicWifiJpaTest {
        @Autowired private PublicWifiRepository publicWifiRepository;

        @DisplayName("findAll")
        @Test
        void findAll() {
            //given
            //when
            List<PublicWifi> wifis = publicWifiRepository.findAll();
            //then
            assertThat(wifis.size()).isEqualTo(30);
        }

        @DisplayName("거리를 기준으로 정렬하여 20개 조회")
        @Test
        void findAllByDistance() {
            //given
            double latitude = 27.9671;
            double longitude = -82.4334;
            //when
            List<PublicWifi> wifis = publicWifiRepository.findByDistance(latitude, longitude);
            //then
            assertThat(wifis.get(0).getLocation().getLatitude()).isEqualTo(latitude);
            assertThat(wifis.size()).isEqualTo(20);
        }

        @DisplayName("findById")
        @Test
        void findById() {
            //given
            Long id = 1L;
            //when
            Optional<PublicWifi> wifi = publicWifiRepository.findById(id);
            //then
            assertThat(wifi).isNotEmpty();
        }

        @DisplayName("save")
        @Test
        void save() {
            //given
            PublicWifi publicWifi = TestEntity.getPublicWifi();
            //when
            PublicWifi savedPublicWifi = publicWifiRepository.save(publicWifi);
            //then
            assertThat(savedPublicWifi.getId()).isEqualTo(31L);
        }

        @DisplayName("update")
        @Test
        void update() {
            //given
            Long id = 1L;
            String wifiName = "wifi";
            PublicWifi publicWifi = publicWifiRepository.getReferenceById(id);
            publicWifi.setWifiName(wifiName);
            LocalDateTime prevUpdatedAt = publicWifi.getUpdatedAt();
            //when
            publicWifiRepository.saveAndFlush(publicWifi);
            //then
            assertThat(publicWifi.getUpdatedAt()).isNotEqualTo(prevUpdatedAt);
            assertThat(publicWifi.getWifiName()).isEqualTo(wifiName);
        }

        @DisplayName("delete")
        @Test
        void delete() {
            //given
            Long id = 1L;
            long prevCount = publicWifiRepository.count();
            //when
            publicWifiRepository.deleteById(id);
            //then
            assertThat(publicWifiRepository.count()).isEqualTo(prevCount - 1);
        }
    }

    @ActiveProfiles("test")
    @DisplayName("공공와이파이 검색 로그 JPA 테스트")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Nested
    public class PublicWifiLogJpaTest {
        @Autowired private PublicWifiLogRepository publicWifiLogRepository;

        @DisplayName("findAll")
        @Test
        void findAll() {
            //given
            //when
            List<PublicWifiLog> logs = publicWifiLogRepository.findAll();
            //then
            assertThat(logs.size()).isEqualTo(30);
        }

        @DisplayName("findById")
        @Test
        void findById() {
            //given
            Long id = 1L;
            //when
            Optional<PublicWifiLog> log = publicWifiLogRepository.findById(id);
            //then
            assertThat(log).isNotEmpty();
        }

        @DisplayName("save")
        @Test
        void save() {
            //given
            PublicWifiLog publicWifiLog = TestEntity.getPublicWifiLog();
            //when
            PublicWifiLog savedPublicWifiLog = publicWifiLogRepository.save(publicWifiLog);
            //then
            assertThat(savedPublicWifiLog.getId()).isEqualTo(31L);
        }

        @DisplayName("delete")
        @Test
        void delete() {
            //given
            Long id = 1L;
            long prevCount = publicWifiLogRepository.count();
            //when
            publicWifiLogRepository.deleteById(id);
            //then
            assertThat(publicWifiLogRepository.count()).isEqualTo(prevCount - 1);
        }
    }

    @ActiveProfiles("test")
    @DisplayName("북마크 그룹 JPA 테스트")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Nested
    public class BookmarkGroupJpaTest {
        @Autowired private BookmarkGroupRepository bookmarkGroupRepository;

        @DisplayName("findAll")
        @Test
        void findAll() {
            //given
            //when
            List<BookmarkGroup> groups = bookmarkGroupRepository.findAll();
            //then
            assertThat(groups.size()).isEqualTo(10);
        }

        @DisplayName("findById")
        @Test
        void findById() {
            //given
            Long id = 1L;
            //when
            Optional<BookmarkGroup> group = bookmarkGroupRepository.findById(id);
            //then
            assertThat(group).isNotEmpty();
        }

        @DisplayName("save")
        @Test
        void save() {
            //given
            BookmarkGroup bookmarkGroup = TestEntity.getBookmarkGroup();
            //when
            BookmarkGroup group = bookmarkGroupRepository.save(bookmarkGroup);
            //then
            assertThat(group.getId()).isEqualTo(11L);
        }

        @DisplayName("update")
        @Test
        void update() {
            //given
            Long id = 1L;
            String groupName = "group";
            BookmarkGroup group = bookmarkGroupRepository.getReferenceById(id);
            group.setGroupName(groupName);
            LocalDateTime prevModifiedAt = group.getModifiedAt();
            //when
            bookmarkGroupRepository.saveAndFlush(group);
            //then
            assertThat(group.getModifiedAt()).isNotEqualTo(prevModifiedAt);
            assertThat(group.getGroupName()).isEqualTo(groupName);
        }

        @DisplayName("delete")
        @Test
        void delete() {
            //given
            Long id = 1L;
            long prevCount = bookmarkGroupRepository.count();
            //when
            bookmarkGroupRepository.deleteById(id);
            //then
            assertThat(bookmarkGroupRepository.count()).isEqualTo(prevCount - 1);
        }
    }

    @ActiveProfiles("test")
    @DisplayName("북마크 JPA 테스트")
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    @Nested
    public class BookmarkJpaTest {
        @Autowired private BookmarkRepository bookmarkRepository;
        @Autowired private BookmarkGroupRepository bookmarkGroupRepository;

        @DisplayName("findAll")
        @Test
        void findAll() {
            //given
            //when
            List<Bookmark> bookmark = bookmarkRepository.findAll();
            //then
            assertThat(bookmark.size()).isEqualTo(20);
        }

        @DisplayName("findById")
        @Test
        void findById() {
            //given
            Long id = 1L;
            //when
            Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
            //then
            assertThat(bookmark).isNotEmpty();
        }

        @DisplayName("save")
        @Test
        void save() {
            //given
            Bookmark bookmark = TestEntity.getBookmark(bookmarkGroupRepository.getReferenceById(1L));
            //when
            Bookmark savedBookmark = bookmarkRepository.save(bookmark);
            //then
            assertThat(savedBookmark.getId()).isEqualTo(21L);
        }

        @DisplayName("update")
        @Test
        void update() {
            //given
            Long id = 1L;
            String bookmarkName = "bookmark";
            Bookmark bookmark = bookmarkRepository.getReferenceById(id);
            bookmark.setBookmarkName(bookmarkName);
            LocalDateTime prevModifiedAt = bookmark.getModifiedAt();
            //when
            bookmarkRepository.saveAndFlush(bookmark);
            //then
            assertThat(bookmark.getModifiedAt()).isNotEqualTo(prevModifiedAt);
            assertThat(bookmark.getBookmarkName()).isEqualTo(bookmarkName);
        }

        @DisplayName("delete")
        @Test
        void delete() {
            //given
            Long id = 1L;
            long prevCount = bookmarkRepository.count();
            //when
            bookmarkRepository.deleteById(id);
            //then
            assertThat(bookmarkRepository.count()).isEqualTo(prevCount - 1);
        }
    }
}
