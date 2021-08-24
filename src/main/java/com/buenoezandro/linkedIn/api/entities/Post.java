package com.buenoezandro.linkedIn.api.entities;

import com.buenoezandro.linkedIn.api.enums.Anexo;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Anexo anexo;

    @NotBlank(message = "O campo TEXTO é obrigatório!!!")
    private String texto;

}