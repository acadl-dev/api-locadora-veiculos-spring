package com.acadl.locadora_carros.controllers;

import com.acadl.locadora_carros.dto.VeiculoDTO;
import com.acadl.locadora_carros.services.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarVeiculo(@RequestBody VeiculoDTO dto ){

        UUID id = this.veiculoService.cadastrarVeiculo(dto);

        return ResponseEntity.status(201).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable UUID id){
        VeiculoDTO veiculoDTO = this.veiculoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(veiculoDTO);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> buscarTodos(){
        List<VeiculoDTO> veiculosDTO = this.veiculoService.buscarTodos();
        return ResponseEntity.status(200).body(veiculosDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        this.veiculoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> editar(@PathVariable UUID id, @RequestBody VeiculoDTO dto){
        VeiculoDTO veiculoDTO = this.veiculoService.editar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(veiculoDTO);
    }

    @GetMapping("/buscarPorModelo/{modelo}")
    public ResponseEntity<List<VeiculoDTO>> buscarPorModelo(@PathVariable String modelo){
        List<VeiculoDTO> veiculosDTO = this.veiculoService.buscarPorModelo(modelo);
        return ResponseEntity.status(HttpStatus.OK).body(veiculosDTO);
    }

}
