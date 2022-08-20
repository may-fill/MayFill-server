package server.mayfill.domain.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.mayfill.domain.store.entity.Tag;
import server.mayfill.domain.store.entity.enumerate.TagName;

import java.util.List;
import java.util.Set;

import static server.mayfill.domain.store.entity.QTag.*;

@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {

    private final JPAQueryFactory query;


    @Override
    public List<Tag> findByTagName(Set<TagName> tagNames) {
        return query
                .selectFrom(tag)
                .where(tag.tagName.in(tagNames))
                .fetch();
    }

}
