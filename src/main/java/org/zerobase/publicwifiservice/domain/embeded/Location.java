package org.zerobase.publicwifiservice.domain.embeded;

import lombok.Getter;

import javax.persistence.Embeddable;

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
}
