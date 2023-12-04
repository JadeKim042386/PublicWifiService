package org.zerobase.publicwifiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerobase.publicwifiservice.domain.PublicWifi;

public interface PublicWifiRepository extends JpaRepository<PublicWifi, Long> {
}
