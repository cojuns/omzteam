package com.class302.omzteam.board.service;

import com.class302.omzteam.board.model.Post;
import com.class302.omzteam.mybatis.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<Post> getAllPosts() {
        return postMapper.selectAllPosts();
    }

    public Post getPostById(int id) {
        return postMapper.selectPost(id);
    }

    public Post createPost(Post post) {
        postMapper.insertPost(post);
        return post; // insert 후에 post 객체에는 post_no가 설정되어 있어야 합니다.
    }

    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    public void deletePost(int id) {
        postMapper.deletePost(id);
    }


}
