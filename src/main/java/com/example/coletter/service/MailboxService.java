package com.example.coletter.service;


import com.example.coletter.common.BaseException;
import com.example.coletter.common.BaseResponseStatus;
import com.example.coletter.jwt.JwtTokenProvider;
import com.example.coletter.model.dto.CreateLetterRequest;
import com.example.coletter.model.dto.CreateLetterResponse;
import com.example.coletter.model.dto.CreateMailboxRequest;
import com.example.coletter.model.dto.CreateMailboxResponse;
import com.example.coletter.model.entity.Letter;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.LetterRepository;
import com.example.coletter.repository.MailboxRepository;
import com.example.coletter.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailboxService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;
    private final MailboxRepository mailboxRepository;
    private final JwtTokenProvider jwtTokenProvider;



    // 편지함 생성
    @Transactional
    public CreateMailboxResponse createMailbox(){
      String title = "제목을 입력해주세요.";
        Mailbox mailbox = new Mailbox(title);
        mailboxRepository.save(mailbox);
        return new CreateMailboxResponse(mailbox.getMailboxId());
    }

}