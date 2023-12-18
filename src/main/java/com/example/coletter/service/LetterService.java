package com.example.coletter.service;


import com.example.coletter.common.BaseException;
import com.example.coletter.common.BaseResponse;
import com.example.coletter.common.BaseResponseStatus;
import com.example.coletter.model.dto.CreateLetterRequest;
import com.example.coletter.model.dto.CreateLetterResponse;
import com.example.coletter.model.dto.LetterResponse;
import com.example.coletter.model.entity.Letter;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.LetterRepository;
import com.example.coletter.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;


    // 편지 조회(1건)
    public BaseResponse<LetterResponse> selectLetter(Long memberId, Long mailboxId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow((() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND)));

        Mailbox mailbox = mailboxRepository.findById(mailboxId)
                        .orElseThrow((() -> new BaseException(BaseResponseStatus.MAILBOX_NOT_FOUND)));
    }


    // 편지 쓰기
    @Transactional
    public CreateLetterResponse createLetter(Long memberId, Long mailboxId, CreateLetterRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow((() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND)));

        Mailbox mailbox = mailboxRepository.findById(mailboxId)
                .orElseThrow((() -> new BaseException(BaseResponseStatus.MAILBOX_NOT_FOUND)));

        letterRepository.findByMemberIdAndMailboxId(member.getMember_id(), mailbox.get_id())
                .orElseThrow((() -> new BaseException(BaseResponseStatus.LETTER_ALREADY_USED)));

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
    public void deleteLetter(Long memberId, Long mailboxId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow((() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND)));

        Mailbox mailbox = mailboxRepository.findById(mailboxId)
                .orElseThrow((() -> new BaseException(BaseResponseStatus.MAILBOX_NOT_FOUND)));
        Letter letter = letterRepository.findByMemberIdAndMailboxId(member.getMember_id(), mailbox.get_id())
                .orElseThrow((() -> new BaseException(BaseResponseStatus.LETTER_NOT_FOUND)));
        letterRepository.delete(letter);
    }
}