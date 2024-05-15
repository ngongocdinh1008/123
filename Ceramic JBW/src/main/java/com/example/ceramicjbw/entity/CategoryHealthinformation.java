package com.example.ceramicjbw.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_healthinformation")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryHealthinformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
