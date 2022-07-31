package server.mayfill.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mayfill.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
