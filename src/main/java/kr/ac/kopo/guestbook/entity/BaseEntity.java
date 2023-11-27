package kr.ac.kopo.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass  // 상속받기 위해
@EntityListeners(value = {AuditingEntityListener.class})
// 자동으로 날짜와 시간을 받아올 수 있음
@Getter
abstract public class BaseEntity {
    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")  // updatable은 생략하면 true
    private LocalDateTime modDate;
}
