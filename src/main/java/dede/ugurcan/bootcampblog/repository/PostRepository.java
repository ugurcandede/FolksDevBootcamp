package dede.ugurcan.bootcampblog.repository;

import dede.ugurcan.bootcampblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {

    Post findTopByOrderByCreatedAt();
}
