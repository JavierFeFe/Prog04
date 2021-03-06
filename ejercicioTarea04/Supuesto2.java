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
public class Supuesto2 {
    /**
     *
     * @param args
     */
    private static final int DIGITOS = 7;
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce un año: ");
        String an = teclado.next();
        int[] meses = {31,28,31,30,31,30,31,31,30,31,30,31}; //Array con los días de los meses ordenados
        String[] nombre = 
        {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"}; //Array con los nombres de los meses
        boolean bisiesto = false;
        int anho = 0;
        if (!an.matches("\\d+") || an.length() > DIGITOS){  //Compruebo si el texto introducido es año con un máximo de 7 dígitos
            System.out.println("¡¡" + an + " no es un año válido!!");
            System.exit(0);
        }else{
            anho = Integer.parseInt(an);
            if (anho % 4 ==0){ //Si el año es divible entre 4 puede ser bisiesto
                if (anho % 100 != 0){ //Si es divisible entre 4 y no lo es entre 100 es bisiesto
                    bisiesto = true;
                }else if (anho % 400 == 0){ // Si es divisible entre 4, 100 y 400 es bisiesto
                    bisiesto = true;
                }
            }
        }
        int mes  = -1;
        System.out.print("Introduce un mes: ");
        String me = teclado.next();
        if (me.matches("\\d+") && Integer.parseInt(me) >0 && Integer.parseInt(me) < 13 )
            mes = Integer.parseInt(me) - 1;
        else{
            for (int i=0; i < nombre.length; i++){ //Me parece más simple que mediante un switch case
               if (nombre[i].equals(me.toLowerCase()))
                   mes = i;
            }
        }
        if (mes == -1){
            System.out.println("¡¡" + me + " no es un formato de mes válido!!");
            System.exit(0);
        }     
        String b = bisiesto?"bisiesto ":"";
        System.out.print("El mes de " + nombre[mes] + " del año " + b + anho + " tiene ");
        if (!bisiesto || mes !=1)
            System.out.println(meses[mes] + " días.");
        else
            System.out.println((meses[mes] + 1) + " días.");
    }
}

