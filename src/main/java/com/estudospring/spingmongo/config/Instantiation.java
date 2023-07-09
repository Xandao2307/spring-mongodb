package com.estudospring.spingmongo.config;

import com.estudospring.spingmongo.domain.Post;
import com.estudospring.spingmongo.domain.User;
import com.estudospring.spingmongo.dto.AuthorDTO;
import com.estudospring.spingmongo.dto.CommentDTO;
import com.estudospring.spingmongo.repository.PostRepository;
import com.estudospring.spingmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository  .deleteAll();
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.now(), "partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.now(), "Bom dia", "Acordei feliz hoje",new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("boa viagem", Instant.now(), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", Instant.now(), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("tenha um otimo dia", Instant.now(), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
