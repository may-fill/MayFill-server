package server.mayfill.domain.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.entity.embedded.Coordinate;

import java.util.List;

import static server.mayfill.domain.store.entity.QStore.*;

@RequiredArgsConstructor
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public boolean existsByCoordinate(Coordinate coordinate) {
        return query
                .selectOne()
                .where(store.coordinate.eq(coordinate))
                .fetchFirst() != null;
    }

    @Override
    public List<Store> findAllStore() {
        return query
                .selectFrom(store)
                .orderBy(store.id.asc())
                .fetch();
    }

    @Override
    public Store findStoreByStoreId(Long storeId) {
        return query
                .selectFrom(store)
                .where(store.id.eq(storeId))
                .fetchOne();
    }

}
