package com.sec.supernatural.backend_coin.po;


import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;
    @Property
    private Long born;

    @Relationship(type="ACTED_IN",direction = Relationship.Direction.OUTGOING)
    private Set<Movie> actedInMovie=new HashSet<>();

    public Person() {
    }

    public Person(String name, Long born) {
        this.name = name;
        this.born = born;
    }

    public Set<Movie> getActedInMovie() {
        return actedInMovie;
    }

    public void setActedInMovie(Set<Movie> actedInMovie) {
        this.actedInMovie = actedInMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBorn() {
        return born;
    }

    public void setBorn(Long born) {
        this.born = born;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
