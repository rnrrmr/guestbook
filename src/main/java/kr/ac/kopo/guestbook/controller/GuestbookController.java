package kr.ac.kopo.guestbook.controller;

import kr.ac.kopo.guestbook.dto.GuestbookDTO;
import kr.ac.kopo.guestbook.dto.PageRequestDTO;
import kr.ac.kopo.guestbook.dto.PageResultDTO;
import kr.ac.kopo.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2  // 로그 출력
@RequiredArgsConstructor // 생성된 객체의 참조값을 주입받을 수 있음
public class GuestbookController {
    private final GuestbookService service;

    @GetMapping("/")
    // 리스트가 자동을 실행되게 해줄거
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list() 실행됨");
        log.info("list......" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("get방식으로 매핑됨");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto...." + dto);

        Long gno = service.register(dto); // 실제 서비스는 serviceImpl에
        // dto => entity로 변환해서 db guestbook 테이블에 insert
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping("/read")
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
    }
}
