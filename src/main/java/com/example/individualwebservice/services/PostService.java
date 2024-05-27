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

            User author = userService.findUserById(id);

            if (author != null) {

                Post post = new Post(postInformation.getTopic(), postInformation.getContent(), author);

                author.addPostToList(post);

                postRepository.save(post);

                userService.saveOrUpdateUser(author);

                return post;
            } else {

                return null;
            }


        } else {

            return null;
        }
    }

    public Post updatePost(long id, Post newPostInformation) {

        if (newPostInformation.getAuthor() == null) {

            Post existingPost = findPostById(id);

            if (existingPost != null) {

                if (!newPostInformation.getTopic().equals(existingPost.getTopic()) && newPostInformation.getTopic() != null) {

                    existingPost.setTopic(newPostInformation.getTopic());
                }
                if (!newPostInformation.getContent().equals(existingPost.getContent()) && newPostInformation.getContent() != null) {

                    existingPost.setContent(newPostInformation.getContent());
                }
            }

            postRepository.save(existingPost);

            return existingPost;

        } else {

            Post existingPost = findPostById(id);
            User existingAuthor = existingPost.getAuthor();
            User newAuthor = userService.findUserById(newPostInformation.getAuthor().getId());

            if (existingPost != null) {

                if (!newPostInformation.getTopic().contains(existingPost.getTopic()) && newPostInformation.getTopic() != null) {

                    existingPost.setTopic(newPostInformation.getTopic());
                }
                if (!newPostInformation.getContent().contains(existingPost.getContent()) && newPostInformation.getContent() != null) {

                    existingPost.setContent(newPostInformation.getContent());
                }

                existingPost.setAuthor(newAuthor);

                existingAuthor.removePostFromList(existingPost);
                newAuthor.addPostToList(existingPost);

                userService.saveOrUpdateUser(existingAuthor);
                userService.saveOrUpdateUser(newAuthor);

                postRepository.save(existingPost);
            }

            return existingPost;
        }
    }

    public String deletePost(long id) {
        Post postToBeDeleted = findPostById(id);

        if (postToBeDeleted != null) {

            User author = postToBeDeleted.getAuthor();

            if (author != null) {

                author.removePostFromList(postToBeDeleted);

                userService.saveOrUpdateUser(author);

                postRepository.delete(postToBeDeleted);

                return "Deleted post";
            } else {

                return "ERROR: Issue when retrieving Author/User";
            }

        } else {

            return "ERROR: Post ID provided does not exist";
        }
    }
}
