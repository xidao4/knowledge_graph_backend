package com.sec.supernatural.backend_coin.controller;

import com.sec.supernatural.backend_coin.bl.PersonBl;
import com.sec.supernatural.backend_coin.po.Movie;
import com.sec.supernatural.backend_coin.vo.Link;
import com.sec.supernatural.backend_coin.vo.Node;
import com.sec.supernatural.backend_coin.po.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    PersonBl personBl;
    @GetMapping("/{name}")
    public Node findByName(@PathVariable String name){
        Person p= personBl.findByName(name);
        return new Node(p.getName(),1);
    }
    @GetMapping("/actedBy/{name}")
    public List<Link> getActedInMovie(@PathVariable String name){
        Person p= personBl.findByName(name);
        List<Link>  links= new ArrayList<>();
        Set<Movie> movieSet=p.getActedInMovie();
        for(Movie movie:movieSet) {
            links.add(new Link(p.getName(), movie.getTitle(), 5));//暂时都写成5
        }
        return links;
    }

    @GetMapping("/all")
    public List<Node> findAll(){
        return personBl.findAll();
    }
}
