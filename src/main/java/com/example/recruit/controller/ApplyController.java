package com.example.recruit.controller;

import com.example.recruit.dto.ApplyDTO;
import com.example.recruit.dto.ResponseDTO;
import com.example.recruit.entity.ApplyEntity;
import com.example.recruit.entity.RecruitEntity;
import com.example.recruit.repository.RecruitRepository;
import com.example.recruit.service.ApplyService;
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
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService service;

    @Autowired
    private RecruitRepository recruitRepository;

    @PostMapping
    public ResponseEntity<?> createApply(
            @RequestBody ApplyDTO dto,
            @AuthenticationPrincipal String applyId
    ){
        try{
            log.info("Log: createApply entrance");
            ApplyEntity entity = ApplyDTO.to_entity(dto);
            log.info("Log: dto => entity ok!");

            //entity.setA_id("temporary-A-id");
            entity.setMember_id(applyId);
            entity.setRecruitment_id("temporary-recruitid");

            List<RecruitEntity> recruitList= recruitRepository.findAll();


            Optional<ApplyEntity> entites = service.create(entity);
            log.info("Log: service.create ok!");
            List<ApplyDTO> dtos = entites.stream().map(ApplyDTO::new).collect(Collectors.toList());
            log.info("Log: entites => dtos ok!");
            ResponseDTO<ApplyDTO> response = ResponseDTO.<ApplyDTO>builder().data(dtos).build();
            log.info("Log: responseDTO ok!");

            return ResponseEntity.ok().body(response);
        }
        catch (Exception e){
            ResponseDTO<ApplyDTO> response = ResponseDTO.<ApplyDTO>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping
    public ResponseEntity<?>retrieveApply(@AuthenticationPrincipal String applyId){
        String temporaryId = "temporary-A-id";
        List<ApplyEntity> entities = service.retrieve(applyId);
        List<ApplyDTO> dtos = entities.stream().map(ApplyDTO::new).collect(Collectors.toList());
        log.warn("Log: retrieveApplyDTO ok!");
        ResponseDTO<ApplyDTO> response = ResponseDTO.<ApplyDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }
}
