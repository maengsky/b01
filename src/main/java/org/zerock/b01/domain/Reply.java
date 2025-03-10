package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "board")
@Table(indexes = {@Index(name = "idx_reply_board_bno", columnList = "board_bno")})  // board_bno 컬럼에 인덱싱 처리
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

    // Board 내용까지 보기위해 추가(책에 없음)
    @Override
    public String toString() {
        return "replyText" + replyText + ", replyer: " + replyer + ", " +
                (board != null ? board.getContent() + "-" + board.getTitle() : "")
                ;
    }

    public void changeText(String text) {
        this.replyText = text;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
}
