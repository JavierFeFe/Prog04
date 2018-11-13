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
    private static final List<Long> primos = new ArrayList<>();
    private static List<Long> resultado;
    private static long numero;
    private static final byte longitud = 12; //A partir de 12 el tiempo se hace demasiado largo
    public static void main (String[] args){
        primos.addAll(Arrays.asList(2l, 3l, 5l)); //Primos básicos para empezar       
        resultado = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        numero = -1;
        String in="";
        while (numero == -1){
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
        generaPrimos(numero);
        System.out.print("Factores primos: ");
        while (numero != -1){ //El valor -1 se usará como marcador para finalizar el bucle
            numero = creaFactores(numero); //uso el método división definido más abajo
        }       
        /* 
                La siguiente parte es para generar el texto con exponenciales
        */               
        String texto="";
        long numeroActivo = -1;
        int exponente = 0;
        for (long num: resultado){
            if (numeroActivo == num){
                exponente++;
            }else{
                texto += (numeroActivo > -1)?generaNumero(numeroActivo, exponente) + " * ":"";
                exponente = 1;
            }
            numeroActivo = num;
        }
        texto += generaNumero(numeroActivo, exponente);
        System.out.println(texto);
    }
    private static void generaPrimos(long numero){
        long contador = primos.get(primos.size() -1);
        while (numero > Math.pow(primos.get(primos.size() -1) +2 ,2)-1){ //Siempre q numero sea mayor que el último primo + 2 al cuadrado - 1
            contador = contador + 2; //Sumando 2 evito los números pares
            String contTexto = String.valueOf(contador);
            if (contTexto.charAt(contTexto.length()-1) == '5'){ //Evito los múltiplos de 5 sin realizar operaciones matemáticas
                contador = contador + 2; 
            }
            boolean esPrimo = true;
            for (long primo: primos){
                if (contador % primo == 0){
                    esPrimo = false;
                    //System.out.println(contador + " es divisible entre: " + primo);
                    break;
                }
            }
            if (esPrimo){
                //System.out.println(contador + " es primo!!");
                primos.add(contador);
            }
        }
    }
    private static String generaNumero(long numeroActivo, long exponente){
        String texto = "";
        texto += numeroActivo;
        texto += (exponente>1)?("^" + exponente):"";
        return texto;
    }
    private static long creaFactores(long numero){
        for (long primo : primos){ 
            if (primo >= numero){ //Si el primo es mayor o igual que el número evitamos hacer la división y finalizamos el bucle.
                resultado.add(numero);
                return -1;
            }
            if ((numero % primo) == 0){ //Si el resto de la división es 0 quiere decir que es divisible.
                resultado.add(primo);
                long valor = numero/primo;
                return valor;
            }
        }
        resultado.add(numero);
        return -1;
    } 
}
