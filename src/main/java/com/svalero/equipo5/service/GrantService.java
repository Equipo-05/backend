package com.svalero.equipo5.service;

import com.svalero.equipo5.dto.in.GrantModifyInDto;
import com.svalero.equipo5.repository.GrantRepository;
import com.svalero.equipo5.domain.Grant;
import com.svalero.equipo5.dto.in.GrantInDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantService {

    @Autowired
    private GrantRepository grantRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Grant> findAll() {
        return grantRepository.findAll();
    }


    public Grant findGrantById(long id) throws GrantNotFoundException {
        return grantRepository.findById(id)
                .orElseThrow(GrantNotFoundException::new);
    }


    public Grant addGrant(GrantInDto grantInDto) {
        Grant grant = new Grant();
        modelMapper.map(grantInDto, grant);

        return grantRepository.save(grant);
    }

    public Grant modifyGrant(long id, GrantModifyInDto grantModifyInDto) throws GrantNotFoundException {
        Grant existingGrant = grantRepository.findById(id)
                .orElseThrow(GrantNotFoundException::new);

        modelMapper.map(grantModifyInDto, existingGrant);
        existingGrant.setId(id);
        return  grantRepository.save(existingGrant);
    }

    public void deleteGrant(long id) throws GrantNotFoundException {
        Grant grant = grantRepository.findById(id)
                .orElseThrow(GrantNotFoundException::new);
        grantRepository.delete(grant);
    }
}
