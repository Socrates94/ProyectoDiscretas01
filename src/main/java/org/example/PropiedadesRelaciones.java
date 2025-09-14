import java.util.*;

import java.util.*;

public class PropiedadesRelaciones {

    // Clase Par para representar pares ordenados correctamente
    static class Par {
        int x, y;

        Par(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Par par = (Par) o;
            return x == par.x && y == par.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    // Verifica si la relación es sobre el conjunto dado
    public static boolean esRelacionValida(Set<Integer> conjunto, Set<Par> relacion) {
        for (Par par : relacion) {
            if (!conjunto.contains(par.x) || !conjunto.contains(par.y)) {
                return false;
            }
        }
        return true;
    }

    // Verifica si la relación es reflexiva
    public static boolean esReflexiva(Set<Integer> conjunto, Set<Par> relacion) {
        for (int elemento : conjunto) {
            if (!relacion.contains(new Par(elemento, elemento))) {
                return false;
            }
        }
        return true;
    }

    // Verifica si la relación es irreflexiva
    public static boolean esIrreflexiva(Set<Integer> conjunto, Set<Par> relacion) {
        for (int elemento : conjunto) {
            if (relacion.contains(new Par(elemento, elemento))) {
                return false;
            }
        }
        return true;
    }

    // Verifica si la relación es simétrica
    public static boolean esSimetrica(Set<Par> relacion) {
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);
            if (!relacion.contains(simetrico)) {
                return false;
            }
        }
        return true;
    }

    // Verifica si la relación es asimétrica
    public static boolean esAsimetrica(Set<Par> relacion) {
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);
            // Si existe el par simétrico y no es el mismo elemento (diagonal)
            if (relacion.contains(simetrico) && !par.equals(simetrico)) {
                return false;
            }
        }
        return true;
    }

    // Verifica si la relación es antisimétrica
    public static boolean esAntisimetrica(Set<Par> relacion) {
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);
            // Si existe el simétrico y no es el mismo elemento, viola antisimetría
            if (relacion.contains(simetrico) && !par.equals(simetrico)) {
                return false;
            }
        }
        return true;
    }

    // Verifica si la relación es transitiva (optimizada)
    public static boolean esTransitiva(Set<Par> relacion) {
        // Creamos un mapa para búsquedas rápidas
        Map<Integer, Set<Integer>> mapa = new HashMap<>();
        for (Par par : relacion) {
            mapa.computeIfAbsent(par.x, k -> new HashSet<>()).add(par.y);
        }

        for (Par par1 : relacion) {
            Set<Integer> segundosElementos = mapa.get(par1.y);
            if (segundosElementos != null) {
                for (int z : segundosElementos) {
                    if (!mapa.getOrDefault(par1.x, Collections.emptySet()).contains(z)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Conjunto
        Set<Integer> conjunto = new HashSet<>(Arrays.asList(1, 2, 3));

        // Relación usando la clase Par
        Set<Par> relacion = new HashSet<>();
        relacion.add(new Par(1, 1));
        relacion.add(new Par(2, 2));
        relacion.add(new Par(3, 3));
        relacion.add(new Par(1, 2));
        relacion.add(new Par(2, 1));

        // Validamos que la relación sea sobre el conjunto
        if (!esRelacionValida(conjunto, relacion)) {
            System.out.println("Error: La relación contiene elementos fuera del conjunto");
            return;
        }

        // Resultados
        System.out.println("Conjunto: " + conjunto);
        System.out.println("Relación: " + relacion);
        System.out.println("Reflexiva: " + esReflexiva(conjunto, relacion));
        System.out.println("Irreflexiva: " + esIrreflexiva(conjunto, relacion));
        System.out.println("Simétrica: " + esSimetrica(relacion));
        System.out.println("Asimétrica: " + esAsimetrica(relacion));
        System.out.println("Antisimétrica: " + esAntisimetrica(relacion));
        System.out.println("Transitiva: " + esTransitiva(relacion));

        // Ejemplo adicional: relación de orden
        System.out.println("\n--- Relación de orden (≤) ---");
        Set<Par> relacionOrden = new HashSet<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= 3; j++) {
                relacionOrden.add(new Par(i, j));
            }
        }
        System.out.println("Relación de orden: " + relacionOrden);
        System.out.println("Reflexiva: " + esReflexiva(conjunto, relacionOrden));
        System.out.println("Antisimétrica: " + esAntisimetrica(relacionOrden));
        System.out.println("Transitiva: " + esTransitiva(relacionOrden));
    }
}