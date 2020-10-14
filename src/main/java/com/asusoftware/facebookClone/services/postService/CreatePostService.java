package com.asusoftware.facebookClone.services.postService;

import com.asusoftware.facebookClone.dto.PostDTO;

public interface CreatePostService {
    void insertPost(PostDTO postDto, Long id);
}
