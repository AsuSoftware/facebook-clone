package com.asusoftware.facebookClone.services;

import com.asusoftware.facebookClone.dto.ImageDTO;
import com.asusoftware.facebookClone.dto.PostDTO;
import com.asusoftware.facebookClone.dto.UserDTO;
import com.asusoftware.facebookClone.exceptions.NotFoundPostException;
import com.asusoftware.facebookClone.exceptions.NotFoundUserException;
import com.asusoftware.facebookClone.models.Image;
import com.asusoftware.facebookClone.models.Post;
import com.asusoftware.facebookClone.models.User;
import com.asusoftware.facebookClone.repositories.ImageRepository;
import com.asusoftware.facebookClone.repositories.PostRepository;
import com.asusoftware.facebookClone.repositories.UserRepository;
import com.asusoftware.facebookClone.services.postService.CreatePostService;
import com.asusoftware.facebookClone.services.postService.UpdatePostService;
import com.asusoftware.facebookClone.services.postService.DeletePostService;
import com.asusoftware.facebookClone.services.postService.FindPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements CreatePostService, UpdatePostService, FindPostService, DeletePostService {

    private final UserRepository repoUser;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Override
    public void insertPost(PostDTO postDto, Long id) {
        User user = repoUser.findById(id).orElseThrow(NotFoundUserException::new); // find the current user
        Post post = convertPostDtoInEntity(postDto);
        post.setUser(user);
        postRepository.save(post);
        List<Image> images = postDto.getImages().stream().map(imageDto -> {
            Image img = new Image();
            img.setImage(imageDto.getImage());
            img.setPost(post); // pe Entitatea imagine setez post-ul
            return img;
        }).collect(Collectors.toList());
        imageRepository.saveAll(images);
    }

    @Override
    public void deletePost(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundPostException();
        }

    }

    @Override
    public List<PostDTO> findAllPosts() {
        return postRepository.findAll().stream().map(this::convertPostInDto).collect(Collectors.toList());
    }

    @Override
    public PostDTO findPostById(Long id) {
        Post post = repoUser.findPostById(id).orElseThrow(NotFoundPostException::new);
        return convertPostInDto(post);
    }

    public void updatePost(PostDTO postDto, Long userId, Long postId) {
        repoUser.findById(userId).orElseThrow(NotFoundUserException::new);
        Post post = repoUser.findPostById(postId).orElseThrow(NotFoundPostException::new);
        List<Image> images = postDto.getImages().stream().map(this::convertImageDtoInImage).collect(Collectors.toList());
        post.setDescription(postDto.getDescription());
        post.setLikes(postDto.getLikes());
        post.setPostDate(postDto.getPostDate());
        post.setPrivacy(postDto.getPrivacy());
        post.setImages(images);
        postRepository.save(post);
    }


    private PostDTO convertPostInDto(Post post) {
        PostDTO postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setImages(post.getImages().stream().map(this::convertImageInDto).collect(Collectors.toList()));
        postDto.setLikes(post.getLikes());
        postDto.setPostDate(post.getPostDate());
        postDto.setPrivacy(post.getPrivacy());
        postDto.setUser(convertUserInDto(post.getUser()));
        return postDto;
    }

    private UserDTO convertUserInDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    private Post convertPostDtoInEntity(PostDTO postDto) {
        Post post = new Post();
        post.setDescription(postDto.getDescription());
        post.setLikes(postDto.getLikes());
        post.setPostDate(postDto.getPostDate());
        post.setPrivacy(postDto.getPrivacy());
        return post;
    }

    private ImageDTO convertImageInDto(Image image) {
        ImageDTO imageDto = new ImageDTO();
        imageDto.setId(image.getId());
        imageDto.setImage(image.getImage());
        return imageDto;
    }

    private Image convertImageDtoInImage(ImageDTO imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setImage(imageDto.getImage());
        return image;
    }

}
