package com.asusoftware.facebookClone.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PostDTO {

    private Long id;
    private List<ImageDTO> images;
    private String description;
    private Long likes;
    private Date postDate;
    private String privacy;


}
