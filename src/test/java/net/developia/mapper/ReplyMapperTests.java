package net.developia.mapper;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import net.developia.domain.Criteria;
import net.developia.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="file:**/*-context.xml")
@Log4j
public class ReplyMapperTests {
	private Long[] bnoArr = {
			1245283L,
			1245282L,
			1245281L,
			1245280L,
			1245279L};
	
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testCreate(){
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트 " +i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 5L;
		ReplyVO vo = mapper.read(targetRno);
		log.info(vo);
	}
	
	@Transactional
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		assertEquals(1,mapper.delete(targetRno));
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("uUpdate Reply ");
		int count = mapper.update(vo);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[2]);
		
		replies.forEach(reply -> log.info(reply));
	}
	

}
