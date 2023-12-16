package org.zerobase.publicwifiservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Getter
@Table(
        name = "bookmark",
        indexes = {
                @Index(columnList = "id")
        }
)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Bookmark implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Setter
    @Column
    @LastModifiedDate
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

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
