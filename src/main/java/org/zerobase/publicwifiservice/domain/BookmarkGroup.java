package org.zerobase.publicwifiservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(
        name = "bookmark_group",
        indexes = {
                @Index(columnList = "id"),
                @Index(columnList = "groupName", unique = true)
        }
)
@Entity
public class BookmarkGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String groupName;
    @Setter
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Setter
    @Column
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "bookmarkGroup")
    private final Set<Bookmark> bookmarks = new LinkedHashSet<>();

    @PrePersist
    void registeredAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.modifiedAt = LocalDateTime.now();
    }

    protected BookmarkGroup() {
    }

    private BookmarkGroup(String groupName) {
        this.groupName = groupName;
    }

    public static BookmarkGroup of(String groupName) {
        return new BookmarkGroup(groupName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookmarkGroup that)) return false;
        return this.getId() != null && Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
