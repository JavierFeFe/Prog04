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
public class Supuesto1 {

    /**
     *
     * @param args
     */
    public static  void main (String[] args){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un número: ");
        String num = teclado.next();
        if (!num.matches("\\d+")) //Compruebo si el texto introducido es un número válido
            System.out.println("¡¡" + num + " no es un formato de número válido!!");
        else{
            if (Long.parseLong(num) % 2 == 0)
                System.out.println(num + " es par.");
            else 
                System.out.println(num + " es impar.");
        }
    }
}
