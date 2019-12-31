package com.iosis.backofficejeuxvideo.repository;

import com.iosis.backofficejeuxvideo.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Long> {

    List<Console> findConsoleByNameContaining(String consoleName);

    @Query("SELECT c FROM Console c WHERE c.code = :code")
    List<Console> findByConsoleCode(@Param("code") String code);
}
