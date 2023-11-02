package com.class302.omzteam.board.controller;

import com.class302.omzteam.board.model.Post;
import com.class302.omzteam.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

//    private final PostService postService;
//
//    @Autowired
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

    @GetMapping("/list")
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "board/list"; // 타임리프 템플릿 이름
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable("id") int id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post/view"; // 타임리프 템플릿 이름
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create"; // 타임리프 템플릿 이름
    }

    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/posts"; // 포스트 생성 후 리스트 페이지로 리다이렉트
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post/edit"; // 타임리프 템플릿 이름
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable("id") int id, @ModelAttribute Post post) {
        postService.updatePost(post);
        return "redirect:/posts";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") int id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

}
