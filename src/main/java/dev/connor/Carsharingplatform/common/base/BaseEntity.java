package dev.connor.Carsharingplatform.common.base;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    LocalDateTime updatedAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    String updatedBy;

    @Version
    @Column(nullable = false)
    Integer version;
}
