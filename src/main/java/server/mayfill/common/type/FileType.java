package server.mayfill.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.mayfill.common.exception.custom.ValidationException;
import server.mayfill.common.util.FileUtils;
import server.mayfill.common.util.UuidUtils;

import static server.mayfill.common.exception.ResponseResult.*;

@Getter
@RequiredArgsConstructor
public enum FileType {

    POST_IMAGE("(유저) 인증 이미지", "/post/certification/v1", FileContentType.IMAGE),
    ;

    private final String description;
    private final String directory;
    private final FileContentType contentType;

    public void validateAvailableContentType(String contentType) {
        this.contentType.validateAvailableContentType(contentType);
    }

    /**
     * 파일의 기존의 확장자를 유지한 채, 유니크한 파일의 이름을 반환합니다.
     */
    public String createUniqueFileNameWithExtension(String originFileName) {
        if (originFileName == null) {
            throw new ValidationException("잘못된 파일의 originFilename 입니다", FORBIDDEN_FILE_TYPE_EXCEPTION);
        }
        String extension = FileUtils.getFileExtension(originFileName);
        return getFileNameWithDirectory(UuidUtils.generate().concat(extension));
    }

    private String getFileNameWithDirectory(String fileName) {
        return this.directory.concat(fileName);
    }

}
