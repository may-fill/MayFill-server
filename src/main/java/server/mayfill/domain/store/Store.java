package server.mayfill.domain.store;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.domain.common.AuditingTimeEntity;
import server.mayfill.domain.store.embedded.Address;
import server.mayfill.domain.store.embedded.Coordinate;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String description;

    @Embedded
    private Address address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "web_site")
    private String webSite;

    @Embedded
    private Coordinate coordinate;

    @Builder(access = AccessLevel.PACKAGE)
    private Store(String name, String description, Address address, String phoneNumber, String webSite, Coordinate coordinate) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.coordinate = coordinate;
    }

    public static Store of(String name, String description, Address address, String phoneNumber, String webSite, Coordinate coordinate) {
        return Store.builder()
                .name(name)
                .description(description)
                .address(address)
                .phoneNumber(phoneNumber)
                .webSite(webSite)
                .coordinate(coordinate)
                .build();
    }

}
