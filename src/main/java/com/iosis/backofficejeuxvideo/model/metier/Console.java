package com.iosis.backofficejeuxvideo.model.metier;

import com.iosis.backofficejeuxvideo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "console")
public class Console extends AuditModel {

    @Id
    @GeneratedValue(generator = "console_generator")
    @SequenceGenerator(
            name = "console_generator",
            sequenceName = "console_sequence",
            initialValue = 1000
    )
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String constructeur;
}
