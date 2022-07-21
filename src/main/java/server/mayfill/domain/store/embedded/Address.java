package server.mayfill.domain.store.embedded;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Address {

    private String road;
    private String jibun;
    private String detail;

    private Address(String road, String jibun, String detail) {
        this.road = road;
        this.jibun = jibun;
        this.detail = detail;
    }

    public static Address of(String road, String jibun, String detail) {
        return new Address(road, jibun, detail);
    }

}
