package innocv.user.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import innocv.user.entity.User;

public interface UserRepository extends JpaRepository<User, Serializable> {

}
