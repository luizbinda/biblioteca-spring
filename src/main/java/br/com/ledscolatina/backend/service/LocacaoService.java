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
            item.getLocacao().forEach( locacaoItem -> {
                if ( null == locacaoItem.getData_devolucao_efetiva() ) {
                    String message = "Este Item já está Locado e está previsto para devolução " +
                            locacaoItem.getData_devolucao_prevista().getDayOfMonth() + " de " +
                            getMonthTranslated(locacaoItem.getData_devolucao_prevista().getMonthValue());
                    throw new ItemLocadoException(message);
                }
            });
        }

        locacao.setData_locacao(LocalDateTime.now());
        locacao = modelMapper.map(locacaoRepository.save(locacao), Locacao.class);
        List<Locacao> locacoesItem = item.getLocacao();
        locacoesItem.add(locacao);
        item.setLocacao(locacoesItem);
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

    private String getMonthTranslated(Integer mes) {
        switch(mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            default: return "Dezembro";
        }
    }

}
