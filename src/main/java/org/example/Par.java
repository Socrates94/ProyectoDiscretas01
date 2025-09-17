package org.example;

import java.util.Objects;

public class Par {

    public int x;
    public int y;

    public Par(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Se necestia el metodo equals ya que necesario comparar los valores de los objetos Par
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Par par = (Par) o;
        return x == par.x && y == par.y;
    }

    //Para acelerar la busqueda de los pares y no este iterando uno por uno hasta encontrar el par.
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}