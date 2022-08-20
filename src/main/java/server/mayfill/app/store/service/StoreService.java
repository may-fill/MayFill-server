package server.mayfill.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.controller.store.dto.request.AddStoreRequest;
import server.mayfill.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public void registerStore(AddStoreRequest request) {
        storeRepository.save(request.toStoreEntity());
    }

}
