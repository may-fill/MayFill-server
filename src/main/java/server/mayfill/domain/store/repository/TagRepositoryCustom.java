package server.mayfill.domain.store.repository;

import server.mayfill.domain.store.entity.Tag;
import server.mayfill.domain.store.entity.enumerate.TagName;

import java.util.List;
import java.util.Set;

public interface TagRepositoryCustom {
    List<Tag> findByTagName(Set<TagName> tagNames);
}
