package com.vcnc.memo.repository;

import com.vcnc.memo.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    @Query("select m from Memo m join fetch m.member where m.id = :id")
    Optional<Memo> findOneWithMember(@Param("id") Long id);

    @Query("select m from Memo m join fetch m.member")
    List<Memo> findAllWithMember();

}
