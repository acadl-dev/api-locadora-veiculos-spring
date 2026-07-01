package com.acadl.locadora_carros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tb_veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    //@Column(name="md", nulllable = false)
    private String modelo;
    private String marca;
    private String cor;
    private Boolean alugado;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
