package potenciadematrices;

// @author Sonya Castro
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class csv {

    FileWriter Fescritor;
    BufferedWriter Bescritor;
    PrintWriter Pescritor;

    public csv(String ruta, String experimento) {

        try {
            Fescritor = new FileWriter(ruta+"/experimento_"+experimento+".csv", true);
            Bescritor = new BufferedWriter(Fescritor);
            Pescritor = new PrintWriter(Bescritor);
            Pescritor.println("Prueba #, N, Tiempo total, Iterativa, Iterativa %, Recursiva, Recursiva %, Errores");
            System.out.println("Se creo el archivo correctamente ...");
        } catch (IOException e) {
            System.out.println("No se encontro la ruta ...");
        }
    }

    public void escribir(double prueba, double N, double total, double iterativa, double recursiva, double errores) {
        Pescritor.println(prueba + "," + N + "," + total + "," + iterativa + "," + recursiva + "," + errores);
        Pescritor.flush();
    }
    
    public void  terminar () {
        Pescritor.close();
        System.out.println("\nEl archivo CSV se guardo correctamente");
    }

}
