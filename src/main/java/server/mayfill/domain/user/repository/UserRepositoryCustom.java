package server.mayfill.domain.user.repository;

import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;

public interface UserRepositoryCustom {

    User findUserById(Long userId);
    User findUserBySocialIdAndSocialType(String socialId, SocialType socialType);

}
