package dede.ugurcan.bootcampblog;

import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.PostStatus;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.CommentRepository;
import dede.ugurcan.bootcampblog.repository.PostRepository;
import dede.ugurcan.bootcampblog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class BootcampBlogApplication implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public BootcampBlogApplication(UserRepository userRepository,
                                   PostRepository postRepository,
                                   CommentRepository commentRepository
    ) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootcampBlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(
                "ugurcandede",
                "ugur@dede.com",
                "Ugurcan Dede",
                Collections.emptyList(),
                Collections.emptyList());

        Post post = new Post(
                "Hello",
                "Hello Folksie!~",
                PostStatus.PUBLISHED,
                user1);

        // Reverse Ownership
//        user1.getPosts().add(post);
        postRepository.save(post);

        Post commentedPost = postRepository.findTopByOrderByCreationDate();
        System.out.println("\nCommentedPost: " + commentedPost);


        User user2 = new User(
                "nemesisce",
                "cagridursun@folksdev",
                "Cagri Dursun",
                Collections.emptyList(),
                Collections.emptyList());

        Post post2 = new Post(
                "Lorem Ipsum",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a diam consectetur.",
                PostStatus.PUBLISHED,
                user2,
                List.of());

        Comment comment = new Comment(
                "Hi Kod Gemisi",
                user2,
                post2);

        commentRepository.save(comment);

        userRepository
                .findAll()
                .forEach(u ->
                        System.out.printf("\n ID -> %s \n\t Data -> %s", u.getId(), u)
                );

    }

}
