package com.asusoftware.facebookClone.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Getter // cosi genera Spring automaticamente i metodi get()
@Setter
@ToString
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "image_product", length = 9999999)
    @NotNull
    private String image;


    @ManyToOne // molti annunci su uno user
    @JoinColumn(name = "post_id") // fa il join con l'altra colonna di un'altra Entità
    private Post post;

}