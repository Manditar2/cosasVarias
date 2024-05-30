/* 
Se necesita estudiar un sistema de distribución alternativo, que podría resultar más adecuado para el manejo de pedidos grandes: en vez de acotar la cantidad
de pedidos que puede llevar un repartidor, limitar su peso total.

Se considera que la cantidad máxima que puede llevar un repartidor corresponde a 30kg. El peso de cada pedido es evaluado con precisión de 0,1kg. Un pedido es
considerado grande si su peso es estrictamente superior a 10kg.

Se necesita un algoritmo que permita calcular la cantidad mínima de viajes necesarios para repartir una serie de pedidos. Se le entregará un arreglo con la
información del peso de cada uno de los pedidos. Para evitar problemas de redondeo, los valores corresponderán a la cantidad en kg multiplicada por 10: por
ejemplo, el peso de 10,1kg será representado como 101. En otras palabras, se representará los pesos en múltiplos de 100g.

Su algoritmo necesita hacerse cargo sólo de los escenarios en que la cantidad de pedidos es entre 1 y 10000 (sí, se considera casos de atender a muchos
clientes, como en servicios de catering a grandes ferias internacionales, etc.), y el peso queda entre 10,1kg y 30kg, inclusive.

Su implementación debe leer los datos de entrada desde stdin y escribir la respuesta en stdout. El stdin contendrá una línea de números enteros separados por
espacios, los pesos de los pedidos expresados en múltiplos de 100g. El stdout debe contener una línea con un número entero, la cantidad mínima de repartidores
necesarios para entregar todos los pedidos.

Ejemplos

0) Entrada: 150 150 150 150 150
Devuelve: 3
Tiene cinco pedidos y cada repartidor puede llevar como máximo dos de ellos. Necesita al menos tres repartidores.

1) Entrada: 130 140 150 160
Devuelve: 2
Por ejemplo, puede distribuir los pedidos de la siguiente manera:
Repartidor 1: 130, 150
Repartidor 2: 140, 160

2) Entrada: 101 101 101 101 101 101 101 101 101
Devuelve: 5

3) Entrada: 101 200 101 101 101 101 200 101 200
Devuelve: 6

4) Entrada: 123, 145, 167, 213, 245, 267, 289, 132, 154, 176, 198
Devuelve: 8

*/
import java.util.Arrays;
public class Repartidor {
    public static void main(String[] args){
        Integer[] test = {123, 145, 167, 213, 245, 267, 289, 132, 154, 176, 198};
        int a = solution(test);
        System.out.println(a);
    }

    public static int solution(Integer[] pesos){
        Arrays.sort(pesos);
        int start = 0;
        int end = pesos.length - 1;
        int residuo = 0;
        int limite = 300;

        while (start <= end){
            if (start == end){
                residuo++;
                break;
            }
            else if (pesos[start] + pesos[end] > limite){
                residuo += 1;
                end--;
            }
            else {
                residuo++;
                end--;
                start++;
            }
        }
        return residuo;
    }
}
