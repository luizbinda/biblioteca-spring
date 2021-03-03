package br.com.ledscolatina.backend.service;

import br.com.ledscolatina.backend.except.custom.ItemNotFoundException;
import br.com.ledscolatina.backend.model.Item;
import br.com.ledscolatina.backend.model.dto.Item.ItemDTO;
import br.com.ledscolatina.backend.model.dto.Item.ItemLocacaoDTO;
import br.com.ledscolatina.backend.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ItemDTO create(Item item) {
        return modelMapper.map(itemRepository.save(item), ItemDTO.class);
    }

    public List<ItemDTO> index() {
        return itemRepository.findAll().stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public ItemDTO show(Long id) {
        return itemRepository.findById(id)
                .map(record -> modelMapper.map(record, ItemDTO.class))
                .orElseThrow(ItemNotFoundException::new);
    }

    public ItemLocacaoDTO show(String numSerie) {
        Item item =  itemRepository.findByNumSerie(numSerie);
        if (item == null ) {
            throw new ItemNotFoundException();
        }

        return modelMapper.map(item, ItemLocacaoDTO.class);
    }

    public ItemDTO update(Item item) {
        return itemRepository.findById(item.getId())
                .map(record -> {
                    record.setNumSerie(item.getNumSerie());
                    record.setTitulo(item.getTitulo());
                    record.setData_aquisicao(item.getData_aquisicao());
                    record.setTipo(item.getTipo());
                    record.setUpdatedAt(item.getUpdatedAt());
                    return modelMapper.map(itemRepository.save(record), ItemDTO.class);
                }).orElseThrow(ItemNotFoundException::new);
    }

    public void delete(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
        }
        else {
            throw new ItemNotFoundException();
        }
    }

}
