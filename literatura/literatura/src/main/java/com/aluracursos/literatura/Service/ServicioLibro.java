package com.aluracursos.literatura.Service;


import com.aluracursos.literatura.Repositorio.RepositorioLibro;
import com.aluracursos.literatura.model.Idioma;
import com.aluracursos.literatura.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServicioLibro {

    private RepositorioLibro libroRepository;

    @Autowired
    public ServicioLibro(RepositorioLibro repository) {
        this.libroRepository = repository;
    }

    public ServicioLibro(){};

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorNombre(String nombre){
        return libroRepository.obtenerLibroPorNombre(nombre);
    }

    public List<Libro>obtenerLibrosPorIdioma(Idioma idioma){
        return libroRepository.obtenerLibrosPorIdioma(idioma);
    }

    public void guardarLibro(Libro libro){
        libroRepository.save(libro);
    }
}

