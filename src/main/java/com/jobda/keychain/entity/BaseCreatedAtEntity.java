package com.jobda.keychain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseCreatedAtEntity {

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
