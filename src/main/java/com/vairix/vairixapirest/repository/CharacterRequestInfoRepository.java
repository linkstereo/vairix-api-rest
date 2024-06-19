package com.vairix.vairixapirest.repository;

import com.vairix.vairixapirest.model.CharacterRequestInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRequestInfoRepository extends JpaRepository<CharacterRequestInfo, Long> {

    @Override
    Page<CharacterRequestInfo> findAll(Pageable pageable);

    @Override
    List<CharacterRequestInfo> findAll(Sort sort);
}
