package com.estudospring.spingmongo.service;

import com.estudospring.spingmongo.domain.Post;
import com.estudospring.spingmongo.repository.PostRepository;
import com.estudospring.spingmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id){
         Optional<Post> posts = postRepository.findById(id);
         return posts.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

}
