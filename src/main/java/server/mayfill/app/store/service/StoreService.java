package server.mayfill.app.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.mayfill.app.image.provider.UploadProvider;
import server.mayfill.app.image.provider.dto.request.ImageUploadFileRequest;
import server.mayfill.app.store.dto.request.AddStoreRequest;
import server.mayfill.app.store.dto.request.UpdateStoreTagsRequest;
import server.mayfill.common.type.FileType;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.store.repository.StoreRepository;
import server.mayfill.domain.store.repository.TagRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final TagRepository tagRepository;
    private final UploadProvider uploadProvider;

    @Transactional
    public void registerStoreWithImage(AddStoreRequest request, MultipartFile imageFile) {
        StoreServiceUtils.validateExistsStore(storeRepository, request.getXCoordinate(), request.getYCoordinate());
        String imageUrl = uploadProvider.uploadFile(ImageUploadFileRequest.of(FileType.STORE_IMAGE), imageFile);
        Store store = storeRepository.save(request.toStoreEntity(imageUrl));
        store.addTags(tagRepository.findByTagName(request.getTags()));
    }

    @Transactional
    public void updateStore(UpdateStoreTagsRequest request, MultipartFile imageFile) {
        Store store = StoreServiceUtils.findByStoreId(storeRepository, request.getStoreId());
        // TODO : store 전체적인 정보 수정
        store.updateTags(tagRepository.findByTagName(request.getTags()));
        if (!Objects.isNull(imageFile) && !imageFile.isEmpty()) { // imageFile 이 있으면
            String imageUrl = uploadProvider.uploadFile(ImageUploadFileRequest.of(FileType.STORE_IMAGE), imageFile);
            store.updateImageUrl(imageUrl);
        }
    }

}
