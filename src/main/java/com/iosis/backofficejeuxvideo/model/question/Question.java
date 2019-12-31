package com.iosis.backofficejeuxvideo.model.question;

import com.iosis.backofficejeuxvideo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name= "question")
public class Question extends AuditModel {

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Getter
    @Setter
    private String title;

    @Column(columnDefinition = "text")
    @Getter
    @Setter
    private String description;
}
