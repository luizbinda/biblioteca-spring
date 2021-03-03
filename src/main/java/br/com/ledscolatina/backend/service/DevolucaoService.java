package br.com.ledscolatina.backend.service;


import br.com.ledscolatina.backend.model.Item;
import br.com.ledscolatina.backend.model.Locacao;
import br.com.ledscolatina.backend.model.dto.Locacao.LocacaoDTO;
import br.com.ledscolatina.backend.repository.ItemRepository;
import br.com.ledscolatina.backend.repository.LocacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DevolucaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;


    @Autowired
    private ModelMapper modelMapper;

    public LocacaoDTO create(Locacao locacao) {
        locacao.setData_devolucao_efetiva(LocalDateTime.now());
        locacaoRepository.save(locacao);
        locacao.setData_locacao(LocalDateTime.now());
        return modelMapper.map(locacao, LocacaoDTO.class);
    }

}
