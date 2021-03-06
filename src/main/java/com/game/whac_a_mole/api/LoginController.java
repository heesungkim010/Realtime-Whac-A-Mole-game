package com.game.whac_a_mole.api;

import com.game.whac_a_mole.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final MemberRepository memberRepository;

    @PostMapping("/api/members/login")
    public LoginMemberResponse loginMember(@RequestBody CreateMemberRequest request
                                           ){ // request received
        boolean isLoginSuccess = memberRepository.saveMember(request.userId);

        if (isLoginSuccess){ // login success.
            //if login success, set session.

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
