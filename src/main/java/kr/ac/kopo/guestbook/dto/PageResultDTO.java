package kr.ac.kopo.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    // DTO 리스트
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;
    // 현재 페이지 번호
    private int page;
    // 목록 사이즈
    private int size;
    // 시작 페이지 번호, 끝 페이지 번호
    private int start, end;
    // 이전 다음
    private boolean prev, next;
    // 페이지 번호 목록
    private List<Integer> pageList;

    // fn - Entity를 DTO로 변환
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        page = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();
        // 마지막이 아닌 화면의 마지막 번호
        int tempEnd = (int)Math.ceil(page/10.0) * 10;

        start = tempEnd - 9;
        // 첫화면의 start는 1이기 때문에 prev는 false
        prev = start > 1;
        // 마지막 화면의 마지막 번호
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
