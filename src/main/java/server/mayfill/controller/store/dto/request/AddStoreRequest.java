package server.mayfill.controller.store.dto.request;

import lombok.*;
import server.mayfill.domain.store.Store;
import server.mayfill.domain.store.embedded.Coordinate;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddStoreRequest {

    @NotBlank(message = "${store.name.notBlank}")
    private String name;

    @NotBlank(message = "${store.address.notBlank}")
    private String roadAddress;

    @NotBlank(message = "${store.detail.notBlank}")
    private String detail;

    @NotBlank(message = "${store.description.notBlank}")
    private String description;

    @NotBlank(message = "${store.phoneNumber.notBlank}")
    private String phoneNumber;

    @NotBlank(message = "${store.webSite.notBlank}")
    private String webSite;

    @NotBlank(message = "${store.xCoordinate.notBlank}")
    private String xCoordinate;

    @NotBlank(message = "${store.yCoordinate.notBlank}")
    private String yCoordinate;

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    public AddStoreRequest(String name, String roadAddress, String detail, String description, String phoneNumber,
                           String webSite, String xCoordinate, String yCoordinate) {
        this.name = name;
        this.roadAddress = roadAddress;
        this.detail = detail;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Store toStoreEntity() {
        return Store.newInstance(name, description, roadAddress, phoneNumber, webSite, Coordinate.of(xCoordinate, yCoordinate));
    }

}
