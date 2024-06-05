package es.udc.sistemasinteligentes;

public class HeuristicaCuadrado extends Heuristica {

    @Override
    public float evalua(Estado e) {
        EstadoCuadrado esC = (EstadoCuadrado) e;
        int n = esC.getSize();
        int[][] cuadrado = esC.getCuadrado();
        int maxN = (n * ((n * n) + 1)) / 2; // La suma mágica esperada

        int result = 0;

        // Calcula la suma de las diagonales y verifica si están completas
        int sumd1 = 0, sumd2 = 0;
        boolean sized1 = true, sized2 = true;

        for (int i = 0; i < n; i++) {
            sumd1 += cuadrado[i][i];
            if (cuadrado[i][i] == 0) sized1 = false; // Verifica si hay celdas vacías

            sumd2 += cuadrado[i][n - 1 - i];
            if (cuadrado[i][n - 1 - i] == 0) sized2 = false; // Verifica si hay celdas vacías
        }

        // Asigna una puntuación a las diagonales
        result += score(sumd1, maxN, sized1);
        result += score(sumd2, maxN, sized2);

        // Calcula la suma de las filas y columnas y asigna una puntuación
        for (int i = 0; i < n; i++) {
            int rowSum = 0, colSum = 0;
            boolean sizeRow = true, sizedCol = true;

            for (int j = 0; j < n; j++) {
                rowSum += cuadrado[i][j];
                if (cuadrado[i][j] == 0) sizeRow = false; // Verifica si hay celdas vacías
                colSum += cuadrado[j][i];
                if (cuadrado[j][i] == 0) sizedCol = false; // Verifica si hay celdas vacías
            }
            // Asigna puntuaciones a filas y columnas
            result += score(rowSum, maxN, sizeRow);
            result += score(colSum, maxN, sizedCol);
        }

        return result;
    }

    // Asigna una puntuación a una fila, columna o diagonal
    private int score(int sum, int maxN, boolean complete) {
        if (sum == maxN && complete) return 0; // La suma es igual a la esperada y está completa
        else if (sum < maxN && !complete) return 1; // La suma es menor a la esperada y no está completa
        else return 1000; // En otros casos
    }
}

/*
 EVALUAR UNA HEURISTICA:
  Heurística Admisible: Una heurística es admisible si nunca sobreestima el costo para llegar desde un nodo dado a un nodo objetivo.
  En otras palabras, el valor retornado por la heurística nunca es mayor que el costo real del camino más corto desde el nodo actual hasta el estado objetivo.
  En el contexto del problema del cuadrado mágico, una heurística admisible nunca daría una evaluación mayor al costo real de llegar a un estado solución

  Heurística Consistente (o Monótona): Una heurística es consistente (o monótona) si, para cada nodo y cada sucesor de ese nodo, la estimación heurística del costo del camino desde el nodo inicial hasta el objetivo,
  pasando por ese sucesor, no es menor que el costo de llegar directamente al objetivo desde el nodo actual.
  En términos más simples, una heurística consistente debe garantizar que el valor heurístico de un nodo
  sea al menos tan grande como el valor heurístico de sus sucesores más el costo real para alcanzar esos sucesores desde el nodo actual.

 */

/*
 ES ADMISIBLE?
  Heurística Admisible: En la heurística proporcionada, las puntuaciones asignadas por la función score son 0 si la suma es igual
  a la suma mágica y está completa, 1 si la suma es menor a la suma mágica y no está completa, y 1000 en otros casos.
  Dado que nunca sobreestima el costo (las puntuaciones siempre son 0 o 1 si está incompleta), la heurística es admisible.

  Para verificar la consistencia de tu heurística, debemos asegurarnos de que para cada nodo (estado) y su sucesor,
  la puntuación heurística del nodo no sea mayor que el costo real de llegar al sucesor más la puntuación heurística del sucesor.

 */