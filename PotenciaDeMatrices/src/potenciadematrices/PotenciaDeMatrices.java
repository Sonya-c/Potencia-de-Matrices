package potenciadematrices;

import java.util.Random;
import java.util.Scanner;

// @author Sonya Castro
public class PotenciaDeMatrices {

    public static void main(String[] args) {

        double INICIO, INICIO_recursiva, INICIO_iterativa;
        double FIN, FIN_recursiva, FIN_iterativa; 
        int prueba = 0; // Indicara cual prueba se esta realizando
        
        double DATA[][] = new double[10][6]; // Aqui se guardaran los datos ejecución

        Scanner leer = new Scanner(System.in);
        csv escribirCSV = null; // Procedimientos encargados de escribir datos en un archivo CSV
        
            
        //--------------------------ENTRADA DE DATOS--------------------------//
        System.out.print("Ingrese la ruta (presione ENTER para cancelar): ");
        String ruta = leer.nextLine();

        System.out.print("Ingrese el número del experimento: ");
        String experimento = leer.next();
        if (!ruta.isEmpty()) // Crear el archivo (si el usuario quiere)
            escribirCSV = new csv(ruta, experimento);

        System.out.print("Ingrese el exponente: ");
        int L = leer.nextInt();
        
        //------------------------------PROCESO-------------------------------//
        for (int N = 10; N < 60; N = N + 5) {
            INICIO = System.nanoTime(); // Tiempo inicial de la prueba #
            System.out.println("\nPRUEBA#" + prueba + " N = " + N);
            
            double potencia[][]; // la matriz resultante
            double matriz[][] = generar(N); // Se genera la matriz de forma aleatoria
            int errores_recursiva;


            System.out.println(">>> VERSIÓN ITERATIVA");
            
            Iterativa iterativa = new Iterativa(L, N, matriz);
            iterativa.escribir(matriz);
            
            System.out.println("\nMatriz de " + N + "x" + N + " elevada a la " + L);
            INICIO_iterativa = System.nanoTime(); // Tiempo inicial de la solución Iterativa
            potencia = iterativa.potencia();
            FIN_iterativa = System.nanoTime(); // Tiempo final de la solución Iterativa
            
            iterativa.escribir(potencia);

            
            
            System.out.println("\n>>> VERSIÓN RECURIVA");
            try {
                errores_recursiva = 0;
                
                Recursiva recursiva = new Recursiva(L, N, matriz);
                recursiva.escribir(matriz, 0, 0);

                System.out.println("\nMatriz de " + N + "x" + N + " elevada a la " + L);
                INICIO_recursiva = System.nanoTime(); // Tiempo inicial de la solución Recursiva
                potencia = recursiva.potencia(1);
                FIN_recursiva = System.nanoTime(); // Tiempo final de la solución Recursiva
                
                recursiva.escribir(potencia, 0, 0);
                
            } catch (StackOverflowError e) {
                errores_recursiva = 1;
                System.out.println("ERROR EN LA EJECUCIÓN: " + e);
                FIN_recursiva = 0;
                INICIO_recursiva = 0;
            }

            
            FIN = System.nanoTime();

            // Guardar los datos del experiemento
            DATA[prueba][0] = prueba + 1; // número del experimento
            DATA[prueba][1] = N; // tamaño de la matriz
            DATA[prueba][2] = FIN - INICIO;  // tiempo total de ejecución
            DATA[prueba][3] = FIN_iterativa - INICIO_iterativa;
            DATA[prueba][4] = FIN_recursiva - INICIO_recursiva;
            DATA[prueba][5] = errores_recursiva;
            prueba = prueba + 1;
        }

        //---------------------------- SALIDAS -------------------------------//
        System.out.println("\n#" + "\t" + "N" + "\t" + "Total   " + "\t" + "Iterativa" + "\t" + "Recusiva" + "   " + "\t" + "Errores");
        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 6; c++) {
                System.out.print(DATA[f][c] + "\t");
            }
            System.out.println("");
            if (!ruta.isEmpty()) {
                escribirCSV.escribir(DATA[f][0], DATA[f][1], DATA[f][2], DATA[f][3], DATA[f][4], DATA[f][5]);
            }
        }
        if (!ruta.isEmpty()) {
            escribirCSV.terminar();
        }
    }

    // Este metodo genera de forma aleatoria la matriz
    public static double[][] generar(int N) {
        double matriz[][] = new double[N][N];
        Random random = new Random();
        for (int f = 0; f < N; f++) {
            for (int c = 0; c < N; c++) {
                matriz[f][c] = random.nextInt(10);
            }
        }
        return matriz;
    }

}
