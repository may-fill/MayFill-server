package server.mayfill.app.image.provider.dto.request;

import server.mayfill.common.type.FileType;

public interface UploadFileRequest {

    FileType getType();

    default void validateAvailableContentType(String contentType) {
        getType().validateAvailableContentType(contentType);
    }

    default String getFileNameWithBucketDirectory(String originalFilename) {
        return getType().createUniqueFileNameWithExtension(originalFilename);
    }

}
