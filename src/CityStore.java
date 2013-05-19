/*
 * CityStore.java
 *
 * This is a Java class for the assignment on graph traversal
 * for COMPSCI220. It is used to implement the CityStack and
 * CityQueue class.
 *
 *
 * Author: Ulrich Speidel
 * Version: 1
 * Date: 21/9/2011
 *
 */

abstract class CityStore {
    protected City[] cities;

    public boolean isEmpty() {
        return ((cities == null) || (cities.length == 0));
    }
}