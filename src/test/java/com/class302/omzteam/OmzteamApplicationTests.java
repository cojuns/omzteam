package com.class302.omzteam;


import com.class302.omzteam.board.model.Post;
import com.class302.omzteam.mybatis.PostMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OmzteamApplicationTests {

    @Autowired
    PostMapper postMapper;


    @Test
    void contextLoads() {

    List<Post> posts = postMapper.selectAllPosts();

        for(Post post : posts){

            System.out.println(post);

        }

    }









}
