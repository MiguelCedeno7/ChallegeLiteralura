package com.aluracursos.literatura.Service;

import com.aluracursos.literatura.Repositorio.RepositorioAutor;
import com.aluracursos.literatura.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServicioAutor {
    public RepositorioAutor autorRepository;

    @Autowired
    public ServicioAutor(RepositorioAutor autorRepository) {
        this.autorRepository = autorRepository;
    }

    public ServicioAutor(){};

    public Optional<Autor> obtenerAutorPorNombre(String nombre){
        return autorRepository.obtenerAutorPorNombre(nombre);
    }

    public void guardarAutor(Autor autor){
        autorRepository.save(autor);
    }

    public List<Autor> obtenerTodosLosAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio){
        return autorRepository.obtenerAutoresVivosEnAnio(anio);
    }

}
