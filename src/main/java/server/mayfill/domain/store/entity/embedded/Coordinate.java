package server.mayfill.domain.store.entity.embedded;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Coordinate {

    private double xCoordinate;
    private double yCoordinate;

    public static Coordinate of(double xCoordinate, double yCoordinate) {
        return new Coordinate(xCoordinate, yCoordinate);
    }

}
