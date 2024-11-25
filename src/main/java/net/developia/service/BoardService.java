package net.developia.service;

import java.util.List;

import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;

public interface BoardService {
	public void register(BoardVO board) throws Exception; // 게시물 등록
	public BoardVO get(Long bno) throws Exception; // 글번호 들어오면 리턴
	public boolean modify(BoardVO board) throws Exception;
	public boolean remove(Long bno) throws Exception;
	//public List<BoardVO> getList() throws Exception;
	public List<BoardVO> getList(Criteria cri) throws Exception;
	public int getTotal(Criteria cri);
}
