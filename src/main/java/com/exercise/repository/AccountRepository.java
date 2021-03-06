package com.exercise.repository;

import com.exercise.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    Optional<Account> findById(Long id);
    @Lock(LockModeType.OPTIMISTIC)
    Optional<Account> findByUserId(Long userId);
}
