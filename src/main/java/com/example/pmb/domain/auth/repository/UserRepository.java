package com.example.pmb.domain.auth.repository;

import com.example.pmb.domain.auth.entity.User;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    List<User> findAll();
    User findUserByUsername(String username);
}
