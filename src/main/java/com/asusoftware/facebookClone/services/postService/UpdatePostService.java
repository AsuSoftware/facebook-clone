package com.asusoftware.facebookClone.services.postService;

import com.asusoftware.facebookClone.dto.PostDTO;

public interface UpdatePostService {
    void updatePost(PostDTO postDto, Long userId, Long postId);
}
