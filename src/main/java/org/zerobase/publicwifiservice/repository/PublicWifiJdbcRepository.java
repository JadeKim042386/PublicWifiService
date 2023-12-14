package org.zerobase.publicwifiservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerobase.publicwifiservice.domain.PublicWifi;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class PublicWifiJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<PublicWifi> wifis) {
        String sql = "INSERT INTO public_wifi (wifi_name, latitude, longitude, addr_state, addr_city, addr_detail, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                wifis,
                wifis.size(),
                (PreparedStatement ps, PublicWifi wifi) -> {
                    ps.setString(1, wifi.getWifiName());
                    ps.setDouble(2, wifi.getLocation().getLatitude());
                    ps.setDouble(3, wifi.getLocation().getLongitude());
                    ps.setString(4, wifi.getAddress().getAddrState());
                    ps.setString(5, wifi.getAddress().getAddrCity());
                    ps.setString(6, wifi.getAddress().getAddrDetail());
                    ps.setTimestamp(7, Timestamp.from(Instant.now()));
                });
    }
}
