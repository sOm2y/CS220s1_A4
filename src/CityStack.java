/*
 * CityStack.java
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

class CityStack extends CityStore {

    public CityStack() {
        this.cities = new City[0];
    }

    public int push(City city) {
        city.setProcessingStatus(true);
        City[] tmp = new City[cities.length + 1];
        for (int i=0; i < cities.length; i++) {
            tmp[i] = cities[i];
        }
        tmp[cities.length] = city;
        cities = tmp;
        return cities.length;
    }

    public City pop() {
        if (cities.length > 0) {
            City city = cities[cities.length-1];
            City[] tmp = new City[cities.length - 1];
            for (int i=0; i < cities.length - 1; i++) {
                tmp[i] = cities[i];
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

    public City getStackTop() {
        if (cities.length > 0) {
            return cities[cities.length-1];
        }
        else
        {
            return null;
        }
    }
}
