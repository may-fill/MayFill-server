package server.mayfill.service.home.dto.response;

import lombok.*;
import server.mayfill.domain.store.Store;
import server.mayfill.domain.store.embedded.Coordinate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AllStoreResponse {

    private Long storeId;
    private String storeName;
    private String address;
    private String phoneNumber;
    private String webSite;
    private Coordinate coordinate;

    @Builder(access = AccessLevel.PACKAGE)
    private AllStoreResponse(Long storeId, String storeName, String address, String phoneNumber, String webSite, String description, Coordinate coordinate) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.coordinate = coordinate;
    }

    public static AllStoreResponse of(Store store) {
        return AllStoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress().getJibun() + store.getAddress().getRoad() + store.getAddress().getDetail())
                .phoneNumber(store.getPhoneNumber())
                .webSite(store.getWebSite())
                .coordinate(store.getCoordinate())
                .build();
    }


}
