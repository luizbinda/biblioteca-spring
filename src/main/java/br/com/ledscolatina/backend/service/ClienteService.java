package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.ClienteNotFoundException;
import br.com.ledscolatina.backend.model.Cliente;
import br.com.ledscolatina.backend.model.dto.Cliente.ClienteDTO;
import br.com.ledscolatina.backend.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO create(Cliente cliente) {
        return modelMapper.map(clienteRepository.save(cliente), ClienteDTO.class);
    }

    public List<ClienteDTO> index() {
        return clienteRepository.findAll().stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO show(Long id) {
        return clienteRepository.findById(id)
                .map(record -> modelMapper.map(record, ClienteDTO.class))
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    public ClienteDTO update(Cliente cliente) {
        return clienteRepository.findById(cliente.getId())
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setSexo(cliente.getSexo());
                    record.setData_nascimento(cliente.getData_nascimento());
                    record.setEndereco(cliente.getEndereco());
                    record.setTelefone(cliente.getTelefone());
                    record.setCpf(cliente.getCpf());
                    record.setCliente(cliente.getCliente());
                    record.setAtivo(cliente.getAtivo());
                    record.setUpdatedAt(cliente.getUpdatedAt());
                    return modelMapper.map(clienteRepository.save(record), ClienteDTO.class);
                }).orElseThrow(() -> new ClienteNotFoundException(cliente.getId()));
    }

    public void delete(Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
        }
        else {
            throw new ClienteNotFoundException(id);
        }
    }

}
