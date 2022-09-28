package com.alejrest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class EjerciciosTemas {
    public static void main(String[] args) {
        //cadenaAlReves("Hola Mundo");
        //leerArrayUnidimensional();
        //leerArrayBidimensional();
        //modificacionesDeUnVector();
        //arrayListCopiaLinkedList();
        //pintarImparesArrayList();
//        try{
//            System.out.println("El resultado de la división es: " + dividePorCero(1, 0));
//        }catch (ArithmeticException e){
//            System.out.println("Esto no se puede hacer " + e.getMessage());
//        }finally{
//            System.out.println("Demo de código");
//        }
        //copiaDeFicheros("origen.txt", "destino.txt");
    }



    public static void cadenaAlReves(String cadena) {
        //Solución:
        String cadenaReves = "";

        for (int i = cadena.length(); i > 0; i--) {
            cadenaReves += cadena.charAt(i - 1);
        }
        System.out.println("La palabra recibida es: " + cadena + " y al reves es " + cadenaReves);
    }

    public static void leerArrayUnidimensional() {
        String[] nombres = {"Pepe", "Pepa", "Paco", "Miguel"};

        for (String nombre : nombres) {
            System.out.println("nombre = " + nombre);
        }
    }

    public static void leerArrayBidimensional() {
        int[][] matrizNumeros = new int[2][5];

        //llena la matriz con números aleatorios
        for (int i = 0; i < matrizNumeros.length; i++) {
            for (int j = 0; j < matrizNumeros[i].length; j++) {
                matrizNumeros[i][j] = (int) (Math.random() * 100 + 1);
            }
        }

        //lee la matriz y muestra los numeros
        for (int i = 0; i < matrizNumeros[0].length; i++) {
            System.out.print("\t[" + i + "]");
        }
        for (int i = 0; i < matrizNumeros.length; i++) {
            System.out.println();
            System.out.print("[" + i + "]");
            for (int j = 0; j < matrizNumeros[i].length; j++) {
                System.out.print("\t");
                System.out.print(matrizNumeros[i][j]);

            }
        }
    }

    private static void modificacionesDeUnVector() {
        Vector<Integer> vectorNumeros = new Vector<>();
        vectorNumeros.add(12);
        vectorNumeros.add(34);
        vectorNumeros.add(67);
        vectorNumeros.add(72);
        vectorNumeros.add(2);

        vectorNumeros.remove(1);
        vectorNumeros.remove(2);

        for (Integer numero : vectorNumeros) {
            System.out.println(numero);
        }
    }

    public static void arrayListCopiaLinkedList() {
        ArrayList<String> mascotas = new ArrayList<>();
        mascotas.add("perro");
        mascotas.add("gato");
        mascotas.add("canario");
        mascotas.add("hamster");

        LinkedList<String> linkedMascotas = new LinkedList<>(mascotas);

        for (String mascota : mascotas) {
            System.out.println(mascota);
        }
        for (String mascotaLinked :
                linkedMascotas) {
            System.out.println(mascotaLinked);
        }
    }

    public static void pintarImparesArrayList() {
        ArrayList<Integer> numeros = new ArrayList<>();
        int z = 0;
        while (z < 10) {
            int num = (int) (Math.random() * 100);
            if (!numeros.contains(num)) {
                numeros.add(num);
                z++;
            }
        }
        System.out.println("Lista de números impares");
        for (int i = 0; i < numeros.size(); i++) {
            if (numeros.get(i) % 2 == 0) {
                numeros.remove(numeros.get(i));
                continue;
            }
            System.out.println(numeros.get(i));
        }
    }

    public static int dividePorCero(int num1, int num2) throws ArithmeticException {
        return num1 / num2;
    }
    private static void copiaDeFicheros(String origen, String destino) {
        try (InputStream in = new FileInputStream(origen);
             PrintStream out = new PrintStream(destino);){
            byte[] datos = in.readAllBytes();
            out.write(datos);
            out.write(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Copia realizada");
    }
}
