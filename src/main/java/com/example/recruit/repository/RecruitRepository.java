package com.example.recruit.repository;

import com.example.recruit.entity.RecruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.recruit.entity.MemberEntity;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<RecruitEntity,String> {
    @Query("select t from RecruitEntity t where t.author_id = ?1")
    List<RecruitEntity>findByRecruitId(String recruitId);
    /*@Query("select t from RecruitEntity t join fetch t.dept")
    List<RecruitEntity> findAllByDept();
     */
}
