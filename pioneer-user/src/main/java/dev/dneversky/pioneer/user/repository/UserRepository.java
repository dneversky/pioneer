package dev.dneversky.pioneer.user.repository;

import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByUserDetails(UserDetails userDetails);
}
