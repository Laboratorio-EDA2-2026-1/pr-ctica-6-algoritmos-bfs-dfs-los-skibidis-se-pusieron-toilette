
import java.util.ArrayList;
import java.util.Scanner;

public class p6_EDA2 {
    
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n=1;
                
        if (args.length == 0) {

            System.out.print("* Ingrese el numero de reinas: ");
            n = s.nextInt();        
            if (n <= 0) n = Math.abs(n);

        } else {
            
            try {
                n = Integer.parseInt(args[0]);

            } catch (NumberFormatException nfe) {
                System.err.println("* Argumentos de compilacion invalidos!");
                System.err.println("  Tiene que ser un numero!");
                s.close();
                return;

            } catch (Exception e) {
                System.err.println("* Error en argumentos de compilacion!");
                s.close();
                return;
            }
        }

        Tab tab = new Tab(n);
        
        ArrayList<ArrayList<String>> soluciones = tab.solucionesFinales(n);
        
        System.out.println("* Soluciones encontradas para " + n + " reinas: " + soluciones.size());
        
        for (int i = 0; i < soluciones.size(); i++) {

            System.out.println("\nSolucion " + (i+1) );
            tab.displayArr(soluciones.get(i));
            Tab.display(soluciones.get(i));
            
        }
        System.out.println();
        
        s.close();
    }

}

class Tab {

    int[] tablero;
    int n;
    
    public ArrayList<ArrayList<String>> solucionesFinales(int n) {
        
        ArrayList<ArrayList<String>> soluciones = new ArrayList<>();
        llenarDFS(0, soluciones, n);
        
        return soluciones;
    }

    Tab(int n) {
        this.n = n;
        this.tablero = new int[n];
    }

    void llenarDFS(int fila, ArrayList<ArrayList<String>> soluciones, int n) {
        
        if (fila == n) {
            soluciones.add(hacerTab(this.tablero));
        }

        for (int col_actual = 0; col_actual < this.n; col_actual++) {

            if (esSeguro(fila, col_actual)) {

                this.tablero[fila] = col_actual; 
                llenarDFS(fila + 1, soluciones, n);  
            }
        }
    }
    
    boolean esSeguro(int fila, int colm) {
        
        for (int i = 0; i < fila; i++) {
            
            // Checa filas y columnas
            if (this.tablero[i] == colm) {
                return false;
            }
            // Checa diagonales
            if (Math.abs(this.tablero[i] - colm) == Math.abs(i - fila)) {
                return false;
            }
        }
        return true;
    }
    
    private ArrayList<String> hacerTab(int[] tablero) {

        ArrayList<String> tableroNuevo = new ArrayList<>();

        for (int col_actual = 0; col_actual < this.n; col_actual++) {

            StringBuilder nuevaFila = new StringBuilder();

            for (int fil_actual = 0; fil_actual < this.n; fil_actual++) {

                if (tablero[col_actual] == fil_actual) {
                    nuevaFila.append("R");

                } else {
                    nuevaFila.append(".");
                }
            }
            tableroNuevo.add(nuevaFila.toString());
        }
        return tableroNuevo;
    }
    
    static void display(ArrayList<String> tablero) {

        for (String fila : tablero) {

            for (char c : fila.toCharArray()) System.out.print(c + " ");
            System.out.println();
        }
    }

    void displayArr(ArrayList<String> tablero) {

        int[] arr = new int[n];
        char[] fila;
      
        for (int i=0; i<this.n; i++) {

            fila = tablero.get(i).toCharArray();

            for (int j=0;j<fila.length;j++) {

                if (fila[j]=='R') arr[j] += i;
            }
        }
        for (int i : arr) System.out.print(i + " ");
        System.out.println("");
    }
}