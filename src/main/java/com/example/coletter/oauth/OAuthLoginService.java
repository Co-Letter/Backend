package com.example.coletter.oauth;

import com.example.coletter.common.BaseException;
import com.example.coletter.common.BaseResponseStatus;
import com.example.coletter.model.dto.CreateMailboxResponse;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.MailboxRepository;
import com.example.coletter.repository.MemberRepository;
import com.example.coletter.service.MailboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;
    private final MailboxService mailboxService;
    private final MailboxRepository mailboxRepository;

    private final List<String> firstNames = Arrays.asList("가냘픈", "강한", "거친", "고운", "괜찮은", "귀여운", "기쁜", "깔끔한", "길쭉한", "날랜", "느린", "뛰어난", "멋진", "무거운", "무딘", "무서운", "바람직한", "밝은", "보드라운", "부드러운", "빠른", "새로운", "서툰", "센", "수다스러운", "수줍은", "슬픈", "아름다운", "새침한", "안쓰러운", "어린", "언짢은", "엄청난", "예쁜", "외로운", "의심쩍은", "작은", "잘난", "잘빠진", "재미있는", "젊은", "점잖은", "즐거운", "지혜로운", "짖궃은", "큰", "한결같은", "희망찬");
    private final List<String> middleNames = Arrays.asList("붉은", "검은", "금빛", "은빛", "하얀", "흰", "새까만", "노오란", "누런", "노란빛", "초록색", "푸른", "파란", "쪽빛", "보랏빛", "거무죽죽한", "불그스름한", "노르스름한", "노릇노릇한", "빨간", "주홍색", "빨간맛", "보라색", "파란색", "남색", "어두운", "밝은", "회색", "진회색", "먹색", "분홍색", "하이얀", "새하얀", "비취색", "옥색", "갈색", "자주색", "쥐색", "하늘색", "녹색", "루비빛", "초콜릿빛", "새파란", "희끗희끗한", "거무스름한", "불긋불긋한", "파릇파릇한", "거뭇거뭇한", "까만");
    private final List<String> lastNames = Arrays.asList("강아지", "고양이", "거북이", "기린", "곰", "돼지", "독수리", "닭", "소", "삵", "스라소니", "오소리", "오리", "두루미", "고슴도치", "두더지", "우파루파", "맹꽁이", "칠면조", "너구리", "카멜레온", "노루", "이구아니", "염소", "들소", "바다표범", "미어캣", "아르마딜로", "비둘기", "스컹크", "여우", "사슴", "늑대", "양", "알파카", "다람쥐", "담비", "북극곰", "퓨마", "카피바라", "래서팬더", "판다", "얼룩말", "산양", "바다코끼리", "라마", "돌고래", "표범", "딱따구리");

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {

        return memberRepository.findByKakaoId(oAuthInfoResponse.getKakao())
                .map(Member::getMemberId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {

        CreateMailboxResponse createMailboxResponse = mailboxService.createMailbox();
        Mailbox mailbox = mailboxRepository.findById(createMailboxResponse.getId())
                .orElseThrow((() -> new BaseException(BaseResponseStatus.MAILBOX_NOT_FOUND)));

        Member member = Member.builder()
                .member_nickname(randomnamegenerater()) ///차후 리스트에서 가져오기
                .member_profile_image(oAuthInfoResponse.getProfile_image_url())
                .kakaoId(oAuthInfoResponse.getKakao())
                .mailbox(mailbox)
                .build();

        return memberRepository.save(member).getMemberId();
    }

    private String randomnamegenerater(){

        Random random = new Random();
        String firstName = getRandomElement(firstNames, random);
        String middleName = getRandomElement(middleNames, random);
        String lastName = getRandomElement(lastNames, random);

        return firstName + middleName + lastName;
    }

    private String getRandomElement(List<String> list, Random random) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
