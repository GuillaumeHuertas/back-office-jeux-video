package com.iosis.backofficejeuxvideo.model.metier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iosis.backofficejeuxvideo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "jeux_video")
public class JeuxVideo extends AuditModel {

    @Id
    @GeneratedValue(generator = "jeux_video_generator")
    @SequenceGenerator(
            name = "jeux_video_generator",
            sequenceName = "jeux_video_sequence",
            initialValue = 1000
    )
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Getter
    @Setter
    private String titre;

    @Getter
    @Setter
    private String developpeur;

    @Getter
    @Setter
    private String editeur;

    @Getter
    @Setter
    private boolean statut;

    @Max(20)
    @Min(0)
    @Getter
    @Setter
    private int note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "console_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @Getter
    @Setter
    private Console console;

    @Column(columnDefinition = "text")
    @Getter
    @Setter
    private String description;
}
