package com.example.coletter.repository;

import com.example.coletter.model.entity.Mailbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailboxRepository extends JpaRepository<Mailbox,Long> {


}
