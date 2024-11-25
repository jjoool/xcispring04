package net.developia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;
import net.developia.domain.PageDTO;
import net.developia.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	
//	@GetMapping("/list") // list.jsp 를 찾기 위함. 
//	public String list(Model model) {
//		try {
//			log.info("list.....");
//			model.addAttribute("list", service.getList());
//			return "board/list";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		try {
			model.addAttribute("list", service.getList(cri));
			// model.addAttribute("pageMaker", new PageDTO(cri, 123)); // 임시 값을 넣어줌.
			int total = service.getTotal(cri);
			log.info("total: " + total);
			model.addAttribute("pageMaker", new PageDTO(cri, total));
			return "board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		try {
			log.info("register: " + board);
			service.register(board);
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	@GetMapping("/get")
//	public void get(@RequestParam("bno") Long bno, Model model) {
//		try {
//			log.info("/get");
//			model.addAttribute("board", service.get(bno));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		try {
			log.info("/get or modify");
			model.addAttribute("board", service.get(bno));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 10.2.4 수정 처리와 테스트
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		try {
			log.info("modify:" + board);
			
			if(service.modify(board)) {
				rttr.addFlashAttribute("result", "success"); 
				} 
			
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			return "redirect:/board/list";
				
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	// 10.2.5 삭제 처리와 테스트
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		try {
			log.info("remove:" + bno);
			
			if(service.remove(bno)) {
				rttr.addFlashAttribute("result", "success"); 
				} 
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
}
