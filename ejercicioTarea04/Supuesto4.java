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
        if (ed.length() > 3){
            System.out.println("No es posible que alguien tenga " + ed + " años.");
            System.exit(0);
        }
        short edad = (short)(Short.parseShort(ed) + 10);
        if (edad > 130){
            System.out.println("¡¡" + edad + " es una edad demasiado alta!");
            System.exit(0);
        }
        char categoria = '0';
        if (edad <= 25){
            categoria = 'A';
        }else if (edad <= 50){
            categoria = 'B';
        }else{
            categoria = 'C';
        }
        System.out.println("Dentro de una década " + nombre + " tendrá " + edad + " años y pertenecerá a la categoría " + categoria + ".");
    }  
}
