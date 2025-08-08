package com.assslash.api.dto.auth;

import com.assslash.api.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespMyInfoDTO {
    private String seasonName;
    private String userName;
    private String name;
    private MemberRole role;
    private String seasonWinRate;
    private int seasonTotalWin;
    private int seasonTotalLose;
}
