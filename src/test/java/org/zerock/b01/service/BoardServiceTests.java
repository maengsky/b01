package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testModify() {
        // 변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated....101")
                .content("updated content 101...")
                .build();

        boardService.modify(boardDTO);
    }
    // 게시물 PageList 테스트
    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }

    // 게시물 및 첨부 파일까지 등록 테스트
    @Test
    public void testRegisterWithImages() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("File...Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_bbb.jpg"
                ));
        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    // 게시물 및 첨부 파일까지 조회 테스트
    @Test
    public void testReadAll() {
        Long bno = 102L;

        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);

        for(String fileName : boardDTO.getFileNames()) {
            log.info(fileName);
        }
    }

    // 게시물 및 첨부 파일 수정 테스트
    @Test
    public void testModifyAll() {
        // 변경에 필요한 데이터
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated...101")
                .content("Updated content 101...")
                .build();

        // 첨부 파일 하나 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID()+ "_ttt.jpg"));

        boardService.modify(boardDTO);
    }

    // 게시물 삭제 처리 테스트
    @Test
    public void testRemoveAll() {
        Long bno = 3L;

        boardService.remove(bno);
    }

    @Test
    public void testListWithAll() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardListAllDTO> responseDTO =
                boardService.listWithAll(pageRequestDTO);

        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(boardListAllDTO -> {
            log.info(boardListAllDTO.getBno() + ":" + boardListAllDTO.getTitle());

            if(boardListAllDTO.getBoardImages() != null) {
                for(BoardImageDTO boardImage : boardListAllDTO.getBoardImages()) {
                    log.info(boardImage);
                }
            }

            log.info("------------------------------------");
        });
    }
}
