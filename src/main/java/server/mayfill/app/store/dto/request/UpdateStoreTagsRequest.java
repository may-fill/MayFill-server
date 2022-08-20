package server.mayfill.app.store.dto.request;

import lombok.*;
import server.mayfill.domain.store.entity.enumerate.TagName;

import javax.validation.constraints.NotNull;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateStoreTagsRequest {

    @NotNull(message = "{store.tags.notNull}")
    private Long storeId;

    @NotNull(message = "{store.tags.notNull}")
    private Set<TagName> tags;

}
