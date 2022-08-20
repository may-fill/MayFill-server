package server.mayfill.app.store.dto.request;

import lombok.*;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.entity.embedded.Coordinate;
import server.mayfill.domain.store.entity.enumerate.TagName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddStoreRequest {

    @NotBlank(message = "{store.name.notBlank}")
    private String name;

    @NotBlank(message = "{store.address.notBlank}")
    private String roadAddress;

    @NotBlank(message = "{store.detail.notBlank}")
    private String detail;

    @NotBlank(message = "{store.description.notBlank}")
    private String description;

    @NotBlank(message = "{store.phoneNumber.notBlank}")
    private String phoneNumber;

    @NotBlank(message = "{store.webSite.notBlank}")
    private String webSite;

    @NotBlank(message = "{store.xCoordinate.notBlank}")
    private String xCoordinate;

    @NotBlank(message = "{store.yCoordinate.notBlank}")
    private String yCoordinate;

    @NotNull(message = "{store.tags.notNull}")
    private Set<TagName> tags;

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    public AddStoreRequest(String name, String roadAddress, String detail, String description, String phoneNumber,
                           String webSite, String xCoordinate, String yCoordinate,  Set<TagName> tags) {
        this.name = name;
        this.roadAddress = roadAddress;
        this.detail = detail;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.tags = tags;
    }

    public Store toStoreEntity(String imageUrl) {
        return Store.newInstance(name, description, roadAddress, phoneNumber, webSite, imageUrl, Coordinate.of(xCoordinate, yCoordinate));
    }

}
