package kr.ac.kopo.guestbook.service;

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
