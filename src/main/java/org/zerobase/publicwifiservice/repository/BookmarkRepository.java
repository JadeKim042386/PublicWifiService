package org.zerobase.publicwifiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerobase.publicwifiservice.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
