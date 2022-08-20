package server.mayfill.service.home.dto.response;

import lombok.*;
import server.mayfill.domain.store.Store;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OneStoreResponse {

    private Long storeId;
    private String storeName;
    private String address;
    private String phoneNumber;
    private String webSite;
    private String description;

    @Builder(access = AccessLevel.PACKAGE)
    private OneStoreResponse(Long storeId, String storeName, String address, String phoneNumber, String webSite, String description) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.description = description;
    }

    public static OneStoreResponse of(Store store) {
        return OneStoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .webSite(store.getWebSite())
                .description(store.getDescription())
                .build();
    }

}
