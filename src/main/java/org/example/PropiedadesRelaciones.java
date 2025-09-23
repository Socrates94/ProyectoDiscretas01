    package org.example;

import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class PropiedadesRelaciones {

//    // Verifica si la relación es sobre el conjunto dado
//    public static boolean esRelacionValida(Set<Integer> conjunto, Set<Par> relacion)
//    {
//        for (Par par : relacion) {
//
//            if (!conjunto.contains(par.x) || !conjunto.contains(par.y)) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    // Verifica si la relación es reflexiva
    public static boolean esReflexiva(Set<Integer> conjunto, Set<Par> relacion) {
        //itera el conjunto para instanciar un objeto Par como elemento iterado del conjunto y verifica si esta en la relacion
        for (int elemento : conjunto) {
            if (!relacion.contains(new Par(elemento, elemento))) {
                return false;
            }
        }
        return true;
    }

    //Muestra los pares reflexivos
    public static void mostrarParesReflexivos(Set<Integer> conjunto, Set<Par> relacion) {

        System.out.println("\nPares reflexivos requeridos:");
        for (int elemento : conjunto) {
            Par par = new Par(elemento, elemento);
            String estado = relacion.contains(par) ? "✅" : "❌";
            System.out.println(estado + " " + par);
        }
    }

    // Verifica si la relación es irreflexiva
    public static boolean esIrreflexiva(Set<Integer> conjunto, Set<Par> relacion) {

        for (int elemento : conjunto) {
            if (relacion.contains(new Par(elemento, elemento))) { //verifica si el par esta en la relacion
                return false;// falla en el primer par reflexivo encontrado
            }
        }
        return true;
    }

    // Verifica si la relación es irreflexiva simple
//    public static boolean esIrreflexiva(Set<Integer> conjunto, Set<Par> relacion) {
//        return !esReflexiva(conjunto, relacion); // ✅ Esto sí funciona
//    }

    //Muestra los pares irreflexivos
    public static void mostrarParesIrreflexivos(Set<Integer> conjunto, Set<Par> relacion) {
        System.out.println("\nPares irreflexivos verificados:");
        for (int elemento : conjunto) {
            Par par = new Par(elemento, elemento);
            if (relacion.contains(par)) {
                System.out.println("❌ Par reflexivo encontrado: " + par + " (debería no existir)");
            } else {
                System.out.println("✅ No existe par reflexivo: " + par);
            }
        }
    }

    // Verifica si la relación es simétrica
    public static boolean esSimetrica(Set<Par> relacion) {
        for (Par par : relacion) {
            //se crea su par simetrico
            Par simetrico = new Par(par.y, par.x);
            if (!relacion.contains(simetrico)) { //ve si este par simetrico esta en la relacion
                return false;//falla en el primer simetrico faltante
            }
        }
        return true;
    }

    //Muestra los pares simetricos
    public static void mostrarParesSimetricos(Set<Par> relacion) {
        System.out.println("\nPares simétricos verificados:");
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);
            if (relacion.contains(simetrico)) {
                System.out.println("✅ Par (" + par.x + "," + par.y + ") tiene su simétrico (" + simetrico.x + "," + simetrico.y + ")");
            } else {
                System.out.println("❌ Par (" + par.x + "," + par.y + ") NO tiene su simétrico (" + simetrico.x + "," + simetrico.y + ")");
            }
        }
    }

    // Verifica si la relación es asimétrica
    public static boolean esAsimetrica(Set<Par> relacion) {
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);
            // Para asimetría: si existe CUALQUIER simétrico (incluidos reflexivos)
            if (relacion.contains(simetrico)) {
                return false; // ❌ No permite ningún simétrico
            }
        }
        return true;
    }

    // Más simple aún:
//    public static boolean esAsimetrica(Set<Par> relacion) {
//        return !esSimetrica(relacion); // ❌ Nada de simetría
//    }

    //Muestra los pares asimetricos de la relacion
    public static void mostrarParesAsimetricos(Set<Par> relacion) {
        System.out.println("\nPares asimétricos verificados:");
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);

            if (par.equals(simetrico)) {
                // Es un par reflexivo (a,a) - permitido en asimétrica
                System.out.println("✅ Par (" + par.x + "," + par.y + ") es reflexivo (permitido)");
            } else if (relacion.contains(simetrico)) {
                // Tiene simétrico pero no es reflexivo - PROHIBIDO
                System.out.println("❌ Par (" + par.x + "," + par.y + ") tiene simétrico (" +
                        simetrico.x + "," + simetrico.y + ") (NO permitido)");
            } else {
                // No tiene simétrico - PERMITIDO
                System.out.println("✅ Par (" + par.x + "," + par.y + ") no tiene simétrico (permitido)");
            }
        }
    }

    // Verifica si la relación es antisimétrica
    public static boolean esAntisimetrica(Set<Par> relacion) {

        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);
            // Si existe el simétrico y no es el mismo elemento, viola antisimetría
            //antisimetrica permite los pares reflexivos !par.equals(simetrico)
            if (relacion.contains(simetrico) && !par.equals(simetrico)) {
                return false;
            }
        }
        return true;
    }

    //Muestra los pares antisimetricos de la relacion
    public static void mostrarParesAntisimetricos(Set<Par> relacion) {
        System.out.println("\nPares antisimétricos verificados:");
        for (Par par : relacion) {
            Par simetrico = new Par(par.y, par.x);

            if (par.equals(simetrico)) {
                // Es un par reflexivo (a,a) - PERMITIDO en antisimétrica
                System.out.println("✅ Par (" + par.x + "," + par.y + ") es reflexivo (permitido)");
            } else if (relacion.contains(simetrico)) {
                // Tiene simétrico pero no es reflexivo - PROHIBIDO
                System.out.println("❌ Par (" + par.x + "," + par.y + ") tiene simétrico (" +
                        simetrico.x + "," + simetrico.y + ") (NO permitido)");
            } else {
                // No tiene simétrico - PERMITIDO
                System.out.println("✅ Par (" + par.x + "," + par.y + ") no tiene simétrico (permitido)");
            }
        }
    }

    // Verifica si la relación es transitiva (optimizada)
    public static boolean esTransitiva(Set<Par> relacion) {

        // Creamos un mapa para búsquedas rápidas
        Map<Integer, Set<Integer>> mapa = new HashMap<>();

        //llenamos el map
//        Busca par.x en el mapa
//        Si existe → devuelve el valor (el Set existente)
//        Si NO existe → ejecuta k -> new HashSet<>() (crea un nuevo Set)
//        Agrega par.y al Set (nuevo o existente)
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

    //Muestra pares transitivos de la relacion
    public static void mostrarParesTransitivos(Set<Par> relacion) {
        System.out.println("\nPares transitivos verificados:");

        // Crear mapa para búsquedas rápidas (igual que en el método original)
        Map<Integer, Set<Integer>> mapa = new HashMap<>();
        for (Par par : relacion) {
            mapa.computeIfAbsent(par.x, k -> new HashSet<>()).add(par.y);
        }

        boolean esTransitiva = true;

        for (Par par1 : relacion) {
            Set<Integer> segundosElementos = mapa.get(par1.y);
            if (segundosElementos != null) {
                for (int z : segundosElementos) {
                    // Verificar si existe (par1.x, z)
                    if (!mapa.getOrDefault(par1.x, Collections.emptySet()).contains(z)) {
                        System.out.println("❌ Falta par transitivo: (" + par1.x + "," + z + ")");
                        System.out.println("   Porque existe (" + par1.x + "," + par1.y + ") y (" + par1.y + "," + z + ")");
                        esTransitiva = false;
                    } else {
                        System.out.println("✅ Par transitivo presente: (" + par1.x + "," + z + ")");
                    }
                }
            }
        }

        if (esTransitiva) {
            System.out.println("✅ Todos los pares transitivos están presentes");
        }
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
                        mostrarParesReflexivos(conjunto, relacion);

                        System.out.println("\nIrreflexiva: " + esIrreflexiva(conjunto, relacion));
                        mostrarParesIrreflexivos(conjunto, relacion);

                        System.out.println("\nSimétrica: " + esSimetrica(relacion));
                        mostrarParesSimetricos(relacion);

                        System.out.println("\nAsimétrica: " + esAsimetrica(relacion));
                        mostrarParesAsimetricos(relacion);

                        System.out.println("\nAntisimétrica: " + esAntisimetrica(relacion));
                        mostrarParesAntisimetricos(relacion);

                        System.out.println("\nTransitiva: " + esTransitiva(relacion));
                        mostrarParesTransitivos(relacion);
                    }

                    //Relacion de equivalencia: reflexiva simetrica y transitiva.
                    if(esReflexiva(conjunto, relacion) && esSimetrica(relacion) && esTransitiva(relacion)){

                        // Crear relación de equivalencia para el conjunto
                        Set<Par> relacionEquivalencia = new HashSet<>();
                        List<Integer> conjuntoEquivalente = new ArrayList<>(conjunto);
                        Collections.sort(conjuntoEquivalente);

                        for (int i = 0; i < conjuntoEquivalente.size(); i++) {
                            for (int j = i; j < conjuntoEquivalente.size(); j++) {
                                int x = conjuntoEquivalente.get(i);
                                int y = conjuntoEquivalente.get(j);
                                relacionEquivalencia.add(new Par(x, y));
                            }
                        }

                        System.out.println("\nRelacion de equivalencia.");
                        System.out.println("Cumple con las propiedades: reflexiva, simetrica y transitiva.");
                        System.out.println("Reflexiva: " + esReflexiva(conjunto, relacionEquivalencia));
                        System.out.println("Simetrica: " + esSimetrica(relacion));
                        System.out.println("Transitiva: " + esTransitiva(relacionEquivalencia));

                        mostrarMatriz(conjunto, relacion);

                        visualizarGraphStream(relacionEquivalencia);

                        Set<Set<Integer>> particion = obtenerParticion(conjunto, relacion);
                        System.out.println("Partición: " + particion + "\n");

                    }

                    //Relacion de orden parcial
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

                        System.out.println("\nRelacion de orden parcial(conjunto parcialmente ordenado).");
                        System.out.println("Cumple las propiedades: reflexiva, antisimetrica y transitiva.");
                        System.out.println("Relación de orden: " + relacionOrden);
                        System.out.println("Reflexiva: " + esReflexiva(conjunto, relacionOrden));
                        System.out.println("Antisimétrica: " + esAntisimetrica(relacionOrden));
                        System.out.println("Transitiva: " + esTransitiva(relacionOrden) + "\n");


                        Set<Par> diagramaHasse = obtenerDiagramaHasse(relacionOrden);

                        visualizarGraphStream(diagramaHasse);

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

    public static void visualizarGraphStream(Set<Par> relacion) {
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

    //metodos para las clases de equivalencia
    public static Set<Set<Integer>> obtenerParticion(Set<Integer> conjunto, Set<Par> relacion) {
        Set<Set<Integer>> particion = new HashSet<>();
        System.out.println("\nClases de equivalencia");
        for (int elemento : conjunto) {
            Set<Integer> clase = obtenerClase(relacion, elemento);
            System.out.println(elemento + ": " + clase);
            particion.add(clase);
        }
        System.out.println();
        return particion;
    }

    public static Set<Integer> obtenerClase(Set<Par> relacion, int elemento) {
        Set<Integer> clase = new HashSet<>();

        for (Par par : relacion) {
            if (par.x == elemento) {
                clase.add(par.y);
                //System.out.println(par);
            }
        }
        return clase;
    }

    public static void mostrarMatriz(Set<Integer> conjunto, Set<Par> relacion) {
        System.out.println("\n=== MATRIZ DE EQUIVALENCIA ===");

        // Convertir a lista ordenada para mostrar consistentemente
        List<Integer> elementos = new ArrayList<>(conjunto);
        Collections.sort(elementos);

        // Encabezado de columnas
        System.out.print("    ");
        for (int col : elementos) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Filas de la matriz
        for (int fila : elementos) {
            System.out.print(fila + " | ");
            for (int col : elementos) {
                // Verificar si el par (fila, col) está en la relación
                if (relacion.contains(new Par(fila, col))) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    //metodo para generar el diagrama de hasse
    public static Set<Par> obtenerDiagramaHasse(Set<Par> relacionCompleta) {
        Set<Par> hasse = new HashSet<>();

        // Copiar todos los pares no reflexivos
        for (Par par : relacionCompleta) {
            if (par.x != par.y) { // Eliminar pares reflexivos (lazos) si par.x es diferente de par.y agrega
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