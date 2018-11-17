/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioTarea04;

import java.util.Scanner;

/**
 *
 * @author Javier Fernández Ferrol
 */
public class Supuesto4 {
    
    /**
     *
     * @param args
     */
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el nombre: ");
        String nombre = teclado.next();
        if (!nombre.matches("[A-Za-zÁÉÍÓÚñáéíóúÑ]+")){ //Compruebo si el texto introducido es un número válido
            System.out.println("¡¡" + nombre + " no es nombre válido!");
            System.exit(0);
        }
        System.out.print("Introduce la edad: ");
        String ed = teclado.next();
        if (!ed.matches("\\d+")){
            System.out.println("¡¡" + ed + " no es un formato de edad válido!");
            System.exit(0);
        }
        short edad = Short.parseShort(ed);
        if (edad > 130){
            System.out.println("¡¡" + edad + " es una edad demasiado alta!");
            System.exit(0);
        }
        char categoria = '0';
        if (edad ==0){
            System.out.println("¡¡" + edad + " no es una edad válida!");
        }else if (edad <= 25){
            categoria = 'A';
        }else if (edad <= 50){
            categoria = 'B';
        }else{
            categoria = 'C';
        }
        System.out.println(nombre + " con " + edad + " años pertenecea a la categoría " + categoria);
    
    }
    
}
