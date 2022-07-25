package server.mayfill.service.home;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.common.exception.custom.NotFoundException;
import server.mayfill.domain.store.repository.StoreRepository;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreServiceUtils {

    public static void validateNotExistsStore(StoreRepository storeRepository, Long storeId) {
        if (!storeRepository.existsById(storeId)) {
            throw new NotFoundException(String.format("해당하는 리필 스테이션 (%s) 이 존재하지 않습니다", storeId), ResponseResult.NOT_FOUND_EXCEPTION);
        }
    }

}
