package dede.ugurcan.bootcampblog.service;

import dede.ugurcan.bootcampblog.TestSupport;
import dede.ugurcan.bootcampblog.dto.CommentDto;
import dede.ugurcan.bootcampblog.dto.converter.CommentDtoConverter;
import dede.ugurcan.bootcampblog.dto.request.CreateCommentRequest;
import dede.ugurcan.bootcampblog.dto.request.UpdateCommentRequest;
import dede.ugurcan.bootcampblog.exception.NotFoundException;
import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentServiceTest extends TestSupport {

    private CommentService commentService;
    private CommentRepository commentRepository;
    private CommentDtoConverter commentDtoConverter;
    private UserService userService;
    private PostService postService;

    @BeforeEach
    void setUp() {
        commentRepository = Mockito.mock(CommentRepository.class);
        commentDtoConverter = Mockito.mock(CommentDtoConverter.class);
        userService = Mockito.mock(UserService.class);
        postService = Mockito.mock(PostService.class);

        commentService = new CommentService(commentRepository, commentDtoConverter, userService, postService);
    }

    @Test
    void testGetCommentById_whenCalledWithId_itShouldReturnCommentDto() {
        Comment comment = generateComment();
        CommentDto commentDto = generateCommentDto();

        Mockito.when(commentRepository.findById("Id")).thenReturn(Optional.of(comment));
        Mockito.when(commentDtoConverter.convertToCommentDto(comment)).thenReturn(commentDto);

        CommentDto result = commentService.getCommentById("Id");

        assertEquals(commentDto, result);

        Mockito.verify(commentRepository).findById("Id");
        Mockito.verify(commentDtoConverter).convertToCommentDto(comment);
    }

    @Test
    void testGetCommentById_whenIdNotExists_itShouldThrowNotFoundException() {

        Mockito.when(commentRepository.findById("Id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> commentService.getCommentById("Id"));

        Mockito.verify(commentRepository).findById("Id");
        Mockito.verifyNoInteractions(commentDtoConverter);
    }

    @Test
    void testGetCommentList_tShouldReturnListOfCommentDto() {

        List<Comment> commentList = generateListOfComment();
        List<CommentDto> commentDtoList = generateListOfCommentDto();

        Mockito.when(commentRepository.findAll()).thenReturn(commentList);
        Mockito.when(commentDtoConverter.convertToCommentDtoList(commentList)).thenReturn(commentDtoList);

        List<CommentDto> result = commentService.getAllCommentDtoList();

        assertEquals(commentDtoList, result);

        Mockito.verify(commentRepository).findAll();
        Mockito.verify(commentDtoConverter).convertToCommentDtoList(commentList);
    }

    @Test
    void testCreateComment_whenCalledValidRequest_itShouldReturnCommentDto() {

        CreateCommentRequest request = generateCreateCommentRequest();
        Comment comment = generateComment();
        CommentDto commentDto = generateCommentDto();

        Mockito.when(userService.findByUserId(request.getUserId())).thenReturn(generateUser());
        Mockito.when(postService.findByPostId(request.getPostId())).thenReturn(generatePost());
        Mockito.when(commentDtoConverter.convertToCommentDto(comment)).thenReturn(commentDto);
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);

        CommentDto result = commentService.createComment(request);

        assertEquals(commentDto, result);

        Mockito.verify(userService).findByUserId("UserId");
        Mockito.verify(postService).findByPostId("PostId");
        Mockito.verify(commentDtoConverter).convertToCommentDto(comment);
        Mockito.verify(commentRepository).save(comment);
    }

    @Test
    void testDeleteComment_whenCalledValidId_itShouldReturnString() {

        Comment comment = generateComment();
        CommentDto commentDto = generateCommentDto();

        Mockito.when(commentRepository.findById("id")).thenReturn(Optional.of(comment));
        Mockito.when(commentDtoConverter.convertToCommentDto(comment)).thenReturn(commentDto);

        String result = commentService.deleteComment("id");

        assertEquals("id deleted", result);

        Mockito.verify(commentRepository).findById("id");
        Mockito.verify(commentDtoConverter).convertToCommentDto(comment);
    }

    @Test
    void testDeleteComment_whenCalledInValidId_itShouldThrowNotFoundException() {

        Mockito.when(commentRepository.findById("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> commentService.getCommentById("id"));

        Mockito.verify(commentRepository).findById("id");
        Mockito.verifyNoInteractions(commentDtoConverter);
    }

    @Test
    void testUpdateComment_whenCalledValidRequest_itShouldReturnCommentDto() {

        UpdateCommentRequest request = generateUpdateCommentRequest();
        Comment updatedComment = generateUpdatedComment(generateComment(), request);
        CommentDto commentDto = generateCommentDto();

        Mockito.when(commentRepository.findById("id")).thenReturn(Optional.of(generateComment()));
        Mockito.when(commentRepository.save(updatedComment)).thenReturn(updatedComment);
        Mockito.when(commentDtoConverter.convertToCommentDto(updatedComment)).thenReturn(commentDto);

        CommentDto result = commentService.updateComment("id", request);

        assertEquals(commentDto, result);

        Mockito.verify(commentRepository).findById("id");
        Mockito.verify(commentRepository).save(updatedComment);
        Mockito.verify(commentDtoConverter).convertToCommentDto(updatedComment);
    }

    @Test
    void testUpdateComment_whenCalledIdInValid_itShouldThrowNotFoundException() {

        UpdateCommentRequest request = generateUpdateCommentRequest();

        Mockito.when(commentRepository.findById("id")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> commentService.updateComment("id", request));

        Mockito.verify(commentRepository).findById("id");
        Mockito.verifyNoInteractions(commentDtoConverter);
    }

}
