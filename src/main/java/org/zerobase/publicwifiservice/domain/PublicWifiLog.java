package org.zerobase.publicwifiservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.zerobase.publicwifiservice.domain.embeded.Location;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(
        name = "public_wifi_log",
        indexes = {
                @Index(columnList = "id")
        }
)
@Entity
public class PublicWifiLog implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    @Embedded
    private Location location;
    @Setter
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    protected PublicWifiLog() {
    }

    private PublicWifiLog(Location location) {
        this.location = location;
    }

    public static PublicWifiLog of(Location location) {
        return new PublicWifiLog(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicWifiLog that)) return false;
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
