/*
 * City.java
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

public class City {

    // Fields
    private String name; // name of city
    private Route[] routes; // array of routes
    private Route[] sortedRoutes; // array of routes
    private double costTo;
    private String pathTo;
    private int lastRouteChecked;
    private int stepsToSortRoutes; // this property stores the number of steps we need to sort the routes
    private boolean isInStackOrQueue;

    // Constructor
    public City(String name) {
        this.name = name;
        this.costTo = -1;
        this.pathTo = "";
        this.lastRouteChecked = -1;
        this.isInStackOrQueue = false;
    }

    // Accessors
    public String getName() {
        return name;
    }

    // Find out if the City has been flagged as being in a queue or stack
    public boolean getProcessingStatus() {
        return isInStackOrQueue;
    }
    
    // use one of the two following accessors to get at the routes from this city:
    public Route[] getRoutes() {
        return routes; // return all routes as an array
    }

    public Route getRoute(int index) {
        return routes[index]; // return specified route only
    }

    // Call this accessor to get routes sorted by fare
    public Route[] getSortedRoutes() {
        if (sortedRoutes == null) {
            sortRoutes();
        }
        return sortedRoutes; // return all sorted routes as an array
    }

    public double getCostTo() {
        return costTo;
    }

    public String getPathTo() {
        return pathTo;
    }

    public int getLastRouteChecked() {
        return lastRouteChecked;
    }

    public int getStepsToSortRoutes() {
        if (sortedRoutes == null) {
            sortRoutes();
        }
        return stepsToSortRoutes; // return steps needed to sort routes for this city
    }
    
    // Other:
    public Route[] addRoute(int to, double cost) {
        if (routes != null) {
            Route[] tmp = new Route[routes.length+1];
            for (int i=0; i < routes.length; i++) {
                tmp[i] = routes[i];
            }
            tmp[routes.length] = new Route(to,cost);
            routes = tmp;
        }
        else
        {
            routes = new Route[1];
            routes[0] = new Route(to,cost);
        }
        return routes;
    }

    public void sortRoutes() {
        // copy unsorted routes to array in its own right - so the
        // unsorted routes are preserved
        sortedRoutes = new Route[routes.length];
        stepsToSortRoutes = 0;
        for (int i=0; i < routes.length; i++) {
            sortedRoutes[i] = routes[i];
        }
        // simple bubblesort will do here
        Route tmp;
        for (int i=1; i < sortedRoutes.length; i++) {
            for (int j=sortedRoutes.length-1; j >= i; j--) {
                stepsToSortRoutes++;
                if (sortedRoutes[j].getCost() <= sortedRoutes[j-1].getCost()) {
                    tmp = sortedRoutes[j];
                    sortedRoutes[j] = sortedRoutes[j-1];
                    sortedRoutes[j-1] = tmp;
                }
            }
        }
    }


    // mutators
    public void setProcessingStatus(boolean status) {
        isInStackOrQueue = status;
    }
    
    public void setCostTo(double cost) {
        this.costTo = cost;
    }

    public String setPathToVia(City origin, Route route) {
        this.pathTo = origin.getPathTo() + ", " + origin.getName() + " to " + this.name + " ($" + route.getCost() + ")";
        return this.pathTo;
    }

    public void setLastRouteChecked(int routeNo) {
        this.lastRouteChecked = routeNo;
    }

    public void reset() {
        this.costTo = -1;
        this.pathTo = "";
        this.lastRouteChecked = -1;
    }

}