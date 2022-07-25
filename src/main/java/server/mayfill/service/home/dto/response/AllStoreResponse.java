package server.mayfill.service.home.dto.response;

import lombok.*;
import server.mayfill.domain.store.Store;
import server.mayfill.domain.store.embedded.Address;
import server.mayfill.domain.store.embedded.Coordinate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreResponse {

    private Long storeId;
    private String storeName;
    private String address;
    private String phoneNumber;
    private String webSite;
    private String description;
    private Coordinate coordinate;

    @Builder(access = AccessLevel.PACKAGE)
    private StoreResponse(Long storeId, String storeName, String address, String phoneNumber, String webSite, String description, Coordinate coordinate) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.description = description;
        this.coordinate = coordinate;
    }

    public static StoreResponse ofAll(Store store) {
        return StoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress().getJibun() + store.getAddress().getRoad() + store.getAddress().getDetail())
                .phoneNumber(store.getPhoneNumber())
                .webSite(store.getWebSite())
                .coordinate(store.getCoordinate())
                .build();
    }

    public static StoreResponse ofOne(Store store) {
        return StoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress().getJibun() + store.getAddress().getRoad() + store.getAddress().getDetail())
                .phoneNumber(store.getPhoneNumber())
                .webSite(store.getWebSite())
                .description(store.getDescription())
                .build();
    }

}
