package com.example.coletter.service;

import com.example.coletter.jwt.JwtTokenProvider;
import com.example.coletter.common.BaseException;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.coletter.common.BaseResponseStatus.*;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailboxService mailboxService;

    public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider, MailboxService mailboxService) {

        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailboxService = mailboxService;
    }


    @Transactional
    public void deleteUser(Long id) throws BaseException {
        try {
            Optional<Member> member = memberRepository.findById(id);
            if (member.isPresent()) {
                Mailbox mailbox = member.get().getMailbox();
                memberRepository.delete(member.get());
                mailboxService.deleteMailbox(mailbox);

            } else {
                throw new BaseException(EMPTY_JWT);
            }
        } catch (ExpiredJwtException exception) {
            throw new BaseException(INVALID_JWT);
        }
    }

    @Transactional(readOnly = true)
    public Member getMember(Long Id) throws BaseException {
        try{
            Optional<Member> member = memberRepository.findById(Id);
            if (member.isPresent()){
                return member.get();
            } else
                throw new BaseException(EMPTY_JWT);
        } catch (ExpiredJwtException exception){
            throw new BaseException(INVALID_JWT);
        }
    }

}
