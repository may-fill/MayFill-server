package server.mayfill.app.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.app.user.dto.request.CreateUserDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        return userRepository.save(User.newInstance(request));
    }

    @Transactional
    public void changeNickname(String newNickname, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        user.changeNickname(newNickname);
    }

    @Transactional
    public void upgradeUserGrade(Long userId, int count) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        user.changeGrade(count);
    }

    @Transactional
    public void resignUser(Long userId) {
        userRepository.delete(UserServiceUtils.findUserById(userRepository, userId));
    }

}
