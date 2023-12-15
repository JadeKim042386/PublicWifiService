package org.zerobase.publicwifiservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

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
public class BookmarkGroup implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String groupName;
    @Setter
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Setter
    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "bookmarkGroup")
    private final Set<Bookmark> bookmarks = new LinkedHashSet<>();

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

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
