package kr.ac.kopo.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guestbook_seq_generator")
    @SequenceGenerator(name="guestbook_seq_generator", sequenceName = "guestbook_seq", allocationSize = 1)
    private Long gno;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 1500, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;

    //제목변경 내용수정
    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
