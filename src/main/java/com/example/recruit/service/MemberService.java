package com.example.recruit.service;

import com.example.recruit.entity.MemberEntity;
import com.example.recruit.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private AuthRepository authRepository;

    public Optional<MemberEntity> create(final MemberEntity memberEntity) {
        if (memberEntity == null || memberEntity.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String email = memberEntity.getEmail();
        if (authRepository.existsByEmail(email)) {
            log.warn("Email already exist{}", email);
            throw new RuntimeException("Email already exists");
        }
        authRepository.save(memberEntity);
        return authRepository.findById(memberEntity.getId());
        //return authRepository.findByEmail(memberEntity.getId());
    }

    public MemberEntity getByCredentials(final String email, final String password){
        return authRepository.findByEmailAndPassword(email, password);
    }
//    public Optional<MemberEntity> getByCredentials(final String email, final String password){
//        return authRepository.findByEmailAndPassword(email, password);
//    }
}
