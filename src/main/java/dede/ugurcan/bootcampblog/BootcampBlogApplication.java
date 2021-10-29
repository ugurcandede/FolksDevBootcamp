package dede.ugurcan.bootcampblog;

import dede.ugurcan.bootcampblog.model.Comment;
import dede.ugurcan.bootcampblog.model.Post;
import dede.ugurcan.bootcampblog.model.StatusType;
import dede.ugurcan.bootcampblog.model.User;
import dede.ugurcan.bootcampblog.repository.CommentRepository;
import dede.ugurcan.bootcampblog.repository.PostRepository;
import dede.ugurcan.bootcampblog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
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

        User user1 = new User("ugurcandede", "ugur@dede.com", "Ugurcan Dede");

        Post post = new Post(
                "Hello",
                "Hello Folksie!~",
                user1);

        // Reverse Ownership
        user1.getPosts().add(post);
        postRepository.save(post);

        Post commentedPost = postRepository.findTopByOrderByCreateDate();
        System.out.println("\nCommentedPost: " + commentedPost);


        User user2 = new User("nemesisce", "cagridursun@folksdev", "Cagri Dursun");

        Post post2 = new Post(
                "Lorem Ipsum",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a diam consectetur.",
                LocalDateTime.now(),
                user2,
                List.of());

        Comment comment = new Comment(
                "Hi Kod Gemisi",
                LocalDateTime.of(2021, 10, 28, 0, 45),
                StatusType.PUBLISHED,
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
