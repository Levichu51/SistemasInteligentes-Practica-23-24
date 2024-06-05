package es.udc.sistemasinteligentes;


public class Nodo implements Comparable<Nodo> {
    private final Nodo nodoPadre;
    private final Accion accion;
    private final Estado estado;
    private float coste;
    private float func;

    public Nodo(Nodo nodoPadre, Accion accion, Estado estado, Heuristica heuristica) {
        this.nodoPadre = nodoPadre;
        this.accion = accion;
        this.estado = estado;

        if(nodoPadre != null){
            this.coste = nodoPadre.coste + accion.getCoste();
        }

        if(heuristica != null){
            this.func = heuristica.evalua(this.estado) + this.coste;
        }
    }

    @Override
    public int compareTo(Nodo o) {
        if(this.func < o.func){
            return -1;
        }
        else if (this.func > o.func) {
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "nodoPadre =" + nodoPadre +
                ", accion =" + accion +
                ", estado =" + estado +
                ", coste =" + coste +
                ", func =" + func +
                '}';
    }

    public Nodo getNodoPadre() {
        return nodoPadre;
    }

    public Estado getEstado() {
        return estado;
    }
}
