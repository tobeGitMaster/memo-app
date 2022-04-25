package com.vcnc.memo.repository;

import com.vcnc.memo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNickname(String nickname);

}
