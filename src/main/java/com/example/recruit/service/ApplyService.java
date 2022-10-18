package com.example.recruit.service;

import com.example.recruit.entity.ApplyEntity;
import com.example.recruit.repository.ApplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApplyService {

    @Autowired
    private ApplyRepository repository;

    public Optional<ApplyEntity>create(final ApplyEntity entity){
        validate(entity);
        repository.save(entity);
        return repository.findById(entity.getId());
    }
    public List<ApplyEntity>retrieve(final String a_id){
        return repository.findByA_id(a_id);
    }
    public void validate(final ApplyEntity entity){
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if(entity.getMember_id()==null){
            log.warn("Unknown user.");
            throw  new RuntimeException("Unknown user.");
        }
    }
}
