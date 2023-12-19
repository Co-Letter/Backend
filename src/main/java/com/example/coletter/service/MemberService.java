package com.example.coletter.service;

import com.example.coletter.jwt.JwtTokenProvider;
import com.example.coletter.common.BaseException;
import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.coletter.common.BaseResponseStatus.*;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {

        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;

    }



    public void deleteUser(Long id) throws BaseException {
        try {
            Optional<Member> member = memberRepository.findById(id);
            if (member.isPresent()) {
                memberRepository.delete(member.get());
            } else {
                throw new BaseException(EMPTY_JWT);
            }
        } catch (ExpiredJwtException exception) {
            throw new BaseException(INVALID_JWT);
        }
    }

}
