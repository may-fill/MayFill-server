package server.mayfill.external.client.naver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverMapGeocodeResponse {

    private List<GeoAddress> geoAddresses;

    @ToString
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeoAddress {
        private String roadAddress;
        private String jibunAddress;
        private String englishAddress;
        private String x;
        private String y;
        private double distance;
    }

}
