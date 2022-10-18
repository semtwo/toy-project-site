package com.example.recruit.repository;

import com.example.recruit.entity.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyRepository extends JpaRepository<ApplyEntity,String> {
    @Query("select t from ApplyEntity t where t.member_id =? 1")
    List<ApplyEntity>findByA_id(String a_id);

}
