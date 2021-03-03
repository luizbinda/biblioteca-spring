package br.com.ledscolatina.backend.controller;
import br.com.ledscolatina.backend.model.Locacao;
import br.com.ledscolatina.backend.model.dto.Locacao.LocacaoDTO;
import br.com.ledscolatina.backend.service.DevolucaoService;
import br.com.ledscolatina.backend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("devolucao")
public class DevolucaoController {

    @Autowired
    private DevolucaoService devolucaoService;

    @PostMapping
    public ResponseEntity<?> create(@DTO(LocacaoDTO.class) Locacao locacao) {
        return ResponseEntity.ok(devolucaoService.create(locacao));
    }

}
