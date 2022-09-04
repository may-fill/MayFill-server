package server.mayfill.app.home;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.app.home.dto.response.AllStoreResponse;
import server.mayfill.app.home.dto.response.OneStoreResponse;
import server.mayfill.common.exception.custom.NotFoundException;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.repository.StoreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
public class HomeRetrieveServiceTest {

    @Autowired
    private HomeRetrieveService homeRetrieveService;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void 홈_지도_페이지_모든_리필스테이션_조회_성공() {
        // when
        List<AllStoreResponse> allStoreResponse = homeRetrieveService.retrieveAllStore();

        // then
        List<Store> allStore = storeRepository.findAllStore();
        for (int index = 0; index < allStoreResponse.size(); index++) {
            AllStoreResponse response = allStoreResponse.get(index);
            Store find = allStore.get(index);
            assertAll(
                    () -> assertThat(response).isNotNull(),
                    () -> assertEquals(response.getStoreId(), find.getId()),
                    () -> assertEquals(response.getStoreName(), find.getName()),
                    () -> assertEquals(response.getAddress(), find.getAddress()),
                    () -> assertEquals(response.getPhoneNumber(), find.getPhoneNumber()),
                    () -> assertEquals(response.getWebSite(), find.getWebSite()),
                    () -> assertEquals(response.getCoordinate(), find.getCoordinate())
            );
        }

    }

    @Test
    void 홈_핀_선택시_하나의_리필스테이션_정보_조회_성공() {
       // when
        OneStoreResponse response = homeRetrieveService.retrieveOneStore(1L);

        // then
        Store find = storeRepository.findStoreByStoreId(1L);
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertEquals(response.getStoreId(), find.getId()),
                () -> assertEquals(response.getStoreName(), find.getName()),
                () -> assertEquals(response.getAddress(), find.getAddress()),
                () -> assertEquals(response.getPhoneNumber(), find.getPhoneNumber()),
                () -> assertEquals(response.getWebSite(), find.getWebSite()),
                () -> assertEquals(response.getDescription(), find.getDescription()),
                () -> assertEquals(response.getTags(), find.getStoreTags())
        );

    }

    @Test
    void 홈_핀_선택시_리필스테이션_정보_조회_실패() {
        Long notExistStoreId = 0L;
        assertThrows(NotFoundException.class, () -> homeRetrieveService.retrieveOneStore(notExistStoreId));
    }

}
