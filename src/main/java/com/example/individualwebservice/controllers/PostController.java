package com.example.individualwebservice.controllers;

import com.example.individualwebservice.entities.Post;
import com.example.individualwebservice.entities.User;
import com.example.individualwebservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> createNewPost(@RequestBody Post postInformation, @RequestParam("userId") long id) {
        return ResponseEntity.ok(postService.createNewPost(postInformation, id));
    }

    @PutMapping("/updatepost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post newPostInformation) {
        return ResponseEntity.ok(postService.updatePost(id, newPostInformation));
    }

    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
