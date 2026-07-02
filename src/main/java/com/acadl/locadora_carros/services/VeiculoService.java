package com.acadl.locadora_carros.services;

import com.acadl.locadora_carros.dto.VeiculoDTO;
import com.acadl.locadora_carros.model.Veiculo;
import com.acadl.locadora_carros.repositories.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository repository){
        this.veiculoRepository = repository;
    }


    public UUID cadastrarVeiculo(VeiculoDTO dto){
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(dto.getModelo());
        veiculo.setMarca(dto.getMarca());
        veiculo.setCor(dto.getCor());
        veiculo.setAlugado(dto.getAlugado());

        this.veiculoRepository.save(veiculo);

        return veiculo.getId();
    }

    public VeiculoDTO buscarPorId(UUID id){
        Veiculo veiculo = this.veiculoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado!"));


        return new VeiculoDTO(veiculo.getId(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAlugado());
    }

    public List<VeiculoDTO> buscarTodos(){
        List<Veiculo> veiculos = this.veiculoRepository.findAll();
        return veiculos.stream().map(veiculo -> new VeiculoDTO(veiculo.getId(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAlugado())).collect(Collectors.toList());

    }

    public void remover(UUID id){
        Veiculo veiculo = this.veiculoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado!"));
        this.veiculoRepository.delete(veiculo);
    }

    public VeiculoDTO editar(UUID id, VeiculoDTO dto){
        Veiculo veiculo = this.veiculoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado!"));

        veiculo.setModelo(dto.getModelo());
        veiculo.setMarca(dto.getMarca());
        veiculo.setCor(dto.getCor());
        veiculo.setAlugado(dto.getAlugado());

        Veiculo veiculoEditado = this.veiculoRepository.save(veiculo);
        return new VeiculoDTO(veiculoEditado.getId(), veiculoEditado.getModelo(), veiculoEditado.getMarca(),veiculoEditado.getCor(), veiculoEditado.getAlugado());
    }

    // Aqui vamos criar um endpoint personalizado
    // Por padrão o Java não é capaz de buscar um veiculo por modelo, por exemplo, por questões óbvias.

    public List<VeiculoDTO> buscarPorModelo(String modelo){
        List<Veiculo> veiculosPorModelo = this.veiculoRepository.findByModelo(modelo);

        if(veiculosPorModelo.isEmpty()){
            return null;
        }

        return veiculosPorModelo.stream().map(veiculo -> new VeiculoDTO(
                veiculo.getId(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getCor(), veiculo.getAlugado())).collect(Collectors.toList());
    }

}
