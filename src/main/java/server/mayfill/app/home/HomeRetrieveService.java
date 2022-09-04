package server.mayfill.app.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.domain.store.repository.StoreRepository;
import server.mayfill.app.home.dto.response.AllStoreResponse;
import server.mayfill.app.home.dto.response.OneStoreResponse;
import server.mayfill.app.store.service.StoreServiceUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeRetrieveService {

    private final StoreRepository storeRepository;

    public List<AllStoreResponse> retrieveAllStore() {
        return storeRepository.findAllStore()
                .stream()
                .map(AllStoreResponse::from)
                .collect(Collectors.toList());
    }

    public OneStoreResponse retrieveOneStore(Long storeId) {
        StoreServiceUtils.validateNotExistsStore(storeRepository, storeId);
        return OneStoreResponse.from(storeRepository.findStoreByStoreId(storeId));
    }

}
