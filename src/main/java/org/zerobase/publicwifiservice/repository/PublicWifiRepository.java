package org.zerobase.publicwifiservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerobase.publicwifiservice.domain.PublicWifi;

import java.util.List;

public interface PublicWifiRepository extends JpaRepository<PublicWifi, Long> {

    //Haversine formula to calculate distance
    //https://en.wikipedia.org/wiki/Haversine_formula
    @EntityGraph(attributePaths = {"bookmark"})
    @Query("SELECT p " +
            "FROM PublicWifi p " +
            "ORDER BY " +
                "6371 * 2 * ASIN(SQRT(" +
                    "POWER(SIN(RADIANS(p.location.latitude - :latitude) / 2), 2) + " +
                    "COS(RADIANS(:latitude)) * COS(RADIANS(p.location.latitude)) * " +
                    "POWER(SIN(RADIANS(p.location.longitude - :longitude) / 2), 2)" +
                    "))"
    )
    List<PublicWifi> findAllWithBookmarkUsingFetchJoin(@Param("latitude") double latitude,
                                                        @Param("longitude") double longitude,
                                                        Pageable pageable);

    @Query("SELECT new java.lang.String(p.wifiName) FROM PublicWifi p")
    List<String> findAllWifiName();
}
