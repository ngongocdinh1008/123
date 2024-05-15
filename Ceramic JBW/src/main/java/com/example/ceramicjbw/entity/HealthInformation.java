package com.example.ceramicjbw.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "health_information")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "image_url")
    private String image;

    private String information;

    @ManyToOne
    @JoinColumn(name = "category_health_information_id")
    private CategoryHealthinformation categoryHealthinformation;

    @OneToMany(mappedBy = "healthInformation", cascade = CascadeType.ALL)
    private List<Details> details;
}
