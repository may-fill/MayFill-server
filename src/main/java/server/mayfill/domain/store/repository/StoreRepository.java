package server.mayfill.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mayfill.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
