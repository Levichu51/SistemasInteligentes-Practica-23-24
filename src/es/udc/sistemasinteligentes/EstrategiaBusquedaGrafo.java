package es.udc.sistemasinteligentes;

import java.util.*;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda{
    //Diapositivas de la 70 a la 90 (mas  o menos)
    public EstrategiaBusquedaGrafo(){}

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        Stack<Nodo> frontierStack = new Stack<>();        //profundidad
        //Queue<Nodo> frontierQueue = new LinkedList<>(); //amplitud
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        Nodo thisNodo = new Nodo(null, null, estadoActual, null);
        frontierStack.add(thisNodo);
        //frontierQueue.add(thisNodo);

        int i = 1;
        int cntNodes = 1; // contador de nodos creados
        //explorados.size() n nodos expandidos (visitados + explorados)

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)) {
            if (frontierStack.isEmpty()) { //frontierQueue.isEmpty()
                throw new EmptyStackException();
            }

            //thisNodo = frontierQueue.remove();
            thisNodo = frontierStack.pop();
            System.out.println((i++) + "Estado actual cambiado a " + thisNodo.getEstado());

            if (p.esMeta(thisNodo.getEstado())) { //estadoActual = thisNodo.getEstado
                break;
            }
            else {
                explorados.add(thisNodo.getEstado());
                Accion[] accionesDisponibles = p.acciones(thisNodo.getEstado());

                for (Accion acc : accionesDisponibles) {
                    Estado sc = p.result(thisNodo.getEstado(), acc);
                    Nodo nextNode = new Nodo(thisNodo, acc, sc, null);
                    cntNodes++;

                    if(!frontierStack.contains(nextNode) && !explorados.contains(sc)){
                        frontierStack.add(nextNode);
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
    }
}
