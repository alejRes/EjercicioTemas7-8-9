package com.alejrest;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

/*
La funcionalidad del programa va a ser un pequeño menú que premita al usuario
poder generar una lista de la compra de una forma organizada y que le genere un documento
con dicha lista de la compra.
 */
public class EjercicioLibre {
    private static final String TABULACION = "\t";
    private static final String SALTO_LINEA = "\n";

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> listasProductos = new HashMap<>();
        Scanner scannerMain;
        int opcion = -1;
        do {
            scannerMain = new Scanner(System.in);
            imprimeMenuPrincipal();
            System.out.println("Seleccione una opción");
            try {
                opcion = scannerMain.nextInt();
                accionOpcionMenu(opcion, listasProductos);
            } catch (InputMismatchException ex) {
                System.out.println("Introduce un numero de opción del menú");
            } catch (IOException e) {
                System.out.println("problemas en el fichero " + e.getMessage());
            }

        } while (opcion != 0);
        scannerMain.close();
    }

    public static void imprimeMenuPrincipal() {
        System.out.println("1 - Crear lista de la compra");
        System.out.println("2 - Ver todos los productos");
        System.out.println("3 - Eliminar producto de la lista");
        System.out.println("4 - Guardar la lista en documento");
        System.out.println("0 - Salir del programa");

    }

    public static void accionOpcionMenu(int opcion, HashMap<String, ArrayList<String>> listas) throws IOException {
        switch (opcion) {
            case 1:
                introducirProductosLista(listas);
                break;
            case 2:
                verProductosLista(listas);
                break;
            case 3:
                eliminarProductolista(listas);
                break;
            case 4:
                guardarListaEnDocumento(listas);
                break;
        }
    }

    private static void introducirProductosLista(HashMap<String, ArrayList<String>> listas) {
        System.out.println("Introduzca un nombre para la seccion de la lista:");
        Scanner scanner = new Scanner(System.in);
        String nombrelista = scanner.next();
        String producto;
        ArrayList<String> productosDeLaSeccion;
        if (!listas.containsKey(nombrelista)) {
            productosDeLaSeccion = new ArrayList<>();
        } else {
            productosDeLaSeccion = listas.get(nombrelista);
        }

        do {
            System.out.println("Introduce un producto para la seccion " + nombrelista.toUpperCase() + "o 0(cero) para salir");
            producto = scanner.next();
            if (!productosDeLaSeccion.contains(producto) && !producto.equals("0"))
                productosDeLaSeccion.add(producto);
            else if (producto.equals("0"))
                System.out.println();
            else
                System.out.println("La lista ya contiene ese producto");
        } while (!producto.equals("0"));
        listas.put(nombrelista, productosDeLaSeccion);
    }

    private static void guardarListaEnDocumento(HashMap<String, ArrayList<String>> listas) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (listas.isEmpty()) {
            System.out.println("No hay listas creadas");
        } else {
            System.out.println("Introduce nombre del fichero para guardar");
            String fichero = scanner.next();
            PrintStream out = new PrintStream(fichero);
            for (String lista : listas.keySet()) {
                out.write(lista.getBytes(StandardCharsets.UTF_8));
                out.write(SALTO_LINEA.getBytes(StandardCharsets.UTF_8));
                for (String producto : listas.get(lista)) {
                    out.write(TABULACION.getBytes(StandardCharsets.UTF_8));
                    out.write(producto.getBytes(StandardCharsets.UTF_8));
                    out.write(SALTO_LINEA.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
    }

    private static void eliminarProductolista(HashMap<String, ArrayList<String>> listas) {
        if (listas.isEmpty()) {
            System.out.println("No hay listas creadas");
        } else {
            System.out.println("Introduce la sección del producto a eliminar");
            Scanner scanner = new Scanner(System.in);
            String seccion = scanner.next();

            ArrayList<String> listaDeSeccion;
            if (listas.containsKey(seccion)) {
                listaDeSeccion = listas.get(seccion);
                System.out.println("Introduce el producto a eliminar");
                String producto = scanner.next();
                if (listaDeSeccion.contains(producto)) {
                    listaDeSeccion.remove(producto);
                }else{
                    System.out.println("El producto a borrar no existe");
                }
            } else
                System.out.println("No existe la seccion");
        }
    }

    private static void verProductosLista(HashMap<String, ArrayList<String>> listas) {
        if (listas.isEmpty()) {
            System.out.println("No hay listas creadas");
        } else {
            for (String lista : listas.keySet()) {
                System.out.println("Sección: " + lista.toUpperCase());
                for (String producto : listas.get(lista)) {
                    System.out.println("\t" + producto);
                }
            }
        }
    }

}
