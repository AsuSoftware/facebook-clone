package com.asusoftware.facebookClone.services.postService;

import com.asusoftware.facebookClone.dto.PostDTO;

import java.util.List;

public interface FindPostService {
        List<PostDTO> findAllPosts();
        PostDTO findPostById(Long id);
}
