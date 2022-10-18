package com.example.recruit.controller;

import com.example.recruit.dto.ApplyDTO;
import com.example.recruit.dto.RecruitDTO;
import com.example.recruit.dto.ResponseDTO;
import com.example.recruit.entity.ApplyEntity;
import com.example.recruit.entity.RecruitEntity;
import com.example.recruit.repository.RecruitRepository;
import com.example.recruit.service.ApplyService;
import com.example.recruit.service.RecruitService;
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
@RequestMapping("/project")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;
    @Autowired
    private RecruitRepository recruitRepository;

    @PostMapping
    public ResponseEntity<?> createRecruit(
            @AuthenticationPrincipal String recruitId,
            @RequestBody RecruitDTO dto){
        try{
            log.info("Log: createRecruitment entrance");
            RecruitEntity entity = RecruitDTO.to_entity(dto);
            log.info("Log: dto => entity ok!");
            //entity.setRecruitId("temporary-userid");
            entity.setAuthor_id(recruitId);

            Optional<RecruitEntity> entites = recruitService.create(entity);
            log.info("Log:  service.create ok!");

            List<RecruitDTO> dtos = entites.stream().map(RecruitDTO::new).collect(Collectors.toList());
            log.info("Log: entites => dtos ok!");

            ResponseDTO<RecruitDTO> response = ResponseDTO.<RecruitDTO>builder().data(dtos).build();
            log.info("Log: responseDTO ok!");

            return ResponseEntity.ok().body(response);
        }
        catch (Exception e){
            ResponseDTO<RecruitDTO> response = ResponseDTO.<RecruitDTO>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping
    public ResponseEntity<?>retrieveRecruit(@AuthenticationPrincipal String recruitId){
        String temporaryUserId = "temporary-userid";
        //List<RecruitEntity> entities = service.retrieve(temporaryUserId);
        log.info("{}", recruitId);
        List<RecruitEntity> entities = recruitService.retrieve(recruitId);
        List<RecruitDTO> dtos = entities.stream().map(RecruitDTO::new).collect(Collectors.toList());

        /*List<RecruitEntity> depts = recruitRepository.findAllByDept();
        depts.forEach(d->{
            System.out.println(d);
            System.out.println(d.getId());
        });
         */
        ResponseDTO<RecruitDTO> response = ResponseDTO.<RecruitDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    //Apply
    /*@Autowired
    private ApplyService applyService;
    @PostMapping("/apply")
    public ResponseEntity<?> createApply(@RequestBody ApplyDTO dto){
        try{
            log.info("Log: createApply entrance");
            ApplyEntity entity = ApplyDTO.to_entity(dto);
            log.info("Log: dto => entity ok!");

            entity.setA_id("temporary-A-id");

            Optional<ApplyEntity> entites = applyService.create(entity);
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
    }*/

}
