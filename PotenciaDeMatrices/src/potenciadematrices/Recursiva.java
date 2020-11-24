package potenciadematrices;

// @author Sonya Castro
public class Recursiva {

    int L;
    int N;
    double matriz[][];
    double potencia[][];

    public Recursiva(int L, int N, double[][] matriz) {
        this.L = L;
        this.N = N;
        this.matriz = matriz;
        this.potencia = matriz;
    }

    public void escribir(double[][] matriz, int f, int c) {
        if (f < N) {
            if (c < N) {
                System.out.print(matriz[f][c] + "\t");
                escribir(matriz, f, c + 1);
            } else {
                System.out.println("");
                escribir(matriz, f + 1, 0);
            }
        }
    }

    public double[][] potencia(int exp) {
        if (exp < L) {
            double[][] C = new double[N][N];

            potencia = producto(matriz, potencia, C, 0, 0, 0, 0);
            potencia(exp + 1);

        }
        return potencia;
    }

    public double[][] producto(double[][] A, double B[][], double C[][], int suma, int f, int c, int k) {
        if (f < N) {
            if (c < N) {
                if (k < N) {
                    suma += A[f][k] * B[k][c];
                    producto(A, B, C, suma, f, c, k + 1);
                } else {
                    C[f][c] = suma;
                    producto(A, B, C, 0, f, c + 1, 0);
                }
            } else {
                producto(A, B, C, 0, f + 1, 0, 0);
            }
        }
        return C;
    }
}
