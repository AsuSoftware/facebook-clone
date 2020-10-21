package com.asusoftware.facebookClone.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "post")
@Getter // cosi genera Spring automaticamente i metodi get()
@Setter
@ToString // Genera automaticamente il metodo ToString()
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "datePost")
    private Date postDate;

    @Column(name = "privacy")
    private String privacy; // if is a electronics, or a book

    // responsabile per il collegamento alla tabella User con Ad tramite id
    @ManyToOne // molti annunci su uno user (manyToOne e responsabile pr la creazione di una colonna from user table)
    @JoinColumn(name = "users_id") // specifica la colonna che si va ad inserire nel Table post
    private User user;

    // mappedBy indica che questa Entità non e responsabile per la relazione, bensi lo e l'altra entità
    // mappedBy si riferisce al nome della proprietà dell'associazione sul lato proprietario.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) // un user a molti annunci, mappedBy si riferisce al field dove e stata fatta la foreinKey
    private List<Image> images;


}