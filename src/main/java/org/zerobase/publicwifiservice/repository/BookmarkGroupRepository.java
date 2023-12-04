package org.zerobase.publicwifiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerobase.publicwifiservice.domain.BookmarkGroup;

public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
}
