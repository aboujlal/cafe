package cafe.dao;

import cafe.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByEmail(@Param("email") String eamil);
}
