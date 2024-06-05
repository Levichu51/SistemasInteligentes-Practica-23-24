package es.udc.sistemasinteligentes;

public class AccionCuadrado extends Accion{
    private int x;
    private int y;
    private int number;

    public AccionCuadrado(int x, int y, int number){
        this.x = x;
        this.y = y;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Posicion m[%d][%d] - Numero: %d", x, y, number);
    }

    @Override
    public boolean esAplicable(Estado es) {
        EstadoCuadrado sc = (EstadoCuadrado) aplicaA(es); //hay que ver si es aplicable viendo su estado aplicado
        int[][] cuadrado = sc.getCuadrado();
        int sumFila = 0;
        int sumCol = 0;
        int sumDiagonal1 = 0;
        int sumDiagonal2 = 0;
        int sumaMagica = (sc.getSize()* (sc.getSize() * sc.getSize() + 1)) / 2;

        if(cuadrado[x][y] != 0){
            return false;
        }

        for (int i = 0; i < sc.getSize(); i++){
            sumFila += cuadrado[x][i];
            sumCol += cuadrado[i][y];
            sumDiagonal1 += cuadrado[i][i];
            sumDiagonal2 += cuadrado[i][sc.getSize() - 1 - i];
        }

        if(sumFila != sumaMagica || sumCol != sumaMagica || sumDiagonal1 != sumaMagica || sumDiagonal2 != sumaMagica){
            return false;
        }

        return true;
    }

    @Override
    public Estado aplicaA(Estado es) {
        EstadoCuadrado ec = (EstadoCuadrado) es;

        // Crear una nueva matriz para almacenar el nuevo estado
        int[][] newCuadrado = new int[ec.getSize()][ec.getSize()];

        // Copiar el estado actual al nuevo estado
        for (int i = 0; i < ec.getSize(); i++) {
            System.arraycopy(ec.getCuadrado()[i], 0, newCuadrado[i], 0, ec.getSize());
        }
        // Aplicar el cambio en la posición (x, y)
        newCuadrado[x][y] = number;

        // Devolver un nuevo estado con la matriz actualizada
        return new EstadoCuadrado(newCuadrado, ec.getSize());

    }
}
