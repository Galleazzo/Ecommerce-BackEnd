package com.br.project.Back.service;

import com.br.project.Back.model.Client;
import com.br.project.Back.model.dto.ClientDTO;
import com.br.project.Back.repository.ClientRepository;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Page<Client> findByCriteria(String value, Integer page, Integer pageSize, String sort, String order) {
        Pageable pageRequest = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sort);
        return clientRepository.search(value, pageRequest);
    }

    public ClientDTO getById(Long id){
        ClientDTO clientDTO = new ClientDTO();
        Client client = clientRepository.getById(id);

        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setCpf(client.getCpf());
        clientDTO.setLastAccess(client.getLastAccess());
        clientDTO.setCreationDate(client.getCreationDate());
        clientDTO.setState(client.getState());
        clientDTO.setCity(client.getCity());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setCep(client.getCep());

        return clientDTO;
    }

    @Transactional
    public ClientDTO saveUpdate(ClientDTO dto){
        LocalDateTime date = LocalDateTime.now();
        Client client = new Client();
        client.setCreationDate(date);
        if(dto.getId() != null) {
            client = clientRepository.getById(dto.getId());
        }
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setCpf(dto.getCpf());
        client.setLastAccess(date);
        client.setState(dto.getState());
        client.setCity(dto.getCity());
        client.setAddress(dto.getAddress());
        client.setCep(dto.getCep());

        clientRepository.save(client);
        return dto;
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

}
