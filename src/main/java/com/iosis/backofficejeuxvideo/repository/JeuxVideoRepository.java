package com.iosis.backofficejeuxvideo.repository;

import com.iosis.backofficejeuxvideo.model.JeuxVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JeuxVideoRepository extends JpaRepository<JeuxVideo, Long> {

    @Query("SELECT j FROM JeuxVideo j WHERE j.titre LIKE %:titre%")
    List<JeuxVideo> findJeuxVideoByPartOfTitre(@Param("titre") String titre);

    List<JeuxVideo> findJeuxVideoByTitreContaining(String jeuxVideoTitre);

    @Query("SELECT j FROM JeuxVideo j WHERE j.titre = :titre")
    List<JeuxVideo> findJeuxVideoByTitre(@Param("titre") String titre);

    List<JeuxVideo> findByConsoleId(Long consoleId);
}
