package com.acadl.locadora_carros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class VeiculoDTO {

    private UUID Id;
    private String modelo;
    private String marca;
    private String cor;
    private Boolean alugado;

}
