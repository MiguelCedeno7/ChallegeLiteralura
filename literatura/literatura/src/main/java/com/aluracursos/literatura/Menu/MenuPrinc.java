package com.aluracursos.literatura.Menu;

public class MenuPrinc {
    private static String menu = """
    1-Buscar libro por título
    2-Listar libros registrados
    3-Listar autores registrados
    4-Listar autores vivos en un determinado año
    5-Listar libros por idioma
    0-Salir
    
    Elija una opción: """;
    private static String bienvenida = "Bienvenidos a literAlura";

    public static String getMenu() {
        return menu;
    }

    public static String getBienvenida() {
        return bienvenida;
    }
}
