package com.example.recruit.controller;

import com.example.recruit.dto.MemberDTO;
import com.example.recruit.dto.ResponseDTO;
import com.example.recruit.entity.MemberEntity;
import com.example.recruit.entity.RecruitEntity;
import com.example.recruit.repository.RecruitRepository;
import com.example.recruit.security.TokenProvider;
import com.example.recruit.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/signup")//회원가입
    public ResponseEntity<?> createMember(@RequestBody MemberDTO memberDTO){
        //ResponseEntity<?>
        log.warn("created");
        try{
            MemberEntity member = MemberDTO.to_entity(memberDTO);
            log.info("Log: dto => entity created!");
            Optional<MemberEntity> createdMember = memberService.create(member);
            log.info("Log: entity created");
            List<MemberDTO> dtos = createdMember.stream().map(MemberDTO::new).collect(Collectors.toList());
            log.info("Log: dtos created");
            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(dtos).build();
            log.info("Log: responseDTO created");
            return ResponseEntity.ok().body(response);
            //return "singUp running";
        }
        catch (Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
            //return "error";
        }
    }
    @PostMapping("/signin")//로그인
    public ResponseEntity<?>authenticate(
            @AuthenticationPrincipal String userId,
            @RequestBody MemberDTO memberDTO){
        MemberEntity member = memberService.getByCredentials(memberDTO.getEmail(), memberDTO.getPassword());
        //Optional<MemberEntity> member = memberService.getByCredentials(memberDTO.getEmail(), memberDTO.getPassword());

        if(member != null){
            final String token = tokenProvider.create(member);
            //List<MemberDTO> dtos = member.stream().map(MemberDTO::new).collect(Collectors.toList());
            final MemberDTO responseMemberDTO = MemberDTO.builder()
                    .email(member.getEmail())
                    .id(member.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseMemberDTO);
        }
        else{
            ResponseDTO responseDTO = ResponseDTO.builder().error("Login failed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
