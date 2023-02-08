package com.loeaf.siginin.repository;

import com.loeaf.siginin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByNickName(String nickName);
}
