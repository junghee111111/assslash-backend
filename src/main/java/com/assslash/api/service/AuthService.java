package com.assslash.api.service;

import com.assslash.api.dto.auth.RespMyInfoDTO;
import com.assslash.api.dto.common.RespCode;
import com.assslash.api.dto.common.ResponseDataDto;
import com.assslash.api.dto.common.ResponseDto;
import com.assslash.api.dto.member.RegisterDTO;
import com.assslash.api.entity.Member;
import com.assslash.api.entity.Season;
import com.assslash.api.entity.SeasonLog;
import com.assslash.api.enums.MemberRole;
import com.assslash.api.repository.MemberRepository;
import com.assslash.api.repository.SeasonLogRepository;
import com.assslash.api.repository.SeasonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    // repositories
    private final MemberRepository memberRepository;
    private final SeasonRepository seasonRepository;
    private final SeasonLogRepository seasonLogRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthService(
            MemberRepository memberRepository,
            SeasonRepository seasonRepository,
            SeasonLogRepository seasonLogRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.memberRepository = memberRepository;
        this.seasonRepository = seasonRepository;
        this.seasonLogRepository = seasonLogRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<ResponseDto> registerProcess(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String name = registerDTO.getName();

        Boolean isUsernameExists = memberRepository.existsByUsername(username);
        Boolean isNameExists = memberRepository.existsByName(name);

        if (isUsernameExists) {
            return ResponseDto.of(RespCode.REGISTER_USERNAME_EXISTS);
        }

        if (isNameExists) {
            return ResponseDto.of(RespCode.REGISTER_NAME_EXISTS);
        }

        Member newMember = new Member();
        newMember.setUsername(username);
        newMember.setPassword(bCryptPasswordEncoder.encode(password));
        newMember.setName(name);
        newMember.setRole(MemberRole.ROLE_USER);
        memberRepository.save(newMember);

        return ResponseDto.of(RespCode.OK);
    }

    public ResponseEntity<ResponseDataDto<RespMyInfoDTO>> getMyInfo(Member member) {
        Member foundMember = memberRepository.findByUsername(member.getUsername());
        RespMyInfoDTO dto = new RespMyInfoDTO();

        if (foundMember == null) {
            return ResponseDataDto.of(RespCode.MEMBER_NOT_FOUND, dto);
        }

        dto.setUserName(foundMember.getUsername());
        dto.setName(foundMember.getName());
        dto.setRole(foundMember.getRole());

        // load season info
        Season currentSeason = seasonRepository.findFirstByIsCurrent(1);
        if (currentSeason == null) {
            return ResponseDataDto.of(RespCode.METHOD_NOT_ALLOWED, dto);
        }

        SeasonLog seasonLog = seasonLogRepository.findFirstByMemberIdAndSeasonId(
                foundMember.getId(), currentSeason.getId()
        );

        if (seasonLog == null) {
            SeasonLog newSeasonLog = SeasonLog.builder()
                    .memberId(foundMember.getId())
                    .seasonId(currentSeason.getId())
                    .win(0)
                    .lose(0)
                    .draw(0)
                    .winRate(0)
                    .totalMatch(0)
                    .build();
            seasonLogRepository.save(newSeasonLog);
            seasonLog = newSeasonLog;
        }
        dto.setSeasonTotalWin(seasonLog.getWin());
        dto.setSeasonTotalLose(seasonLog.getLose());
        dto.setSeasonWinRate(String.format("%.2f", (seasonLog.getWinRate() * 100)) + "%");

        dto.setSeasonName(currentSeason.getName());

        return ResponseDataDto.of(RespCode.OK, dto);
    }
}
