package org.zerobase.publicwifiservice.domain.embeded;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Location {
    private Double latitude;
    private Double longitude;

    protected Location() {
    }

    private Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Location of(Double latitude, Double longitude) {
        return new Location(latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location that)) return false;
        return this.getLatitude() != null && Objects.equals(this.getLatitude(), that.getLatitude()) &&
                this.getLongitude() != null && Objects.equals(this.getLongitude(), that.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLatitude(), this.getLongitude());
    }
}
