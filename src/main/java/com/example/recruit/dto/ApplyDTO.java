package com.example.recruit.dto;

import com.example.recruit.entity.ApplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDTO {
    private String p_id;
    private String a_id;
    private String contents;

    public ApplyDTO(final ApplyEntity entity){
        this.p_id = entity.getRecruitment_id();
        this.a_id = entity.getMember_id();
        this.contents = entity.getContents();
    }
    public static ApplyEntity to_entity(final ApplyDTO dto){
        return ApplyEntity.builder()
                .recruitment_id(dto.getP_id())
                .member_id(dto.getA_id())
                .contents(dto.getContents())
                .build();
    }
}
