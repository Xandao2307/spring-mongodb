package com.estudospring.spingmongo.resources;

import com.estudospring.spingmongo.domain.Post;
import com.estudospring.spingmongo.resources.utils.URL;
import com.estudospring.spingmongo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String title){
        var posts = postService.findByTitle(URL.decodeParam(title));
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text",defaultValue = "") String title, @RequestParam(value = "minDate",defaultValue = "") String minDate, @RequestParam(value = "maxDate",defaultValue = "") String maxDate){
        title = URL.decodeParam(title);
        var min = URL.convertDate(minDate, new Date(0L));
        var max = URL.convertDate(maxDate, new Date(0L));
        var posts = postService.fullSearch(title,min,max);

        return ResponseEntity.ok().body(posts);
    }
   
}
