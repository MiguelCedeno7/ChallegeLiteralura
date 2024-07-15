package com.aluracursos.literatura.Principal;

import com.aluracursos.literatura.Menu.MenuPrinc;
import com.aluracursos.literatura.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Principal {
    private final MenuService servicioMenu;

    @Autowired
    public Principal(MenuService servicioMenu) {
        this.servicioMenu = servicioMenu;
    }

    public void EjecutarAplicacion() {
        Menu menu = new Menu();
        Scanner teclado = new Scanner(System.in);
        System.out.println(MenuPrinc.getBienvenida());
        int opcion;
        do {
            try {
                System.out.print(MenuPrinc.getMenu() + " ");
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        servicioMenu.guardarLibro();
                        break;
                    case 2:
                        servicioMenu.listarLibrosRegistrados();
                        break;
                    case 3:
                        servicioMenu.listarAutoresRegistrados();
                        break;
                    case 4:
                        servicioMenu.listarAutoresVivosEnAnio();
                        break;
                    case 5:
                        servicioMenu.listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saliendo de literAlura...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un número");
                opcion = -1;
                teclado.nextLine();
            }
        } while (opcion != 0);
        teclado.close();
    }
}
