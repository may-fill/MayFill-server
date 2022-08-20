package server.mayfill.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.mayfill.common.type.FileType;
import server.mayfill.app.post.dto.request.AddPostRequest;
import server.mayfill.domain.post.repository.PostRepository;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.app.image.provider.UploadProvider;
import server.mayfill.app.image.provider.dto.request.ImageUploadFileRequest;
import server.mayfill.service.post.dto.response.AllPostResponse;
import server.mayfill.service.post.dto.response.GradeResponse;
import server.mayfill.service.post.dto.response.MyPostResponse;
import server.mayfill.service.user.UserServiceUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UploadProvider uploadProvider;

    @Transactional
    public GradeResponse addPostWithImage(AddPostRequest request, MultipartFile imageFile, Long userId) {
        String imageUrl = uploadProvider.uploadFile(ImageUploadFileRequest.of(FileType.POST_IMAGE), imageFile);
        User user = UserServiceUtils.findUserById(userRepository, userId);
        user.addPosts(postRepository.save(request.toPostEntity(user, imageUrl)));
        user.changeGrade(user.getPosts().size() - 1);
        return GradeResponse.of(user.getGrade());
    }

    public List<MyPostResponse> retrieveMyPost(Long userId) {
        return postRepository
                .findPostByUserId(userId)
                .stream()
                .map(MyPostResponse::of)
                .collect(Collectors.toList());
    }

    public List<AllPostResponse> retrieveAllPost() {
        return postRepository
                .findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream().map(AllPostResponse::of)
                .collect(Collectors.toList());
    }

}
