package com.app.vple.domain.dto;

import com.app.vple.domain.User;
import com.app.vple.domain.enums.Age;
import com.app.vple.domain.enums.Gender;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailDto {

    private Long id;

    private String email;

    private String nickname;

    private Gender gender;

    private Age age;

    private String image;

    private Integer followers;

    private Integer followings;

    private Integer planCount;

    private List<UserWrittenPlanListDto> myPlans;

    private String introduction;

    public UserDetailDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.image = user.getImage();
        this.myPlans = user.getPlans().stream().map(
                UserWrittenPlanListDto::new
        ).collect(Collectors.toList());
    }
}
