package com.crm.controller;

import com.crm.entity.Comment;
import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    // Constructor injection
    public CommentController(PostRepository postRepository, CommentRepository commentRepository) {

        System.out.println(1000);
        System.out.println(1000);
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping
    public String createCommment(
            @RequestBody Comment comment,
            @RequestParam long postId
    ){
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
        return "crated successfully";
    }

}
