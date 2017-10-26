package org.dw.brd.eventmanagement.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "marriage_applicant")
public class MarriageApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 15)
    private String lastName;

    private Long partnerId;

    @NotNull
    @Size(min = 5, max = 60)
    private String address;
}
