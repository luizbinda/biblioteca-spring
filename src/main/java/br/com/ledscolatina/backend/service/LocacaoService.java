package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.ClienteNotActiveException;

import br.com.ledscolatina.backend.except.custom.ItemLocadoException;
import br.com.ledscolatina.backend.except.custom.LocacaoNotFoundException;
import br.com.ledscolatina.backend.model.Cliente;
import br.com.ledscolatina.backend.model.Item;
import br.com.ledscolatina.backend.model.Locacao;
import br.com.ledscolatina.backend.model.dto.Locacao.LocacaoDTO;
import br.com.ledscolatina.backend.repository.ClienteRepository;
import br.com.ledscolatina.backend.repository.ItemRepository;
import br.com.ledscolatina.backend.repository.LocacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LocacaoDTO create(Locacao locacao) {
        Cliente cliente = clienteRepository.findById(locacao.getCliente().getId()).get();

        if (cliente.getAtivo() != null && !cliente.getAtivo()) {
            throw new ClienteNotActiveException(cliente.getId());
        }

        Item item = itemRepository.findById(locacao.getItem().getId()).get();
        if (null != item.getLocacao()) {
            Locacao locacaoItem = locacaoRepository.findById(item.getLocacao().getId()).get();
            String message = "Este Item já está Locado e está previsto para devolução" +
                    locacaoItem.getData_devolucao_prevista().getDayOfMonth() + "de" +
                    locacaoItem.getData_devolucao_prevista().getMonth();
            throw new ItemLocadoException(message);
        }

        locacao.setData_locacao(LocalDateTime.now());
        locacao = modelMapper.map(locacaoRepository.save(locacao), Locacao.class);
        item.setLocacao(locacao);
        itemRepository.save(item);
        return modelMapper.map(locacao, LocacaoDTO.class);
    }

    public List<LocacaoDTO> index() {
        return locacaoRepository.findAll().stream()
                .map(locacao -> modelMapper.map(locacao, LocacaoDTO.class))
                .collect(Collectors.toList());
    }

    public LocacaoDTO show(Long id) {
        return locacaoRepository.findById(id)
                .map(record -> modelMapper.map(record, LocacaoDTO.class))
                .orElseThrow(() -> new LocacaoNotFoundException(id));
    }

    public LocacaoDTO update(Locacao locacao) {
        return locacaoRepository.findById(locacao.getId())
                .map(record -> {
                    record.setCliente(locacao.getCliente());
                    record.setItem(locacao.getItem());
                    record.setValor(locacao.getValor());
                    record.setMulta(locacao.getMulta());
                    record.setData_devolucao_prevista(locacao.getData_devolucao_prevista());
                    record.setUpdatedAt(locacao.getUpdatedAt());
                    return modelMapper.map(locacaoRepository.save(record), LocacaoDTO.class);
                }).orElseThrow(() -> new LocacaoNotFoundException(locacao.getId()));
    }

    public void delete(Long id) {
        if (locacaoRepository.findById(id).isPresent()) {
            locacaoRepository.deleteById(id);
        }
        else {
            throw new LocacaoNotFoundException(id);
        }
    }

}
