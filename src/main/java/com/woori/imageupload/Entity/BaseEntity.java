package com.woori.imageupload.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass//독립적으로 사용불가능
@EntityListeners(AuditingEntityListener.class)//감사기능을 이용해서 자동 등록
public class BaseEntity {
    @Column(name = "regDate", nullable = false, updatable = false)//생략 불가능, 수정불가능
    @CreatedDate//첫 생성날짜로 등록
    private LocalDateTime regDate;//생성날짜(비공개)
    @Column(name = "modDate")
    @LastModifiedDate//마지막 수정날짜로 등록
    private LocalDateTime modDate;//수정날짜(등록날짜 공개)
}
