package org.zerobase.publicwifiservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Getter
@ToString(callSuper = true)
@Table(
        name = "bookmark",
        indexes = {
                @Index(columnList = "id"),
                @Index(columnList = "bookmark_name", unique = true)
        }
)
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String bookmarkName;
    @Setter
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmarkGroupId")
    private BookmarkGroup bookmarkGroup;

    @ToString.Exclude
    @Setter
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "publicWifiId")
    private PublicWifi publicWifi;

    @PrePersist
    void registeredAt() {
        this.createdAt = LocalDateTime.now();
    }

    public Bookmark() {
    }

    private Bookmark(String bookmarkName, BookmarkGroup bookmarkGroup) {
        this.bookmarkName = bookmarkName;
        this.bookmarkGroup = bookmarkGroup;
    }

    public static Bookmark of(String bookmarkName, BookmarkGroup bookmarkGroup) {
        return new Bookmark(bookmarkName, bookmarkGroup);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bookmark that)) return false;
        return this.getId() != null && Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
