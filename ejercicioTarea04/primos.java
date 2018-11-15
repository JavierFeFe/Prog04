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
    //private static final List<Long> PRIMOS = new ArrayList<>(); //Lista de primos ordenados numéricamente
    //private static final List<Long> FACTORES = new ArrayList<>(); //Lista de factores de nuestro número
    private static long numero = -1; //El número con el que trabajaremos
    private static final byte LONGITUD = 12; //A partir de 12 el tiempo de procesamiento se hace demasiado largo en caso de resultar ser un número primo
    public static void main (String[] args){           
        Scanner teclado = new Scanner(System.in);
        while (numero == -1){ //Fuerza la introducción de un número válido con una longitud máxima especificada en la variable lontitud
            System.out.print("Introduce el número con un máximo de " + LONGITUD + " dígitos: ");
            String in = teclado.next();
            if (!in.matches("\\d+")) System.out.println("¡¡" + in + " no es un formato de número válido!!");
            else if (in.length() > LONGITUD) System.out.println("¡¡El número es demasiado largo!!");
            else numero = Long.parseLong(in);
        }
        System.out.println("Generando nºs primos necesarios...");
        List<Long> factores = generaFactores(numero); //Se generan los primos necesarios y se factoriza el número  
        /* 
                La siguiente parte es para generar el texto con exponenciales
        */ 
        if (factores.size() == 1){
            System.out.println("El número " + factores.get(0) + " es primo.");
        }else{
            System.out.print("Factores primos: ");
            String resultado=""; //Aquí almaceno la cadena de texto con los factores y sus potencias
            long numeroActivo = -1l;
            int exponente = 0;
            for (long num: factores){
                if (numeroActivo == num) exponente++;
                else{
                    resultado += (numeroActivo > -1)?generaNumero(numeroActivo, exponente) + " * ":"";
                    exponente = 1;
                }
                numeroActivo = num;
            }
            resultado += generaNumero(numeroActivo, exponente); //Añado el último número del bucle
            System.out.println(resultado); //Muestro el resultado
        }
    }
    private static List<Long> generaFactores(long numero){
        List<Long> primos = new ArrayList<>();
        List<Long> factores = new ArrayList<>();
        primos.addAll(Arrays.asList(2l, 3l, 5l, 7l)); //Primos más básicos para primeras comprobaciones  
        long ultimoPrimo = primos.get(primos.size() -1);
        for (long primo: primos){//Este bucle simplifica el nº lo máximo posible intentando factorizar entre 2, 3, 5 y 7 (los primos más básicos)
            while (numero % primo == 0){
                factores.add(primo);
                numero /= primo;
            }
        }
        primos.remove(0); //Evito el 2 (regla del 2)
        primos.remove(2); //Evito el 5 (regla del 5)
        while (numero > Math.pow(primos.get(primos.size() -1) +2 ,2)-1){ //Siempre q numero sea mayor que el último primo + 2 (para evitar par) al cuadrado - 1 (porque no sabemos si ese número será primo)
            //Regla del 2 (Sumando 2 evito la comprobación de los divisibles entre 2 (números pares))
            ultimoPrimo += 2; //Aplico regla del 2
            //Regla del 5 (Si el nº acaba en 5 es múltiplo de 5 (el 0 ya es par))
            String contTexto = String.valueOf(ultimoPrimo);
            while (contTexto.charAt(contTexto.length()-1) == '5'){
                ultimoPrimo += 2; //Aplico regla del 2
                contTexto = String.valueOf(ultimoPrimo);
            }
            /*
                Podría añadir más reglas mnemotécnicas para evitar comprobaciones con ciertos nºs, como el 3 o el 7 
                pero tengo mis dudas de que esto mejore los tiempos de procesamiento (al menos en los rangos q estamos manejando).
            */
            boolean esPrimo = true;
            for (long primo: primos){ //Recorro el bucle para saber si es divisible entre los primos identificados
                if (ultimoPrimo % primo == 0){
                    esPrimo = false;
                    break; //Ya sabemos que no es primo así q no es necesario continuar el bucle
                }
            }
            if (esPrimo){ //Si el bucle anterior no encuentra divisores quiere decir que es primo
                primos.add(ultimoPrimo);
                while (numero % ultimoPrimo == 0){ //Compruebo si nuestro número es divisible entre el nuevo número primo
                    numero /= ultimoPrimo; // Simplifico el nº de primos necesarios por recorrer
                    factores.add(ultimoPrimo); //Añade nuevo primo a primosFinales
                }
            }
        }
        if (numero != 1) factores.add(numero); //Si el nº final es desigual 1 quiere decir q no se pudo factorizar por todos los nºs primos generados, por lo q debe de ser primo
        System.out.println("Total de números primos generados: " + (primos.size() + 2));
        System.out.println("Último número primo: " +  primos.get(primos.size() - 1));
        return factores;
    }
    private static String generaNumero(long numeroActivo, long exponente){ //Este método crea una cadena de texto que representa un número con exponencial (o sin el)
        String texto = "";
        texto += numeroActivo;
        texto += (exponente>1)?("^" + exponente):"";
        return texto;
    }
}
