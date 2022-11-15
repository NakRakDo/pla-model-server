package com.example.pmb.domain.auth.entity;


import com.example.pmb.domain.common.BaseEntity;
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
@Table(name = "test_member_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberInfo extends BaseEntity {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int password;
    @Column(name = "email")
    private String email;

}
