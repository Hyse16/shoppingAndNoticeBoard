package com.project.shoppingAndNoticeBoard.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.shoppingAndNoticeBoard.Service.MemberService;
import com.project.shoppingAndNoticeBoard.entity.KakaoProfile;
import com.project.shoppingAndNoticeBoard.entity.Member;
import com.project.shoppingAndNoticeBoard.entity.OAuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor
@Controller

public class KakaoController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/auth/kakao/callback")
    public String authLogin(String code) {

        //HttpHeader 오브젝트 생성
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "dd1f34327fe4c230bae90b6a4b9d0b11");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - Post방식으로 - 그리고 response  변수의 응답을 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 액세스 토큰 :" + oAuthToken.getAccess_token());
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();

        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        // Http 요청하기 - Post방식으로 - 그리고 response  변수의 응답을 받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        System.out.println(response2.getBody());


        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //User 오브젝트 : username password, email, address
        System.out.println("카카오 아이디(번호) :" + kakaoProfile.getId());
        System.out.println("카카오 이메일 :" + kakaoProfile.getKakao_account().getEmail());

        System.out.println("쇼핑몰 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("쇼핑몰 서버 이메일 " + kakaoProfile.getKakao_account().getEmail());
        UUID garbagePw = UUID.randomUUID();
        System.out.println("쇼핑몰 패스워드:" + garbagePw);

        Member member = Member.builder()
                .name(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(garbagePw.toString())
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();


        memberService.saveMember(member);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getName(), member.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
