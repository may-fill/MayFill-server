package server.mayfill.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mayfill.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
