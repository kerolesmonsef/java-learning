package com.foo.learning.controller;

import com.foo.learning.entity.Blog;
import com.foo.learning.entity.Owner;
import com.foo.learning.enums.Status;
import com.foo.learning.repository.BlogRepository;
import com.foo.learning.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/owner")
public class BlogController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/saveOwner")
    public Owner saveOwner(@RequestBody Owner owner) {
        System.out.println("Owner save called..."); // adjfff

        Owner ownerOut = ownerRepository.save(owner);
        System.out.println("Saved!!!");

        return ownerOut;
    }

    @GetMapping("/getOwner/{id}")
    public Object getOwner(
            @PathVariable(name = "id") Integer id,
            @RequestParam(name = "name", defaultValue = "") String name
    ) {
        System.out.println("Owner get called...");
        Owner ownerOut = ownerRepository.findById(id)//sdlkfj
                .orElse(null);
        System.out.println("\nOwner details with Blogs :: \n" + ownerOut);
        System.out.println("\nList of Blogs :: \n" + ownerOut.getBlogs());
        System.out.println("\nDone!!!");
        System.out.println("Name :: " + name);

        return ownerOut.getBlogs();
    }

    @PostMapping("/addBlog/{ownerId}")
    public Owner addPost(@RequestBody Blog blog, @PathVariable Integer ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElse(new Owner());

        owner.getBlogs()
                .add(blog);
        return owner;
    }

    @GetMapping("/getBlog/{id}")
    public Object getBlogInfo(@PathVariable(name = "id") Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElse(null);

        System.out.println(Status.valueOf("ACTIVE"));
        return blog.getOwner();
    }
}
