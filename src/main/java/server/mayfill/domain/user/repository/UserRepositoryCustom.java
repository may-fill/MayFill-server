package server.mayfill.domain.user.repository;

import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.entity.enumerate.SocialType;

public interface UserRepositoryCustom {
    boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType);
    User findUserById(Long userId);
    User findUserBySocialIdAndSocialType(String socialId, SocialType socialType);
}
