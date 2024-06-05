package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo thisNodo = new Nodo(null, null, estadoActual, null);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;

            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);

                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    thisNodo = new Nodo(thisNodo, acc, estadoActual, null);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);

        return reconstruye_sol(thisNodo);
    }


    private Nodo[] reconstruye_sol(Nodo nodo){
        ArrayList<Nodo> sol = new ArrayList<Nodo>();
        Nodo thisNodo = nodo;

        while (thisNodo != null){
            sol.add(0, thisNodo);
            thisNodo = thisNodo.getNodoPadre();
        }

        return sol.toArray(new Nodo[0]);

        //su propósito principal es proporcionar un tipo de referencia para que el método toArray()
        // de la clase ArrayList pueda determinar el tipo de array que debe devolver.
    }

}
