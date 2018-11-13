/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Javier Fernández Ferrol
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> primos = new ArrayList<>();
        primos.addAll(Arrays.asList(2, 3, 5)); //Primos básicos para empezar
        int numero = 564654654;
        int contador = primos.get(primos.size() -1);
        while (numero > Math.pow(primos.get(primos.size() -1) +2 ,2)-1){ //Siempre q numero sea mayor que el último primo + 2 al cuadrado - 1
            contador = contador + 2; //Sumando 2 evito los números pares
            String contTexto = String.valueOf(contador);
            if (contTexto.charAt(contTexto.length()-1) == '5'){ //Evito los múltiplos de 5 sin realizar operaciones matemáticas
                contador = contador + 2; 
            }
            boolean esPrimo = true;
            for (int primo: primos){
                if (contador % primo == 0){
                    esPrimo = false;
                    //System.out.println(contador + " es divisible entre: " + primo);
                    break;
                }
            }
            if (esPrimo){
                System.out.println(contador + " es primo!!");
                primos.add(contador);
            }
        }
    }
}
