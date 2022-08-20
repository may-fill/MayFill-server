package server.mayfill.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mayfill.domain.store.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {
}
