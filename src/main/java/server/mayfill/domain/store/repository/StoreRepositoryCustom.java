package server.mayfill.domain.store.repository;

import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.entity.embedded.Coordinate;

import java.util.List;

public interface StoreRepositoryCustom {
    boolean existsByCoordinate(Coordinate coordinate);
    List<Store> findAllStore();
    Store findStoreByStoreId(Long storeId);
}
