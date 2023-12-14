package org.zerobase.publicwifiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerobase.publicwifiservice.domain.PublicWifi;
import org.zerobase.publicwifiservice.domain.embeded.Address;
import org.zerobase.publicwifiservice.domain.embeded.Location;

import java.util.List;
import java.util.Set;

public interface PublicWifiRepository extends JpaRepository<PublicWifi, Long> {

    //Haversine formula to calculate distance
    //https://en.wikipedia.org/wiki/Haversine_formula
    @Query(nativeQuery = true,
            value =
            "SELECT * " +
            "FROM public_wifi p " +
            "ORDER BY " +
                "6371 * 2 * ASIN(SQRT(" +
                    "POWER(SIN(RADIANS(p.latitude - :latitude) / 2), 2) + " +
                    "COS(RADIANS(:latitude)) * COS(RADIANS(p.latitude)) * " +
                    "POWER(SIN(RADIANS(p.longitude - :longitude) / 2), 2)" +
                    ")) ASC " +
            "LIMIT 20"
    )
    List<PublicWifi> findByDistance(@Param("latitude") double latitude,
                                    @Param("longitude") double longitude);

    boolean existsByWifiName(String wifiName);

    @Query("SELECT new java.lang.String(p.wifiName) FROM PublicWifi p")
    List<String> findAllWifiName();
}
