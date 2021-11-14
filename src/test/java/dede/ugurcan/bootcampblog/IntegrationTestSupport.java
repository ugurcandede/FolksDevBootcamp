package dede.ugurcan.bootcampblog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dede.ugurcan.bootcampblog.dto.converter.CommentDtoConverter;
import dede.ugurcan.bootcampblog.dto.converter.PostDtoConverter;
import dede.ugurcan.bootcampblog.dto.converter.UserDtoConverter;
import dede.ugurcan.bootcampblog.repository.CommentRepository;
import dede.ugurcan.bootcampblog.repository.PostRepository;
import dede.ugurcan.bootcampblog.repository.UserRepository;
import dede.ugurcan.bootcampblog.service.CommentService;
import dede.ugurcan.bootcampblog.service.PostService;
import dede.ugurcan.bootcampblog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
@DirtiesContext
@AutoConfigureMockMvc
public class IntegrationTestSupport extends TestSupport {

    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserDtoConverter userDtoConverter;

    @Autowired
    public PostService postService;
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public PostDtoConverter postDtoConverter;

    @Autowired
    public CommentService commentService;
    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public CommentDtoConverter commentDtoConverter;

    public final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }

}
