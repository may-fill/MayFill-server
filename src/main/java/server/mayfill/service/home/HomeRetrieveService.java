package server.mayfill.service.home;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.domain.store.repository.StoreRepository;
import server.mayfill.service.home.dto.response.AllStoreResponse;
import server.mayfill.service.home.dto.response.OneStoreResponse;

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
                .map(AllStoreResponse::of)
                .collect(Collectors.toList());
    }

    public OneStoreResponse retrieveOneStore(Long storeId) {
        return OneStoreResponse.of(storeRepository.findStoreByStoreId(storeId));
    }

}
