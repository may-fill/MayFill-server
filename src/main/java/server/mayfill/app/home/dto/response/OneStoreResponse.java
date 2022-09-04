package server.mayfill.app.home.dto.response;

import lombok.*;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.entity.enumerate.TagName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private String imageUrl;
    private List<String> tags = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private OneStoreResponse(Long storeId, String storeName, String address, String phoneNumber, String webSite, String description, String imageUrl) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static OneStoreResponse from(Store store) {
        OneStoreResponse response = OneStoreResponse.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .webSite(store.getWebSite())
                .description(store.getDescription())
                .imageUrl(store.getImageUrl())
                .build();
        response.tags.addAll(store.getStoreTags());
        return response;
    }

    private static List<TagValueResponseDto> toTagValueResponseDto(List<TagName> tags) {
        return tags.stream()
                .map(TagValueResponseDto::from)
                .collect(Collectors.toList());
    }

}
