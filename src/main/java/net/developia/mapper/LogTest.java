package net.developia.mapper;

import lombok.extern.java.Log;
// debug - warn - info - error - fetal
// log4j 사용시: 필요한 단계까지 출력, 문자열 에러 많이 남. 파일 출력 쉽게 바꿀 수 있음.(옵션 줄 수 있음) 

@Log
public class LogTest {
	public static void main(String[] args) {
		System.out.println("Hello~");
		log.warning("warning...");
		log.info("info...");
	}
}
