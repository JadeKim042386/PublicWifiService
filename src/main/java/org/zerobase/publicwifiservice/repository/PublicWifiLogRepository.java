package org.zerobase.publicwifiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerobase.publicwifiservice.domain.PublicWifiLog;

public interface PublicWifiLogRepository extends JpaRepository<PublicWifiLog, Long> {
}
