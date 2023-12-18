package com.example.coletter.repository;

import com.example.coletter.model.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter,Long> {
    Letter findByMemberIdAndMailboxId(long memberId, long mailboxId);
}
