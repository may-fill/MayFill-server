package server.mayfill.app.store.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import server.mayfill.common.exception.custom.ConflictException;
import server.mayfill.common.exception.custom.NotFoundException;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.entity.embedded.Coordinate;
import server.mayfill.domain.store.repository.StoreRepository;

import static server.mayfill.common.exception.ResponseResult.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreServiceUtils {

    public static void validateNotExistsStore(StoreRepository storeRepository, Long storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new NotFoundException(String.format("해당하는 리필 스테이션 (%s) 이 존재하지 않습니다", storeId), NOT_FOUND_STORE_EXCEPTION);
        }
    }

    public static void validateExistsStore(StoreRepository storeRepository, String  xCoordinate, String yCoordinate) {
        if (storeRepository.existsByCoordinate(Coordinate.of(xCoordinate, yCoordinate))) {
            throw new ConflictException("이미 존재하는 리필스테이션 입니다", CONFLICT_ALREADY_EXIST_STORE_EXCEPTION);
        }
    }

    public static Store findByStoreId(StoreRepository storeRepository, Long storeId) {
        Store store = storeRepository.findStoreByStoreId(storeId);
        if (store == null) {
            throw new NotFoundException(String.format("해당하는 리필 스테이션 (%s) 이 존재하지 않습니다", storeId), NOT_FOUND_STORE_EXCEPTION);
        }
        return store;
    }

}
