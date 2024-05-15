package com.example.ceramicjbw.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "keyword")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_word")
    private String keyword;

    @ManyToOne
    @JoinColumn(name = "disease_id ")
    private Diseases disease;

    @ManyToOne
    @JoinColumn(name = "posts_id ")
    private HealthInformation  posts;
}
