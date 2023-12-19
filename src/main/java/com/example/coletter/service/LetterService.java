package com.example.coletter.service;


import com.example.coletter.common.BaseException;
import com.example.coletter.common.BaseResponseStatus;
import com.example.coletter.jwt.JwtTokenProvider;
import com.example.coletter.model.dto.CreateLetterRequest;
import com.example.coletter.model.dto.CreateLetterResponse;
import com.example.coletter.model.entity.Letter;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.LetterRepository;
import com.example.coletter.repository.MemberRepository;
import com.example.coletter.repository.MailboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;
    private final MailboxRepository mailboxRepository;
    private final JwtTokenProvider jwtTokenProvider;

//
////     편지 조회(1건)
//    public BaseResponse<LetterResponse> selectLetter(String accessToken, Long mailboxId) {
//        Member member = validMemberById(accessToken);
//        Mailbox mailbox = validMailboxById(mailboxId);
//
//
//    }


    // 편지 쓰기
    @Transactional
    public CreateLetterResponse createLetter(String accessToken, Long mailboxId, CreateLetterRequest request) {
        Member member = validMemberById(accessToken);
        Mailbox mailbox = validMailboxById(mailboxId);

        Optional<Letter> letterValid = letterRepository.findByMemberMemberIdAndMailboxMailboxId(member.getMemberId(), mailbox.getMailboxId());
        if (letterValid.isPresent()) throw new BaseException(BaseResponseStatus.LETTER_ALREADY_USED);

        Letter letter = Letter.builder()
                .content(request.getContent())
                .writer(request.getWriter())
                .secret(request.getSecret())
                .background(request.getBackground())
                .build();

        letterRepository.save(letter);

        return new CreateLetterResponse(letter.getId());
    }

    // 편지 삭제
    @Transactional
    public void deleteLetter(String accessToken, Long mailboxId) {
        Member member = validMemberById(accessToken);
        Mailbox mailbox = validMailboxById(mailboxId);


        Letter letter = letterRepository.findByMemberMemberIdAndMailboxMailboxId(member.getMemberId(), mailbox.getMailboxId())
                .orElseThrow((() -> new BaseException(BaseResponseStatus.LETTER_NOT_FOUND)));
        letterRepository.delete(letter);
    }


    private Member validMemberById(String accessToken) {
        Long memberId = jwtTokenProvider.extractId(accessToken);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND));
    }

    private Mailbox validMailboxById(Long mailboxId) {
        return mailboxRepository.findById(mailboxId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.MAILBOX_NOT_FOUND));
    }
}