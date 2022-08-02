package server.mayfill.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.service.user.dto.request.CreateUserDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser(CreateUserDto request) {
        return userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), request.getNickname()));
    }

    @Transactional
    public void changeNickname(String newNickname, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        user.changeNickname(newNickname);
    }

    @Transactional
    public void resignUser(Long userId) {
        userRepository.delete(UserServiceUtils.findUserById(userRepository, userId));
    }

}
