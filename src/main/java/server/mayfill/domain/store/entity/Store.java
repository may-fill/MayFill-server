package server.mayfill.domain.store.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.domain.common.AuditingTimeEntity;
import server.mayfill.domain.store.entity.embedded.Coordinate;
import server.mayfill.domain.store.entity.enumerate.TagName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<StoreTag> tags = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private Store(String name, String description, String address, String phoneNumber, String webSite, String imageUrl, Coordinate coordinate) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.imageUrl = imageUrl;
        this.coordinate = coordinate;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static Store newInstance(String name, String description, String address, String phoneNumber, String webSite, String imageUrl, Coordinate coordinate) {
        return Store.builder()
                .name(name)
                .description(description)
                .address(address)
                .phoneNumber(phoneNumber)
                .webSite(webSite)
                .imageUrl(imageUrl)
                .coordinate(coordinate)
                .build();
    }

    public List<String> getStoreTags() {
        return tags.stream()
                .map(StoreTag::getTag)
                .map(Tag::getTagName)
                .map(TagName::getValue)
                .collect(Collectors.toList());
    }

    public void addTags(List<Tag> tags) {
        for (Tag tag : tags) {
            this.addTag(tag);
        }
    }

    private void addTag(Tag tag) {
        StoreTag storeTag = StoreTag.of(this, tag);
        this.tags.add(storeTag);
    }

    public void updateTags(List<Tag> changedTags) {
        this.tags.removeIf(storeTag -> !changedTags.contains(storeTag.getTag()));
        List<Tag> hasTags = getTags();
        addTags(changedTags.stream()
                .filter(tag -> !hasTags.contains(tag))
                .collect(Collectors.toList()));
    }

    private List<Tag> getTags() {
        return this.tags.stream()
                .map(StoreTag::getTag)
                .collect(Collectors.toList());
    }

}
