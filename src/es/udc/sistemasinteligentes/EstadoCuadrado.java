package es.udc.sistemasinteligentes;

import java.util.Arrays;
import java.util.Objects;

public class EstadoCuadrado extends Estado{
    private final int n;
    private final int [][] cuadrado;

    public EstadoCuadrado(int[][] cuadrado, int n) {
        this.cuadrado = cuadrado;
        this.n = n;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++){
            str.append("[");

            for(int j = 0; j < n; j++){
                str.append(cuadrado[i][j]);

                if(j < n - 1){
                    str.append(", ");
                }
            }
            str.append("]");
            if(i < n - 1){
                str.append("\n");
            }
        }
        str.append("\n");

        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        EstadoCuadrado otherState = (EstadoCuadrado) obj;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(cuadrado[i][j] != otherState.cuadrado[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int primo = 31; //un n primo 17, 31...
        int hash = Objects.hash(n);

        hash = primo * hash * Arrays.hashCode(cuadrado); //hash de la matriz

        return hash;
    }

    public int[][] getCuadrado(){
        return this.cuadrado;
    }
    public int getSize(){
        return this.n;
    }
}
