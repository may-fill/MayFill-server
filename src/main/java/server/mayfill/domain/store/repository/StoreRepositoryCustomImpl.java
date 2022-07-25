package server.mayfill.domain.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.mayfill.domain.store.Store;

import java.util.List;

import static server.mayfill.domain.store.QStore.*;

@RequiredArgsConstructor
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Store> findAllStore() {
        return query.selectFrom(store).fetch();
    }

    @Override
    public Store findStoreByStoreId(Long storeId) {
        return query
                .selectFrom(store)
                .where(store.id.eq(storeId))
                .fetchOne();
    }

}
