package cl.bibliosmart.bibliosmart.modules.bibliotecario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.PoliticaPrestamo;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.repository.PoliticaPrestamoRepository;

@Service
public class PoliticaPrestamoService {

    @Autowired
    PoliticaPrestamoRepository politicaPrestamoRepository;
    public List<PoliticaPrestamo> obtenerTodas() {
        return politicaPrestamoRepository.findAll();
}


}
