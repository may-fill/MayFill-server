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
    public void resign(Long userId) {
        userRepository.delete(UserServiceUtils.findUserById(userRepository, userId));
    }

}
