package com.example.ProjectTrelloinSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Type(type = "text")
    private String description;
    private int status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Folders folder;

}
