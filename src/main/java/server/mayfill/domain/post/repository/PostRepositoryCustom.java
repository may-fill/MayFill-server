package server.mayfill.domain.post.repository;

import server.mayfill.domain.post.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findPostByUserId(Long userId);

}
