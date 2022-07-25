package server.mayfill.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;

import static server.mayfill.domain.user.QUser.*;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public User findUserById(Long userId) {
        return query
                .selectFrom(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }

    @Override
    public User findUserBySocialIdAndSocialType(String socialId, SocialType socialType) {
        return query
                .selectFrom(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                )
                .fetchOne();
    }

}
