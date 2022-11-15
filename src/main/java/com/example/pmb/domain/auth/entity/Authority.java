package com.example.pmb.domain.auth.entity;

import com.example.pmb.domain.common.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_user_authority")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Authority extends BaseEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "authority")
    private String name;


}
