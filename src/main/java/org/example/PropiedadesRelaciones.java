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

    public static void inputs(){
        Scanner in = new Scanner(System.in);

        // Conjunto
        Set<Integer> conjunto = new HashSet<>();
        Set<Par> relacion = new HashSet<>();
        int opc = 0;

        do{

            try{
                System.out.println("\n====== MENU ======");
                System.out.println("Selecciona una opcion del menu.");
                System.out.println("1.- Ingresar un conjunto con su relacion.");
                System.out.println("2.- Salir.");
                System.out.print("Ingresa una opcion: ");
                opc = in.nextInt();
                in.nextLine();
            }catch (InputMismatchException e){
                System.out.print("FATAL ERROR: Debe ingresar un número entero de la opcion del menu. ");
                in.nextLine(); // Limpiar el buffer
                continue; // Si estás en un loop, volver al inicio
            }

            switch (opc){
                case 1:

                    // Ingreso dinámico del conjunto
                    System.out.println("=== INGRESO DEL CONJUNTO ===");
                    System.out.println("Ingrese los elementos del conjunto (ingrese 'fin' para terminar).");

                    while (true) {
                        System.out.print("\nElemento (número entero): ");
                        String input = in.nextLine().trim();

                        if (input.equalsIgnoreCase("fin")) {

                            break;
                        }

                        try {
                            int elemento = Integer.parseInt(input);
                            if (conjunto.add(elemento)) {
                                System.out.print(" | Elemento " + elemento + " agregado.");
                            } else {
                                System.out.print(" | El elemento " + elemento + " ya existe en el conjunto.");
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
                        System.out.print("\nPar (formato x,y): ");
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
                                System.out.print(" | Par " + nuevoPar + " agregado.");
                            } else {
                                System.out.print(" | El par " + nuevoPar + " ya existe en la relación.");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese números válidos. Formato: x,y");
                        } catch (Exception e) {
                            System.out.println("Error en el formato. Use: x,y");
                        }
                    }

                    //in.close();
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

                    if(esReflexiva(conjunto,relacion) && esAntisimetrica(relacion) && esTransitiva(relacion)){
                        // Crear relación de orden pacial para el conjunto
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

                        System.out.println("\nRelacion de orden parcial. Cumple las propiedades, reflexiva, antisimetrica y transitiva.");
                        System.out.println("Relación de orden: " + relacionOrden);
                        System.out.println("Reflexiva: " + esReflexiva(conjunto, relacionOrden));
                        System.out.println("Antisimétrica: " + esAntisimetrica(relacionOrden));
                        System.out.println("Transitiva: " + esTransitiva(relacionOrden) + "\n");


                        Set<Par> diagramaHasse = obtenerDiagramaHasse(relacionOrden);

                        visualizarHasseGraphStream(diagramaHasse);
                    }else{
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("+No cumple las tres propiedades necesarias para realizar el diagrama de hasse.+");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

                    }

                    conjunto.clear();
                    relacion.clear();

                    break;
                case 2:
                    System.out.println("Nos vemos pronto...");
                    break;
                default:
                    System.out.println("Seleccione una opcion del menu...");
            }
        }while(opc != 2);

        in.close();
    }

    public static void visualizarHasseGraphStream(Set<Par> relacion) {
        // Extraer todos los elementos únicos de la relación
        Set<Integer> elementos = new HashSet<>();
        for (Par par : relacion) {
            elementos.add(par.x);
            elementos.add(par.y);
        }

        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Diagrama de Hasse");

        // 🎨 ESTILOS PARA EL GRÁFICO
        String stylesheet = """
        node {
            size: 30px;
            fill-color: red;
            text-size: 16;
            text-color: white;
            text-style: bold;
            stroke-mode: plain;
            stroke-color: black;
            stroke-width: 2px;
        }
        edge {
            fill-color: black;
            size: 2px;
        }
        graph {
            padding: 40px;
        }
        """;

        graph.setAttribute("ui.stylesheet", stylesheet);
        graph.setAttribute("ui.title", "Diagrama de Hasse");  // 🏷️ Título de ventana

        // Crear nodos
        for (Integer elemento : elementos) {
            org.graphstream.graph.Node node = graph.addNode(String.valueOf(elemento));
            node.setAttribute("ui.label", elemento);
        }

        // Crear aristas
        for (Par arista : relacion) {
            String edgeId = arista.x + "-" + arista.y;
            graph.addEdge(edgeId, String.valueOf(arista.x), String.valueOf(arista.y));
        }

        // 🎨 MEJORAR VISUALIZACIÓN
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }

    public static Set<Par> obtenerDiagramaHasse(Set<Par> relacionCompleta) {
        Set<Par> hasse = new HashSet<>();

        // Copiar todos los pares no reflexivos
        for (Par par : relacionCompleta) {
            if (par.x != par.y) { // Eliminar pares reflexivos (lazos)
                hasse.add(par);
            }
        }

        // Eliminar relaciones transitivas (redundantes)
        Set<Par> redundantes = new HashSet<>();
        for (Par par1 : hasse) {
            for (Par par2 : hasse) {
                if (par1.y == par2.x && par1.x != par2.x && par1.y != par2.y) {
                    // Buscar si existe par1.x -> par2.y directamente
                    Par posibleRedundante = new Par(par1.x, par2.y);
                    if (hasse.contains(posibleRedundante)) {
                        redundantes.add(posibleRedundante);
                    }
                }
            }
        }

        hasse.removeAll(redundantes);
        return hasse;
    }

    public static void conjunto(){
        inputs();
    }


    public static void main(String[] args) {
        //Funcion conjunto
        conjunto();
    }

}