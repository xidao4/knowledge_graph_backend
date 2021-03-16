package com.sec.supernatural.backend_coin.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String title;
    @Property
    private String tagline;
    @Property
    private Long released;

    public Movie() {
    }

    public Movie(String title, String tagline, Long released) {
        this.title = title;
        this.tagline = tagline;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Long getReleased() {
        return released;
    }

    public void setReleased(Long released) {
        this.released = released;
    }
}
