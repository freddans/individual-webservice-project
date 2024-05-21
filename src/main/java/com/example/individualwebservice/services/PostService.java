package com.example.individualwebservice.services;

import com.example.individualwebservice.Repositories.PostRepository;
import com.example.individualwebservice.entities.Post;
import com.example.individualwebservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            return post;
        } else {

            return null;
        }
    }

    public Post createNewPost(Post postInformation, long id) {

        if (postInformation.getTopic() != null && postInformation.getContent() != null && id != 0) {

            User existingUser = userService.findUserById(id);

            if (existingUser != null) {

                Post post = new Post(postInformation.getTopic(), postInformation.getContent(), existingUser);

                postRepository.save(post);

                return post;
            } else {

                return null;
            }


        } else {

            return null;
        }
    }

    public Post updatePost(long id, Post newPostInformation) {
        Post existingPost = findPostById(id);

        if (existingPost != null) {

            if (!newPostInformation.getTopic().contains(existingPost.getTopic()) && !newPostInformation.getTopic().isEmpty()) {

                existingPost.setTopic(newPostInformation.getTopic());
            }
            if (!newPostInformation.getContent().contains(existingPost.getContent()) && !newPostInformation.getContent().isEmpty()) {

                existingPost.setContent(newPostInformation.getContent());
            }

            postRepository.save(existingPost);

            return existingPost;
        } else {

            return null;
        }
    }

    public String deletePost(long id) {
        Post postToBeDeleted = findPostById(id);

        if (postToBeDeleted != null) {

            postRepository.delete(postToBeDeleted);

            return "Deleted post";
        } else {

            return "ERROR: Post ID provided does not exist";
        }
    }
}
