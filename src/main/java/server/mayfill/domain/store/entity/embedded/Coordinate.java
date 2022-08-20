package server.mayfill.domain.store.embedded;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Coordinate {

    private String xCoordinate;
    private String yCoordinate;

    public static Coordinate of(String xCoordinate, String yCoordinate) {
        return new Coordinate(xCoordinate, yCoordinate);
    }

}
