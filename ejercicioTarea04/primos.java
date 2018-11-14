/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioTarea04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Javier Fernández Ferrol
 */
public class Supuesto3 {
    private static final List<Long> primos = new ArrayList<>(); //Lista de primos ordenados numéricamente
    private static final List<Long> primosFinales = new ArrayList<>(); //Lista de primos por lo que sabemos q es divisible nuestro número
    private static long numero; //El número con el que trabajaremos
    private static final byte longitud = 12; //A partir de 12 el tiempo de procesamiento se hace demasiado largo en caso de resultar ser un número primo
    public static void main (String[] args){
        primos.addAll(Arrays.asList(2l, 3l, 5l, 7l)); //Primos más básicos para primeras comprobaciones     
        Scanner teclado = new Scanner(System.in);
        numero = -1;
        String in="";
        while (numero == -1){ //Fuerza la introducción de un número válido con una longitud máxima especificada en la variable lontitud
            System.out.print("Introduce el número con un máximo de " + longitud + " dígitos: ");
            in = teclado.next();
            if (!in.matches("\\d*")){
                System.out.println("¡¡" + in + " no es un formato de número válido!!");
            }
            else if (in.length() > longitud){
                System.out.println("¡¡El número es demasiado largo!!");
            }else{
                numero = Long.parseLong(in);
            }
        }
        System.out.println("Generando nºs primos necesarios...");
        generaPrimos(numero); //Se generan los primos por los que es divisible nuestro número   
        /* 
                La siguiente parte es para generar el texto con exponenciales
        */ 
        if (primosFinales.size() == 1){
            System.out.println("El número " + primosFinales.get(0) + " es primo.");
        }else{
            System.out.print("Factores primos: ");
            String texto="";
            long numeroActivo = -1;
            int exponente = 0;
            for (long num: primosFinales){
                if (numeroActivo == num){
                    exponente++;
                }else{
                    texto += (numeroActivo > -1)?generaNumero(numeroActivo, exponente) + " * ":"";
                    exponente = 1;
                }
                numeroActivo = num;
            }
            texto += generaNumero(numeroActivo, exponente); //Añado el último número del bucle
            System.out.println(texto); //Muestro el resultado
        }
    }
    private static void generaPrimos(long numero){
        int posicionInicial= primos.size() -1;
        long contador = primos.get(posicionInicial);
        for (long primo: primos){//Este bucle simplifica el nº lo máximo posible intentando factorizar entre 2, 3, 5 y 7 (los primos más básicos)
            while (numero % primo == 0){
                primosFinales.add(primo);
                numero = numero/primo;
            }
        }
        while (numero > Math.pow(primos.get(primos.size() -1) +2 ,2)-1){ //Siempre q numero sea mayor que el último primo + 2 al cuadrado - 1
            contador += 2; //Sumando 2 evito la comprobación de los divisibles entre 2
            String contTexto = String.valueOf(contador);
            while (contTexto.charAt(contTexto.length()-1) == '5'){ //Evito la comprobación de los divisibles entre 5
                contador += 2; 
                contTexto = String.valueOf(contador);
            }
            /* 
                Podría añadir reglas mnemotécnicas para evitar comprobaciones con ciertos nºs, como el 3 o el 7
            */
            boolean esPrimo = true;
            for (long primo: primos){ //Recorro el bucle para saber si es divisible entre los primos identificados
                if (contador % primo == 0){
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo){
                primos.add(contador);
                while (numero % contador == 0){ //Compruebo si nuestro número es divisible entre el nuevo número primo
                    numero = numero/contador; // Simplifico el nº de primos necesarios
                    primosFinales.add(contador); //Añade nuevo primo a primosFinales
                }
            }
        }
        if (numero != 1){ //Si el nº final es !=1 quiere decir q no se pudo factorizar por todos los nºs primos generados, por lo q debe de ser primo
            primosFinales.add(numero);
        }
    }
    private static String generaNumero(long numeroActivo, long exponente){ //Este método crea una cadena de texto que representa un número con exponencial (o sin el)
        String texto = "";
        texto += numeroActivo;
        texto += (exponente>1)?("^" + exponente):"";
        return texto;
    }
}
