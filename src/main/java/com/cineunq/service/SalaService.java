package com.cineunq.service;

import com.cineunq.controllers.dto.request.SalaAsientosRequest;
import com.cineunq.controllers.dto.request.SalaFilaRequest;
import com.cineunq.dao.InfoTipoSalaRepository;
import com.cineunq.dao.SalaRepository;
import com.cineunq.dominio.FormaSala;
import com.cineunq.dominio.InfoTipoSala;
import com.cineunq.dominio.Sala;
import com.cineunq.dominio.enums.TipoSala;
import com.cineunq.exceptions.NotFoundException;
import com.cineunq.service.interfaces.ISalaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalaService implements ISalaService {

    @Autowired
    private SalaRepository repository;

    @Autowired
    private InfoTipoSalaService infoTipoSalaService;

    @Override
    public Sala saveSala(Sala s) {
        return repository.save(s);
    }

    @Override
    public Sala findById(Long idSala){
        Optional<Sala> sala = repository.findById(idSala);
        if(sala.isPresent()){
            return sala.get();
        }
        throw new NotFoundException("Compra : No se a encontrado la Sala solicitada");
    }

    public Sala findSalaByFuncionId(Long idFuncion){
        return repository.salaPorIdFuncion(idFuncion);
    }

    @Transactional
    public Sala testeoSala(SalaAsientosRequest sala){
        InfoTipoSala infoTipoSala = infoTipoSalaService.getByTipoSala(sala.getTipoSala());
        List<FormaSala> result = new ArrayList<>();
        for (SalaFilaRequest s : sala.getSeats()){
            result.add(FormaSala.builder().fila(s.getFila()).columnas(s.getColumna()).build());
        }
        return repository.save(Sala.builder().nombreSala(sala.getName()).tipoSala(infoTipoSala).forma(result).columnas(String.valueOf(sala.getColumns())).cantFilas(sala.getRows()).build());
    }

    public Map<String,List<Sala>> salasPorFormatoSala() {
        List<Sala> salas = repository.salasPorInfoTipoSala();
        Map<String,List<Sala>> salasOrdenadas = new HashMap<>();
        for (Sala s: salas) {
            String nameTipoSala = s.getTipoSala().getTipoSala().nombre();
            if(salasOrdenadas.get(nameTipoSala) != null){
                salasOrdenadas.get(nameTipoSala).add(s);
            }else{
                List<Sala> element = new ArrayList<>();
                element.add(s);
                salasOrdenadas.put(nameTipoSala,element);
            }
        }
        return salasOrdenadas;
    }

}
