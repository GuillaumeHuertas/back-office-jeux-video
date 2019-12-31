package com.iosis.backofficejeuxvideo.repository;

import com.iosis.backofficejeuxvideo.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
