/*
 * CityQueue.java
 *
 * This is a Java class for the assignment on graph traversal
 * for COMPSCI220.
 *
 *
 * Author: Ulrich Speidel
 * Version: 1 (incomplete)
 * Date: 21/9/2011
 *
 */

class CityQueue extends CityStore {

    public CityQueue() {
        this.cities = new City[0];
    }

    public int enqueue(City city) {
        city.setProcessingStatus(true);
        City[] tmp = new City[cities.length + 1];
        for (int i=0; i < cities.length; i++) {
            tmp[i] = cities[i];
        }
        tmp[cities.length] = city;
        cities = tmp;
        return cities.length;
    }

    public City dequeue() {
        if (cities.length > 0) {
            City city = cities[0];
            City[] tmp = new City[cities.length - 1];
            for (int i=1; i < cities.length; i++) {
                tmp[i-1] = cities[i];
            }
            cities = tmp;
            city.setProcessingStatus(false);
            return city;
        }
        else
        {
            return null;
        }
    }

    public City getQueueFront() {
        if (cities.length > 0) {
            return cities[0];
        }
        else
        {
            return null;
        }
    }
}
