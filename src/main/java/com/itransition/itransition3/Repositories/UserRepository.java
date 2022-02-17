package com.itransition.itransition3.Repositories;

import com.itransition.itransition3.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User u set u.lastOnline = :time where u.id = :id")
    void setLastOnline(@Param("id") Long id, @Param("time") String time);

    @Modifying
    @Transactional
    @Query("update User u set u.isBlocked = true where u.id = :id")
    void blockAcc(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.isBlocked = false where u.id = :id")
    void unblockAcc(@Param("id") Long id);
}
