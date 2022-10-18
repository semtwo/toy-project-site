package com.example.recruit.service;

import com.example.recruit.entity.RecruitEntity;
import com.example.recruit.repository.RecruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecruitService {
    @Autowired
    private RecruitRepository repository;

    public Optional<RecruitEntity>create(final RecruitEntity entity){
        validate(entity);
        repository.save(entity);
        return repository.findById(entity.getId());
    }
    public List<RecruitEntity>retrieve(final String recruitId){

        return repository.findByRecruitId(recruitId);
    }
    /*public List<RecruitEntity>findDept(final String dept){
        return repository.findAllByDept(dept);
    }*/
    public void validate(final RecruitEntity entity){
        if(entity==null){
            log.warn("Entity cannot be null");
            throw  new RuntimeException("Entity cannot be null");
        }
        if(entity.getAuthor_id()==null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
