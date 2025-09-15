package org.example;

import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class PropiedadesRelaciones {

    // Verifica si la relación es sobre el conjunto dado
    public static boolean esRelacionValida(Set<Integer> conjunto, Set<Par> relacion)
    {
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

    public static void conjunto(){
        Scanner in = new Scanner(System.in);

        // Conjunto
        Set<Integer> conjunto = new HashSet<>();
        Set<Par> relacion = new HashSet<>();

        // Ingreso dinámico del conjunto
        System.out.println("=== INGRESO DEL CONJUNTO ===");
        System.out.println("Ingrese los elementos del conjunto (ingrese 'fin' para terminar):");

        while (true) {
            System.out.print("Elemento (número entero): ");
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("fin")) {

                break;
            }

            try {
                int elemento = Integer.parseInt(input);
                if (conjunto.add(elemento)) {
                    System.out.println("Elemento " + elemento + " agregado.");
                } else {
                    System.out.println("El elemento " + elemento + " ya existe en el conjunto.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número entero válido o 'fin' para terminar.");
            }
        }

        if (conjunto.isEmpty()) {
            System.out.println("El conjunto no puede estar vacío. Saliendo...");
            in.close();
            return;
        }

        System.out.println("\nConjunto final: " + conjunto);

        // Ingreso dinámico de la relación
        System.out.println("\n=== INGRESO DE LA RELACIÓN ===");
        System.out.println("Ingrese los pares de la relación (formato: x,y).");
        System.out.println("Ingrese 'fin' para terminar.\n");

        while (true) {
            System.out.print("Par (formato x,y): ");
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("fin")) {
                break;
            }

            try {
                String[] partes = input.split(",");
                if (partes.length != 2) {
                    System.out.println("Formato incorrecto. Use: x,y");
                    continue;
                }

                int x = Integer.parseInt(partes[0].trim());
                int y = Integer.parseInt(partes[1].trim());

                // Verificar que los elementos de la relacion estén en el conjunto
                if (!conjunto.contains(x) || !conjunto.contains(y)) {
                    System.out.println("Error: Los elementos deben estar en el conjunto.");
                    continue;
                }

                Par nuevoPar = new Par(x, y);
                if (relacion.add(nuevoPar)) {
                    System.out.println("Par " + nuevoPar + " agregado.");
                } else {
                    System.out.println("El par " + nuevoPar + " ya existe en la relación.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese números válidos. Formato: x,y");
            } catch (Exception e) {
                System.out.println("Error en el formato. Use: x,y");
            }
        }

        in.close();


        // Mostrar resultados
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Conjunto: " + conjunto);
        System.out.println("Relación: " + relacion);

        if (relacion.isEmpty()) {
            System.out.println("\nLA RELACION ESTA VACIA:");
            System.out.println("Reflexiva: " + esReflexiva(conjunto, relacion));
            System.out.println("Irreflexiva: " + esIrreflexiva(conjunto, relacion));
            System.out.println("Simétrica: " + esSimetrica(relacion));
            System.out.println("Asimétrica: " + esAsimetrica(relacion));
            System.out.println("Antisimétrica: " + esAntisimetrica(relacion));
            System.out.println("Transitiva: " + esTransitiva(relacion));
        } else {
            System.out.println("\nPropiedades de la relación:");
            System.out.println("Reflexiva: " + esReflexiva(conjunto, relacion));
            System.out.println("Irreflexiva: " + esIrreflexiva(conjunto, relacion));
            System.out.println("Simétrica: " + esSimetrica(relacion));
            System.out.println("Asimétrica: " + esAsimetrica(relacion));
            System.out.println("Antisimétrica: " + esAntisimetrica(relacion));
            System.out.println("Transitiva: " + esTransitiva(relacion));
        }

        // Crear relación de orden para el conjunto
        Set<Par> relacionOrden = new HashSet<>();
        List<Integer> conjuntoOrdenado = new ArrayList<>(conjunto);
        Collections.sort(conjuntoOrdenado);

        for (int i = 0; i < conjuntoOrdenado.size(); i++) {
            for (int j = i; j < conjuntoOrdenado.size(); j++) {
                int x = conjuntoOrdenado.get(i);
                int y = conjuntoOrdenado.get(j);
                relacionOrden.add(new Par(x, y));
            }
        }

        System.out.println("\nRelación de orden: " + relacionOrden);
        System.out.println("Reflexiva: " + esReflexiva(conjunto, relacionOrden));
        System.out.println("Antisimétrica: " + esAntisimetrica(relacionOrden));
        System.out.println("Transitiva: " + esTransitiva(relacionOrden));

        visualizarHasseGraphStream(conjunto, relacionOrden);
    }

    public static void visualizarHasseGraphStream(Set<Integer> conjunto, Set<Par> hasse) {
        System.setProperty("org.graphstream.ui", "swing");

        Graph graph = new SingleGraph("Diagrama de Hasse");
        graph.setAttribute("ui.stylesheet", "node { size: 20px; fill-color: #999; text-size: 14; }");

        // Agregar nodos
        for (Integer elemento : conjunto) {
            org.graphstream.graph.Node node = graph.addNode(String.valueOf(elemento));
            node.setAttribute("ui.label", elemento);
        }

        // Agregar aristas (sin flechas para Hasse)
        for (Par arista : hasse) {
            String edgeId = arista.x + "-" + arista.y;
            graph.addEdge(edgeId, String.valueOf(arista.x), String.valueOf(arista.y));
        }

        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }

    public static void main(String[] args) {
        //Funcion conjunto
        conjunto();
    }

}