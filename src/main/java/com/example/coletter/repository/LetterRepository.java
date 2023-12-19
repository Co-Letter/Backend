package com.example.coletter.repository;

import com.example.coletter.model.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter,Long> {
    Optional<Letter> findByMemberIdAndMailboxId(long memberId, long mailboxId);
}
