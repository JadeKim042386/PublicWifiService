package org.zerobase.publicwifiservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.zerobase.publicwifiservice.domain.embeded.Address;
import org.zerobase.publicwifiservice.domain.embeded.Location;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(
        name = "public_wifi",
        indexes = {
                @Index(columnList = "id"),
                @Index(columnList = "wifiName", unique = true)
        }
)
@Entity
public class PublicWifi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String wifiName;
    @Setter
    @Column(nullable = false)
    @Embedded
    private Location location;
    @Setter
    @Column(nullable = false)
    @Embedded
    private Address address;
    @Setter
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ToString.Exclude
    @OneToOne(mappedBy = "publicWifi", cascade = CascadeType.ALL)
    private Bookmark bookmark;

    @PrePersist
    @PreUpdate
    void updatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    protected PublicWifi() {
    }

    private PublicWifi(String wifiName, Location location, Address address) {
        this.wifiName = wifiName;
        this.location = location;
        this.address = address;
    }

    public static PublicWifi of(String wifiName, Location location, Address address) {
        return new PublicWifi(wifiName, location, address);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicWifi that)) return false;
        return this.getId() != null && Objects.equals(this.getId(), that.getId()) ||
                this.getWifiName() != null && Objects.equals(this.getWifiName(), that.getWifiName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getWifiName());
    }
}
