package com.acadl.locadora_carros.repositories;

import com.acadl.locadora_carros.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository <Veiculo, UUID>{
}
