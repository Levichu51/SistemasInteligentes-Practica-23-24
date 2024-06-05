package es.udc.sistemasinteligentes;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadrado ec = (EstadoCuadrado) es;
        int sumaDiagonal1 = 0;
        int sumaDiagonal2 = 0;
        int sumaFila = 0;
        int sumaCol = 0;

        for (int i = 0; i < ec.getSize(); i++) {
            sumaDiagonal1 += ec.getCuadrado()[i][i];
            sumaDiagonal2 += ec.getCuadrado()[i][ec.getSize() - 1 - i];
        }

        if (sumaDiagonal1 != sumaDiagonal2) {
            return false;
        }

        for (int i = 0; i < ec.getSize(); i++) {
            for (int j = 0; j < ec.getSize(); j++) {
                sumaFila += ec.getCuadrado()[i][j];
                sumaCol += ec.getCuadrado()[j][i];
            }
        }

        if (sumaFila != sumaCol || sumaCol != sumaDiagonal1) {
            return false;
        }

        return true;
    }

    @Override
    public Accion[] acciones(Estado es) {
        EstadoCuadrado estadoCuadrado = (EstadoCuadrado) es;
        ArrayList<Accion> listaAcciones = new ArrayList<>();

        // Obtener la lista de valores presentes en el cuadrado
        ArrayList<Integer> valoresPresentes = obtenerValoresPresentes(estadoCuadrado);

        // Obtener la lista de valores ausentes en el cuadrado
        ArrayList<Integer> valoresAusentes = obtenerValoresAusentes(estadoCuadrado, valoresPresentes);

        // Generar acciones para cada casilla vacía
        for (int fila = 0; fila < estadoCuadrado.getSize(); fila++) {
            for (int columna = 0; columna < estadoCuadrado.getSize(); columna++) {

                if (estadoCuadrado.getCuadrado()[fila][columna] == 0) {

                    for (Integer valor : valoresAusentes) { //recorre las casillas con 0 y aplica numeros ausentes
                        Accion accion = new AccionCuadrado(fila, columna, valor);

                        if (accion.esAplicable(estadoCuadrado)) {
                            listaAcciones.add(accion);
                        }
                    }
                }
            }
        }
        return listaAcciones.toArray(new Accion[0]);
    }

    // Método para obtener los valores presentes en el cuadrado
    private ArrayList<Integer> obtenerValoresPresentes(EstadoCuadrado estadoCuadrado) {
        ArrayList<Integer> valoresPresentes = new ArrayList<>();

        for (int i = 0; i < estadoCuadrado.getSize(); i++) {
            for (int j = 0; j < estadoCuadrado.getSize(); j++) {
                valoresPresentes.add(estadoCuadrado.getCuadrado()[i][j]);
            }
        }
        return valoresPresentes;
    }

    // Método para obtener los valores ausentes en el cuadrado
    private ArrayList<Integer> obtenerValoresAusentes(EstadoCuadrado estadoCuadrado, ArrayList<Integer> valoresPresentes) {
        ArrayList<Integer> valoresAusentes = new ArrayList<>();

        int totalValores = estadoCuadrado.getSize() * estadoCuadrado.getSize();
        for (int i = 1; i <= totalValores; i++) {
            if (!valoresPresentes.contains(i)) {
                valoresAusentes.add(i);
            }
        }
        return valoresAusentes;
    }
}