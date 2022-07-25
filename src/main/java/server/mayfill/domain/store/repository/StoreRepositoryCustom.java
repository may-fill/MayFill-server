package server.mayfill.domain.store.repository;

import server.mayfill.domain.store.Store;

import java.util.List;

public interface StoreRepositoryCustom {

    List<Store> findAllStore();
    Store findStoreByStoreId(Long storeId);

}
