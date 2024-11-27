package com.crm.controller;

import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    // Constructor injection
    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public String createPost(
            @RequestBody Post post
    ){
        postRepository.save(post);
        return "Post created successfully!";
    }
    @DeleteMapping
    public void delete(){
        postRepository.deleteById(1L);
    }

}
