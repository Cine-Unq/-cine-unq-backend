package com.cineunq.service;

import com.cineunq.dao.InfoTipoSalaRepository;
import com.cineunq.dominio.InfoTipoSala;
import com.cineunq.dominio.enums.TipoSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cineunq.dominio.enums.TipoSala.*;

@Service
public class InfoTipoSalaService{

    @Autowired
    private InfoTipoSalaRepository infoTipoSalaRepository;

    public TipoSala getTipoSala(String name) {
        switch (name) {
            case "2D":
                return DOS_D;
            case "3D":
                return TRES_D;
            case "4D":
                return CUATRO_D;
        }
        return null;
    }


    public InfoTipoSala getByTipoSala(String name){
        return infoTipoSalaRepository.findByTipoSala(getTipoSala(name));
    }

}
