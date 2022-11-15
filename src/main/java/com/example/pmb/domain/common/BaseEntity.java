package com.example.pmb.domain.common;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "reg_date",updatable = false)
    private Date regDate;

    @CreatedBy
    @Column(name = "reg_user",updatable = false)
    private String regUser;

    @LastModifiedDate
    @Column(name = "mod_date")
    private Date modDate;

    @LastModifiedBy
    @Column(name = "mod_user")
    private String modUser;


}
