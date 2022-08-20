package server.mayfill.service.image.provider.dto.request;

import lombok.*;
import server.mayfill.common.type.FileType;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageUploadFileRequest implements UploadFileRequest {

    private static final String SEPARATOR = "/";
    private static final String IMAGE_CONTENT_TYPE_TYPE = "image";

    private FileType type;

    public static ImageUploadFileRequest of(FileType type) {
        return new ImageUploadFileRequest(type);
    }

    public String getFileNameWithBucketDirectory(String originalFilename) {
        return type.createUniqueFileNameWithExtension(originalFilename);
    }

}
