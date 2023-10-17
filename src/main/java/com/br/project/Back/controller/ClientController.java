package com.br.project.Back.controller;

import com.br.project.Back.model.Client;
import com.br.project.Back.model.dto.ClientDTO;
import com.br.project.Back.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/listpage")
    public Page<Client> getByCriteria(@RequestParam String value, @RequestParam Integer page,
                                      @RequestParam Integer pageSize, @RequestParam String sort,
                                      @RequestParam String order) {
        return clientService.findByCriteria(value, page, pageSize, sort, order);
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id){
        return clientService.getById(id);
    }

    @PostMapping(path = "/save", consumes = "application/json; charset=utf-8")
    public ClientDTO saveClient(@RequestBody ClientDTO dto){
        return clientService.saveUpdate(dto);
    }

    @PutMapping(path = "/update", consumes = "application/json; charset=utf-8")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO){
        return clientService.saveUpdate(clientDTO);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

}
