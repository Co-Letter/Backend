package com.example.coletter.repository;

import com.example.coletter.model.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LetterRepository extends JpaRepository<Letter,Long> {
    Optional<Letter> findByMemberMemberIdAndMailboxMailboxId(Long memberId, Long mailboxId);

    List<Letter> findByMailboxMailboxIdOrderByCreateAtDesc(Long mailboxId);

}
