package com.class302.omzteam.mybatis;

import com.class302.omzteam.board.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> selectAllPosts();
    Post selectPost(@Param("post_no") int post_no);
    int insertPost(Post post);
    int updatePost(Post post);
    int deletePost(@Param("post_no") int post_no);

}
