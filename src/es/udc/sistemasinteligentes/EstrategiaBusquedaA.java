package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.PriorityQueue;
import java.util.Queue;

public class EstrategiaBusquedaA implements EstrategiaBusquedaInformada{
    //Diapositiva 121
    public EstrategiaBusquedaA(){
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        Queue<Nodo> frontierQueue = new PriorityQueue<>();
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Nodo thisNodo = new Nodo(null, null, p.getEstadoInicial(), h);
        frontierQueue.add(thisNodo);

        int i = 1;
        int cntNodes = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + p.getEstadoInicial());

        while(!p.esMeta(p.getEstadoInicial())) {
            if (frontierQueue.isEmpty()) {
                throw new EmptyStackException();
            }
            thisNodo = frontierQueue.remove();
            System.out.println((i++) + "Estado actual cambiado a " + thisNodo.getEstado());

            if (p.esMeta(thisNodo.getEstado())) { //estadoActual = thisNodo.getEstado
                break;
            }
            else{
                explorados.add(thisNodo.getEstado());
                Accion[] accionesDisponibles = p.acciones(thisNodo.getEstado());

                for (Accion acc : accionesDisponibles) {
                    Estado sc = p.result(thisNodo.getEstado(), acc);
                    Nodo nextNode = new Nodo(thisNodo, acc, sc, h);
                    cntNodes++;

                    if(!frontierQueue.contains(nextNode) && !explorados.contains(sc)){
                        frontierQueue.add(nextNode);
                        System.out.println((i++) + " - " + sc + " NO explorado");
                        System.out.println((i++) + " - Nodo anadido a frontera" + nextNode.toString());
                    }
                    else{
                        System.out.println("Ya explorado");
                    }
                }

            }
        }

        System.out.println("Numero de nodos creados: " +cntNodes);
        System.out.println("Numero de nodos expandidos: " +explorados.size());
        System.out.println((i++) + " - FIN - ");
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
    }

}
