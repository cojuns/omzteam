package com.class302.omzteam.board.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private int post_no;
    private Integer top_post_no;
    private int board_no;
    private int num;
    private String content;
    private LocalDateTime date_created;
    private String title;
    private Integer hits; // Nullable field
}
