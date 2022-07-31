package server.mayfill.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.mayfill.common.type.FileType;
import server.mayfill.controller.post.dto.request.AddPostRequest;
import server.mayfill.domain.post.repository.PostRepository;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.service.image.provider.UploadProvider;
import server.mayfill.service.image.provider.dto.request.ImageUploadFileRequest;
import server.mayfill.service.user.UserServiceUtils;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UploadProvider uploadProvider;

    @Transactional
    public void addPostWithImage(AddPostRequest request, MultipartFile imageFile, Long userId) {
        String imageUrl = uploadProvider.uploadFile(ImageUploadFileRequest.of(FileType.POST_IMAGE), imageFile);
        postRepository.save(request.toPostEntity(UserServiceUtils.findUserById(userRepository, userId), imageUrl));
    }

}
