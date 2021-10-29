package dede.ugurcan.bootcampblog.repository;

import dede.ugurcan.bootcampblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
