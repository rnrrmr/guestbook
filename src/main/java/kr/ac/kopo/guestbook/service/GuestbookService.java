package kr.ac.kopo.guestbook.service;

import com.querydsl.core.BooleanBuilder;
import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import kr.ac.kopo.guestbook.dto.PageRequestDTO;
import kr.ac.kopo.guestbook.dto.PageResultDTO;
import kr.ac.kopo.guestbook.entity.Guestbook;

public interface GuestbookService {
    // 글 등록
    Long register(GuestbookDTO dto);

    // 글 목록
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    // 글 내용 조회
    GuestbookDTO read(Long gno);

    // 글 수정
    void modify(GuestbookDTO dto);  // gno, title, content를 사용해서 수정

    // 글 삭제
    void remove(Long gno);  // 추상 메소드 선언, gno만 있어도 삭제가능

    // 검색
    BooleanBuilder getSearch(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook guestbookEntity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return guestbookEntity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
