package com.example.coletter.service;


import com.example.coletter.common.BaseException;
import com.example.coletter.common.BaseResponse;
import com.example.coletter.jwt.JwtTokenProvider;
import com.example.coletter.model.dto.CreateMailboxResponse;
import com.example.coletter.model.entity.Letter;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.MailboxRepository;
import com.example.coletter.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static com.example.coletter.common.BaseResponseStatus.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailboxService {


    private final MailboxRepository mailboxRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final Letter letter;


    // 편지함 생성
    @Transactional
    public CreateMailboxResponse createMailbox() {
        try {
            final String title = "제목을 입력해주세요.";
            Mailbox mailbox = new Mailbox(title);
            mailboxRepository.save(mailbox);
            return new CreateMailboxResponse(mailbox.getMailboxId());
        } catch (BaseException e) {
            throw new BaseException(MAILBOX_NOT_FOUND);
        }
    }


    // 편지함 삭제
    @Transactional
    public void deleteMailbox(Mailbox mailbox) {
        try{
          mailboxRepository.delete(mailbox);
        } catch (BaseException exception){
            throw new BaseException(MAILBOX_NOT_FOUND);
        }


    }

    public Long countMail(String accessToken){
        Long memberId = jwtTokenProvider.extractId(accessToken);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(INVALID_JWT));

        Long mailboxId = member.getMailbox().getMailboxId();

        Mailbox mailbox = mailboxRepository.findById(mailboxId)
                .orElseThrow(() -> new BaseException(MAILBOX_NOT_FOUND));
        return (long) mailbox.getLetters().size();
    }

    @Transactional
    public Long updateMailboxTitle(String accessToken, String title) {
        Long memberId = jwtTokenProvider.extractId(accessToken);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(INVALID_JWT));
        Long mailboxId = member.getMailbox().getMailboxId();

        Mailbox mailbox = mailboxRepository.findById(mailboxId)
                .orElseThrow(() -> new BaseException(MAILBOX_NOT_FOUND));
        System.out.println("before" + mailbox.getTitle()+" "+mailbox.getMailboxId()+" "+mailbox.getCreate_at());
        mailbox.changeTitle(title);
        System.out.println(mailbox.getTitle()+" "+mailbox.getMailboxId()+" "+mailbox.getCreate_at());
        mailboxRepository.save(mailbox);
        return mailbox.getMailboxId();
    }

    public List<Letter> getAllLetter(String accessToken) throws BaseException {
        Long memberId = jwtTokenProvider.extractId(accessToken);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(INVALID_JWT));

        List<Letter> letters = member.getMailbox().getLetters();

        return letters;
    }


}