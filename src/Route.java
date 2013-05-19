/*
 * Route.java
 *
 * This is a Java class for the assignment on graph traversal
 * for COMPSCI220.
 *
 *
 * Author: Ulrich Speidel
 * Version: 1
 * Date: 21/9/2011
 *
 */

public class Route {

    // Fields
    private int to; // number of destination vertex
    private double cost; // cost of the airfare

    // Constructor
    public Route(int to, double cost) {
        this.to = to;
        this.cost = cost;
    }

    // Accessors
    public int getDestination() {
        return to;
    }

    public double getCost() {
        return cost;
    }

}