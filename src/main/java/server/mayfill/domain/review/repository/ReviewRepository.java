package server.mayfill.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mayfill.domain.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
