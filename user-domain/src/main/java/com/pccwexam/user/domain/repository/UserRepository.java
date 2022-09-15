package com.pccwexam.user.domain.repository;

import com.pccwexam.user.domain.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    Optional<UserDO> findOneByEmail(String email);

}
