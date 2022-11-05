package server.mayfill.app.home.dto.response;

import lombok.Getter;
import lombok.ToString;
import server.mayfill.domain.store.entity.enumerate.TagName;

@ToString
@Getter
public class TagValueResponseDto {

    private String value;

    private TagValueResponseDto() {}

    private TagValueResponseDto(final String value) {
        this.value = value;
    }

    public static TagValueResponseDto from(TagName tagName) {
        return new TagValueResponseDto(tagName.getValue());
    }

}
