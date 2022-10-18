package com.example.recruit.dto;

import com.example.recruit.entity.RecruitEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitDTO {
    private String title;
    private String dept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    private String contents;
    private int rnum;
    private String recruitId;

    public RecruitDTO(final RecruitEntity entity){
        this.title = entity.getTitle();
        this.dept = entity.getDept();
        this.fromDate = entity.getFromDate();
        this.toDate = entity.getToDate();
        this.contents = entity.getContents();
        this.rnum = entity.getRnum();
        this.recruitId = entity.getAuthor_id();
    }
    public static RecruitEntity to_entity(final RecruitDTO dto){
        return RecruitEntity.builder().title(dto.getTitle()).dept(dto.getDept())
                .fromDate(dto.getFromDate()).toDate(dto.getToDate()).contents(dto.getContents())
                .rnum(dto.getRnum()).author_id(dto.getRecruitId()).build();
    }
}
