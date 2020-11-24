package potenciadematrices;

// @author Sonya Castro
public class Iterativa {

    int L;
    int N;
    double matriz[][];
    double potencia[][];

    public Iterativa(int L, int N, double[][] matriz) {
        this.L = L;
        this.N = N;
        this.matriz = matriz;
        this.potencia = matriz;
    }

    public void escribir(double[][] matriz) {
        for (int f = 0; f < N; f++) {
            for (int c = 0; c < N; c++) {
                System.out.print(matriz[f][c]+"\t");
            }
            System.out.println("");
        }
    }

    public double[][] potencia() {
        for (int exp = 1; exp < L; exp++) {
            potencia = producto(matriz, potencia);
        }
        return potencia;
    }

    public double[][] producto(double[][] A, double B[][]) {
        double C[][] = new double[N][N];
        
        for (int f = 0; f < N; f++) {
            for (int c = 0; c < N; c++) {
                int suma = 0;
                for (int k = 0; k < N; k++) {
                    suma += A[f][k]*B[k][c];
                }
                C[f][c]=suma;
            }
        }
        return C;
    }
}
