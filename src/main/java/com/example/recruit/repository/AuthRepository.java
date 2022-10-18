package com.example.recruit.repository;

import com.example.recruit.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    MemberEntity findByEmailAndPassword(String email, String password);

    //Optional<MemberEntity> findByEmailAndPassword(String email, String password);
    //@Query("select t from MemberEntity t")
    //Optional<MemberEntity> findById(String id);
}
