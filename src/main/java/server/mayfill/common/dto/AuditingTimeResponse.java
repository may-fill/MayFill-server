package server.mayfill.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import server.mayfill.domain.common.AuditingTimeEntity;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AuditingTimeResponse {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    protected void setBaseTime(AuditingTimeEntity auditingTimeEntity) {
        this.createdAt = auditingTimeEntity.getCreatedAt();
        this.updatedAt = auditingTimeEntity.getUpdatedAt();
    }

}
