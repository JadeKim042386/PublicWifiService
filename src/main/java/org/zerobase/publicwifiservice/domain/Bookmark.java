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
                @Index(columnList = "id")
        }
)
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Setter
    @Column
    private LocalDateTime modifiedAt;

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

    @PreUpdate
    void updatedAt() {
        this.modifiedAt = LocalDateTime.now();
    }

    public Bookmark() {
    }

    private Bookmark(BookmarkGroup bookmarkGroup, PublicWifi publicWifi) {
        this.bookmarkGroup = bookmarkGroup;
        this.publicWifi = publicWifi;
    }

    public static Bookmark of(BookmarkGroup bookmarkGroup, PublicWifi publicWifi) {
        return new Bookmark(bookmarkGroup, publicWifi);
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
