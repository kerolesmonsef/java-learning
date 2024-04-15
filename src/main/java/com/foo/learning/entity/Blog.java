package com.foo.learning.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blogs")
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "content")
    private String content;

    public Blog(String title, String category, String content) {
        this.title = title;
        this.category = category;
        this.content = content;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    protected Owner owner;
}