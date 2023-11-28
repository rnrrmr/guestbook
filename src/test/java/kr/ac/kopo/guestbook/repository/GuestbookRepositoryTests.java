package kr.ac.kopo.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.ac.kopo.guestbook.entity.Guestbook;
import kr.ac.kopo.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i ->{
            Guestbook guestbook = Guestbook.builder()
                    .title("Title --- " + i)
                    .content("Content --- " + i)
                    .writer("Writer --- " + (i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
            //guestbook테이블에 300개의 더미데이터를 insert
        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if (result.isPresent()){
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title...");
            guestbook.changeContent("Changed Content...");

            guestbookRepository.save(guestbook);
            // 기존에 존재하는 entity인 경우 save()는 update수행
        }
    }

    @Test
    public void testQuery1(){   // 단일항목 검색 테스트
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2(){   // 다중항목 검색 테스트
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "2";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expressionTitle = qGuestbook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestbook.content.contains(keyword);
        BooleanExpression expressionAll = expressionTitle.or(expressionContent); // 1-----
        builder.and(expressionAll);   // 2 ------
        builder.and(qGuestbook.gno.gt(0L));  // 3-------

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery3(){   // 다중항목 검색 테스트
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "3";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expressionTitle = qGuestbook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestbook.content.contains(keyword);
        BooleanExpression expressionWriter = qGuestbook.writer.contains(keyword);
        BooleanExpression expressionAll1 = expressionTitle.or(expressionContent); // 1-----
        BooleanExpression expressionAll2 = expressionAll1.or(expressionWriter);
        builder.and(expressionAll2);   // 2 ------
        builder.and(qGuestbook.gno.gt(0L));  // 3-------

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

}
