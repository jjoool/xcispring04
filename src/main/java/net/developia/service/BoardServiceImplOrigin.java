package net.developia.service;

import java.util.List;

//import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.mapper.BoardMapper;

@Log4j
// @Service // business layer가 됨.
//@AllArgsConstructor 
public class BoardServiceImplOrigin{ //implements BoardService {
	private BoardMapper mapper;

	//@Override
	public void register(BoardVO board) throws Exception {
		try {
			log.info("register..." + board);
			mapper.insertSelectKey(board);
		} catch (Exception e) {
			log.error(e.getMessage()); // 관리자 처리 위한 error message
			// user에게 출력하는 메시지 : controller에서(presentation layer에서)
			throw e;
		}
		
	}

	// 조회 작업
	//@Override
	public BoardVO get(Long bno) throws Exception {
		log.info("get...." + bno);
		try {
			BoardVO board = mapper.read(bno);
			if (board == null) throw new RuntimeException(bno + "게시물이 없음");
			return board;
		} catch (Exception e) {
			log.error(e.getMessage()); 
			throw e;
		}
		// 글을 읽지 못하는 경우가 있음.(글이 갑자기 없어져서) -> 예외 처리 
		// return mapper.read(bno); 
	}
	
	// 수정
	//@Override
	public boolean modify(BoardVO board) throws Exception {
		boolean result = false;
		try {
			log.info("modify....."+board);
			// update 잘 되었을 때 1 return, 아닐 시 0 return => 반영된 레코드의 개수를 return 하기 때문
			if(mapper.update(board) == 0) 
				throw new RuntimeException(board.getBno() + "번 게시물이 수정되지 않음");
				return true;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		} 
		
	}

	// 삭제
	//@Override
	public boolean remove(Long bno) throws Exception {
		boolean result = false;
		try {
			log.info("remove....."+bno);
			if(mapper.delete(bno) == 0) 
				throw new RuntimeException(bno + "번 게시물이 삭제되지 않음");
				return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		} 
	}

	// 목록(리스트) 작업
	//@Override
	public List<BoardVO> getList() throws Exception {
		try {
			return mapper.getList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	
}
