package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.dto.converter.CommentDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreateCommentRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;

    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository,
                          CommentDtoConverter commentDtoConverter,
                          UserService userService,
                          PostService postService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.userService = userService;
        this.postService = postService;
    }

    protected Comment findByCommentId(String id) {
        return commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
    }

    protected List<Comment> getAllCommentList() {
        return commentRepository.findAll();
    }

    public CommentDto getCommentById(String id) {
        return commentDtoConverter.convertToCommentDto(findByCommentId(id));
    }

    public List<CommentDto> getAllCommentDtoList() {
        return commentDtoConverter.convertToCommentDtoList(getAllCommentList());
    }

    public CommentDto createComment(CreateCommentRequest request) {
        User user = userService.findByUserId(request.getUserId());
        Post post = postService.findByPostId(request.getPostId());

        Comment comment = new Comment(request.getBody(), user, post);

        return commentDtoConverter.convertToCommentDto(commentRepository.save(comment));
    }


}
