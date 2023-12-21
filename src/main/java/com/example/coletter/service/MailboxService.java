package com.example.coletter.service;


import com.example.coletter.common.BaseException;
import com.example.coletter.model.dto.CreateMailboxResponse;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.repository.MailboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.example.coletter.common.BaseResponseStatus.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailboxService {


    private final MailboxRepository mailboxRepository;


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


}