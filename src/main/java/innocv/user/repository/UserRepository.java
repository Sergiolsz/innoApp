package innocv.user.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import innocv.user.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Serializable> {

}
