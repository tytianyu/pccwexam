package com.pccwexam.user.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@EntityListeners({AuditingEntityListener.class})
public class UserDO {

    private static final int ACTIVATED = 1;
    private static final int DELETED = 0;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 100)
    private String nickname;
    @Column(nullable = false)
    private Integer status;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;

    public void activeUser() {
        this.status = ACTIVATED;
    }

    public void deleteUser() {
        this.status = DELETED;
    }

}
