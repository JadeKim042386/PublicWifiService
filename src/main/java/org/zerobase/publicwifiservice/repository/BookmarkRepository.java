package org.zerobase.publicwifiservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerobase.publicwifiservice.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @EntityGraph(attributePaths = {"publicWifi", "bookmarkGroup"})
    @Override
    Page<Bookmark> findAll(Pageable pageable);
}
