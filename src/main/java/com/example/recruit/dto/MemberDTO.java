package com.example.recruit.dto;

import com.example.recruit.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String membername;
    private String email;
    private String password;
    private String dept;
    private int rating;
    private int pnum;
    private String id;
    private String token;

    public MemberDTO(final MemberEntity entity) {
        this.membername = entity.getMembername();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.dept = entity.getDept();
        this.rating = entity.getRating();
        this.pnum = entity.getPnum();

    }

    public static MemberEntity to_entity(final MemberDTO dto) {
        return MemberEntity.builder().membername(dto.getMembername()).password(dto.getPassword()).dept(dto.getDept()).email(dto.getEmail())
                .rating(dto.getRating()).pnum(dto.getPnum()).build();
    }
}
