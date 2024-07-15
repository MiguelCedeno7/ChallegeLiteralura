package com.aluracursos.literatura.Service;


import com.aluracursos.literatura.SolicitudAPI.SolicitarAPI;
import com.aluracursos.literatura.model.Autor;
import com.aluracursos.literatura.model.Idioma;
import com.aluracursos.literatura.model.Libro;
import com.aluracursos.literatura.model.RecordLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {
    private SolicitarAPI solicitarAPI;
    private Scanner scanner;
    private ServicioLibro servicioLibro;
    private ServicioAutor servicioAutor;
    private JsonParser jsonParser;

    @Autowired
    public MenuService(ServicioLibro servicioLibro, ServicioAutor servicioAutor, JsonParser jsonParser) {
        this.solicitarAPI = new SolicitarAPI();
        this.scanner = new Scanner(System.in);
        this.servicioLibro = servicioLibro;
        this.servicioAutor = servicioAutor;
        this.jsonParser = jsonParser;
    }

    public void guardarLibro() {
        List<RecordLibro> librosObtenidos = obtenerLibrosApi();

        if (librosObtenidos.isEmpty()) {
            System.out.println("No se encontró ningun libro");
            return;
        }

        System.out.println("Escoja un libro para guardar[0-Cancelar]");
        for (int i = 0; i < librosObtenidos.size(); i++) {
            System.out.println((i + 1) + " - " + librosObtenidos.get(i).titulo() + " - " + librosObtenidos.get(i).idiomas().get(0) + " - " + librosObtenidos.get(i).autores().get(0).nombre());
        }

        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) {
            return;
        }
        if (opcion < 1 || opcion > librosObtenidos.size()) {
            System.out.println("Error: número erroneo");
            return;
        }

        RecordLibro libroRecord = librosObtenidos.get(opcion - 1);
        Optional<Libro> libroTraidoDelRepo = servicioLibro.obtenerLibroPorNombre(libroRecord.titulo());
        Optional<Autor> autorTraidodelRepo = servicioAutor.obtenerAutorPorNombre(libroRecord.autores().get(0).nombre());

        if (libroTraidoDelRepo.isPresent()) {
            System.out.println("Error: no se puede registrar dos veces el mismo libro");
            return;
        }

        Libro libro = new Libro(recordLibro);

        if (!autorTraidodelRepo.isPresent()) {
            Autor autorNuevo = libro.getAutor();
            servicioAutor.guardarAutor(autorNuevo);
        }

        servicioLibro.guardarLibro(libro);
    }

    public List<RecordLibro> obtenerLibrosApi() {
        System.out.print("Ingrese el título del libro [0-Cancelar]: ");
        String titulo = scanner.nextLine();
        if (titulo.equals("0")) {
            return Collections.emptyList();
        }
        List<RecordLibro> librosObtenidos;
        librosObtenidos = jsonParser.parsearLibro(solicitarAPI.obtenerDatos(titulo));
        return librosObtenidos;
    }


    public void listarLibrosRegistrados() {
        List<Libro> libros = servicioLibro.obtenerTodosLosLibros();
        libros.forEach(libro -> libro.imprimirInformacion());
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = servicioAutor.obtenerTodosLosAutores();
        autores.forEach(autor -> autor.imprimirInformacion());
    }

    public void listarAutoresVivosEnAnio() {
        try {
            System.out.print("Ingrese año: ");
            int anio = scanner.nextInt();
            scanner.nextLine();
            List<Autor> autores = servicioAutor.obtenerAutoresVivosEnAnio(anio);
            autores.forEach(autor -> autor.imprimirInformacion());
        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número");
        }

    }

    public void listarLibrosPorIdioma() {
        Idioma.listarIdiomas();
        System.out.print("Ingrese el codigo del idioma [0-Cancelar]: ");
        String idiomaBuscado = scanner.nextLine();
        if (idiomaBuscado.equals("0")) {
            return;
        }
        List<Libro> libros = servicioLibro.obtenerLibrosPorIdioma(Idioma.stringToEnum(idiomaBuscado));
        libros.forEach(libro -> libro.imprimirInformacion());
    }

}

