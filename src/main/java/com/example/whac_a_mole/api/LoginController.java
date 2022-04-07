package com.example.whac_a_mole.api;

import com.example.whac_a_mole.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final MemberRepository memberRepository;

    @PostMapping("/api/members/login")
    public LoginMemberResponse loginMember(@RequestBody CreateMemberRequest request,
                                           HttpServletRequest servletRequest){ // request received
        log.info("userId : {}",request.userId);
        boolean isLoginSuccess = memberRepository.saveMember(request.userId);

        if (isLoginSuccess){ // login success.
            //if login success, set session.
            HttpSession session = servletRequest.getSession(true);
            session.setAttribute(SessionConst.LOGIN_MEMBER, request.userId);
            //save session-data
            return new LoginMemberResponse(true); //did_login : true
        }

        //if matches : login success, set session.
        return new LoginMemberResponse(false); //did_login : false
    }

    @Data
    static class CreateMemberRequest{
        private String userId;
    }

    @Data
    class LoginMemberResponse{
        private boolean didLogin;
        public LoginMemberResponse(boolean didLogin){
            this.didLogin = didLogin;
        }
    }
}
