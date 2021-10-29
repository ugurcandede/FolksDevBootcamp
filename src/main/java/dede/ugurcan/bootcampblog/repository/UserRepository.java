package dede.ugurcan.bootcampblog.repository;

import dede.ugurcan.bootcampblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
