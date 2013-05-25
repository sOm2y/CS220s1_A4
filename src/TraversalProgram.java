/*
 * TraversalProgram.java
 *
 * This is a Java program for the COMPSCI220
 * assignment on graph traversal
 *
 * Author: Ulrich Speidel
 * Version: 1
 * Date: 21/9/2011
 *
 */

public class TraversalProgram {
    
    // The cities array holds the adjacency list for our graph.
    // Each entry in the array is a City object. Each City
    // object contains various properties about the city (such as
    // name, days you want to spend there, etc.) as well as the
    // onward routes available from the city in question.
    // See City.java for details.
    private static final int MIN_CITIES = 25; // minimum number of nodes (cities) to have so the graph does not become disconnected
    private static final int MAX_CITIES = 51; // maximum number of nodes (cities) we can have
    private City[] cities; // This array will be initialised with City objects by initialiseCities()
    private int routeCounter; // Used by initialiseCities() to count routes
    private int[] whiteCities; // array to hold the indices (in the cities array) or white (unprocessed) cities. A -1 indicates that the city with this index is no longer white 
    private int nextWhiteCity; // used in getNextWhiteCity below, gives the index of the next white city in whiteCities
    private int maxCities; // number of cities to initialise
    private int steps; // use this field to count the number of program steps (number of nodes put on stack or in queue, plus number of comparison steps in sorting, where applicable)
    private int sortSteps; // use this field to count the number of comparison steps in sorting, where applicable (algorithms with "cheapest edge first" only)
    private CityStack cityStack;
    private CityQueue cityQueue;

    // These are convenience methods you may wish to use
    private int initWhiteCities() {
        int stepsToSortRoutes = 0;
        whiteCities = new int[maxCities];
        for (int i=0; i < cities.length; i++) {
            whiteCities[i] = i;
            stepsToSortRoutes += cities[i].getStepsToSortRoutes();
        }
        nextWhiteCity = 0;
        return stepsToSortRoutes;
    }

    private int getNextWhiteCity() {
        while ((nextWhiteCity < whiteCities.length) && (whiteCities[nextWhiteCity] < 0)) {
            nextWhiteCity++;
        }
        if (nextWhiteCity < whiteCities.length) {
            return nextWhiteCity;
        }
        else
        {
            return -1;
        }
    }

    // PUT YOUR OWN METHODS HERE
    
    public void task1() {

		initWhiteCities(); 									// initialise city
		cityStack = new CityStack();

		if (cityStack.isEmpty()) { 							// initialise stack
			cityStack.push(cities[0]); 						// push auckland to stack
			whiteCities[0] = -1; 							// set auckland to grey
			cities[nextWhiteCity].setCostTo(0); 			// set cost
		}

		while (!cityStack.isEmpty()) {
			int routeLength = cityStack.getStackTop().getRoutes().length;
			if (cityStack.getStackTop().getLastRouteChecked() < routeLength - 1) {

				cityStack.getStackTop().setLastRouteChecked(
						cityStack.getStackTop().getLastRouteChecked() + 1);		// set next route of this stack
				steps++; 														// step +1
			}
			int destCity = cityStack.getStackTop().getRoutes()[cityStack
					.getStackTop().getLastRouteChecked()].getDestination();		//set destination
			if (whiteCities[destCity] != -1) {									// if destination is white			
				whiteCities[destCity] = -1;										// let it to grey
				cityStack.push(cities[destCity]); 								// push to stack
				
			} else if (cityStack.getStackTop().getLastRouteChecked() >= routeLength - 1) {			
				cityStack.pop(); 												// pop out this stack if it's the 
																				//last route of this city
			}

		}

	}

	public void task2() {

		sortSteps = initWhiteCities(); 						// initialise city
		steps = sortSteps;	
		cityStack = new CityStack();

		if (cityStack.isEmpty()) { 							// initialise stack
			cityStack.push(cities[0]); 						// push auckland to stack
			whiteCities[0] = -1; 							// set auckland to grey
			cities[nextWhiteCity].setCostTo(0); 			// set cost
		}

		while (!cityStack.isEmpty()) {			
			int routeLength = cityStack.getStackTop().getSortedRoutes().length;
			if (cityStack.getStackTop().getLastRouteChecked() < routeLength - 1) {
				cityStack.getStackTop().setLastRouteChecked( 
						cityStack.getStackTop().getLastRouteChecked() + 1);			// set next route of this stack
				steps++; 															// step +1
			}
			int destCity = cityStack.getStackTop().getSortedRoutes()[cityStack
					.getStackTop().getLastRouteChecked()].getDestination();
			if (whiteCities[destCity] != -1) {										// if destination is white		
				whiteCities[destCity] = -1; 										// let it to grey
				cityStack.push(cities[destCity]); 									// push to stack
			
			} else if (cityStack.getStackTop().getLastRouteChecked() >= routeLength - 1) {
				cityStack.pop(); 													// pop out this stack

			}

		}

	}

	public void task3() {
		initWhiteCities();															// initialise city
		cityStack = new CityStack();

		if (cityStack.isEmpty()) {													 // initialise stack
			cityStack.push(cities[0]);												 // push auckland to stack
			whiteCities[0] = -1;													 // set auckland to grey
			cities[nextWhiteCity].setCostTo(0); 									// set cost
		}

		while (!cityStack.isEmpty()) {
			int routeLength = cityStack.getStackTop().getRoutes().length;

			if (cityStack.getStackTop().getLastRouteChecked() < routeLength - 1) {
				cityStack.getStackTop().setLastRouteChecked(						 // set next route
						cityStack.getStackTop().getLastRouteChecked() + 1);
				steps++; 															// step +1
			}
			int destCity = cityStack.getStackTop().getRoutes()[cityStack 
					.getStackTop().getLastRouteChecked()].getDestination();
			double destCost = cityStack.getStackTop().getRoutes()[cityStack
					.getStackTop().getLastRouteChecked()].getCost();
			double lastDestCost = cityStack.getStackTop().getCostTo();
			double totalCost = destCost + lastDestCost;
			double destCostTo = cities[destCity].getCostTo();

			if (whiteCities[destCity] != -1) { 										// if destination is white

				whiteCities[destCity] = -1;											 // let it to grey
				cityStack.push(cities[destCity]); 									// push to stack
				cityStack.getStackTop().setCostTo(totalCost);

			} else {
				if (totalCost < destCostTo) {
					cityStack.push(cities[destCity]);
					cityStack.getStackTop().setCostTo(totalCost);
					cityStack.getStackTop().setLastRouteChecked(-1);
				}
				if (cityStack.getStackTop().getLastRouteChecked() >= routeLength - 1) {
					cityStack.pop(); 													// pop out this stack

				}
			}
		}
	}

	public void task4() {
		sortSteps = initWhiteCities(); // initialise city
		steps = sortSteps;
		cityStack = new CityStack();

		if (cityStack.isEmpty()) { // initialise stack
			cityStack.push(cities[0]); // push auckland to stack
			whiteCities[0] = -1; // set auckland to grey
			cities[nextWhiteCity].setCostTo(0); // set cost
		}

		while (!cityStack.isEmpty()) {
			int routeLength = cityStack.getStackTop().getSortedRoutes().length;

			if (cityStack.getStackTop().getLastRouteChecked() < routeLength - 1) {

				cityStack.getStackTop().setLastRouteChecked( // set next route
						cityStack.getStackTop().getLastRouteChecked() + 1);
				steps++; // step +1
			}
			int destCity = cityStack.getStackTop().getSortedRoutes()[cityStack 
					.getStackTop().getLastRouteChecked()].getDestination();
			double destCost = cityStack.getStackTop().getSortedRoutes()[cityStack
					.getStackTop().getLastRouteChecked()].getCost();
			double lastDestCost = cityStack.getStackTop().getCostTo();
			double totalCost = destCost + lastDestCost;
			double destCostTo = cities[destCity].getCostTo();

			if (whiteCities[destCity] != -1) { // if destination is white

				whiteCities[destCity] = -1; // let it to grey
				cityStack.push(cities[destCity]); // push to stack
				cityStack.getStackTop().setCostTo(totalCost);

			} else {
				if (totalCost < destCostTo) {
					cityStack.push(cities[destCity]);
					cityStack.getStackTop().setCostTo(totalCost);
					cityStack.getStackTop().setLastRouteChecked(-1);
				}
				if (cityStack.getStackTop().getLastRouteChecked() >= routeLength - 1) {
					cityStack.pop(); // pop out this stack

				}
			}
		}
	}

	public void task5() {
		initWhiteCities();
		cityQueue = new CityQueue();
						
		if (cityQueue.isEmpty()) { // initialise stack
			cityQueue.enqueue(cities[0]); // push auckland to stack
			whiteCities[0] = -1; // set auckland to grey
			cities[0].setCostTo(0); // set cost
		}
			
		while(!cityQueue.isEmpty()){
			int routeLength =cityQueue.getQueueFront().getRoutes().length;
			
			for(int i=0;i<routeLength;i++){
				cityQueue.getQueueFront().setLastRouteChecked(i);
				int destCity = cityQueue.getQueueFront().getRoutes()[i].getDestination();
				steps++;
				if(whiteCities[destCity]!=-1){
					cityQueue.enqueue(cities[destCity]);
					whiteCities[destCity] = -1;
					cities[destCity].setCostTo(cityQueue.getQueueFront().getCostTo()
							+cityQueue.getQueueFront().getRoute(cityQueue.getQueueFront().getLastRouteChecked()).getCost());			
				}
			}
			cityQueue.dequeue();
		}
		
	}
	public void task6() {
		sortSteps = initWhiteCities(); // initialise city
		steps = sortSteps;
		cityQueue = new CityQueue();
						
		if (cityQueue.isEmpty()) { // initialise stack
			cityQueue.enqueue(cities[0]); // push auckland to stack
			whiteCities[0] = -1; // set auckland to grey
			cities[0].setCostTo(0); // set cost
		}
		
		while(!cityQueue.isEmpty()){
			int routeLength =cityQueue.getQueueFront().getSortedRoutes().length;
			
			for(int i=0;i<routeLength;i++){
				cityQueue.getQueueFront().setLastRouteChecked(i);
				int destCity = cityQueue.getQueueFront().getSortedRoutes()[i].getDestination();
				steps++;
				if(whiteCities[destCity]!=-1){
					cityQueue.enqueue(cities[destCity]);
					whiteCities[destCity] = -1;
					cities[destCity].setCostTo(cityQueue.getQueueFront().getCostTo()
							+cityQueue.getQueueFront().getSortedRoutes()[cityQueue.getQueueFront().getLastRouteChecked()].getCost());					
				}
			}
			cityQueue.dequeue();
		}
		
	}

	
	public void task7() {
		initWhiteCities();
		cityQueue = new CityQueue();
						
		if (cityQueue.isEmpty()) { // initialise stack
			cityQueue.enqueue(cities[0]); // push auckland to stack
			whiteCities[0] = -1; // set auckland to grey
			cities[0].setCostTo(0); // set cost
		}
		
		while(!cityQueue.isEmpty()){
			int routeLength =cityQueue.getQueueFront().getRoutes().length;
			
			for(int i=0;i<routeLength;i++){
				cityQueue.getQueueFront().setLastRouteChecked(i);
				int destCity = cityQueue.getQueueFront().getRoutes()[i].getDestination();
				double curCityCostTo = cityQueue.getQueueFront().getCostTo();
				double destCityCost = cityQueue.getQueueFront().getRoute(cityQueue.getQueueFront().getLastRouteChecked()).getCost();
				steps++;
				System.out.println(steps+" to "+(steps+1));
				if(whiteCities[destCity]!=-1){
					cityQueue.enqueue(cities[destCity]);
					whiteCities[destCity] = -1;
					cities[destCity].setCostTo(curCityCostTo+destCityCost);
				
				}else{				
					if(curCityCostTo+destCityCost<cities[destCity].getCostTo()){
						cities[destCity].setCostTo(curCityCostTo+destCityCost);
						if(!cities[destCity].getProcessingStatus()){						
							cityQueue.enqueue(cities[destCity]);
						}
					}
				}
			}
			cityQueue.dequeue();
		}
		
	}
	
	public void task8(){
		sortSteps = initWhiteCities(); // initialise city
		steps = sortSteps;
		cityQueue = new CityQueue();
						
		if (cityQueue.isEmpty()) { // initialise stack
			cityQueue.enqueue(cities[0]); // push auckland to stack
			whiteCities[0] = -1; // set auckland to grey
			cities[0].setCostTo(0); // set cost
		}
		
		while(!cityQueue.isEmpty()){
			int routeLength =cityQueue.getQueueFront().getSortedRoutes().length;
			
			for(int i=0;i<routeLength;i++){
				cityQueue.getQueueFront().setLastRouteChecked(i);
				int destCity = cityQueue.getQueueFront().getSortedRoutes()[i].getDestination();
				double curCityCostTo = cityQueue.getQueueFront().getCostTo();
				double destCityCost = cityQueue.getQueueFront().getSortedRoutes()[cityQueue.getQueueFront().getLastRouteChecked()].getCost();
			
				steps++;
				
				if(whiteCities[destCity]!=-1){
					cityQueue.enqueue(cities[destCity]);
					whiteCities[destCity] = -1;
					cities[destCity].setCostTo(curCityCostTo+destCityCost);
				
				}else{				
					if(curCityCostTo+destCityCost<cities[destCity].getCostTo()){					
						cities[destCity].setCostTo(curCityCostTo+destCityCost);
						if(!cities[destCity].getProcessingStatus()){
							
							cityQueue.enqueue(cities[destCity]);
						}
					}
				}
			}
			cityQueue.dequeue();
		}
	}
    
    
    
    
    // This is a diagnostic method used by start()
    public void printCitiesAndPaths() {
        City city;
        Route route;
        Route[] routes;
        for (int i=0; i < cities.length; i++) {
            city = cities[i];
            System.out.print("City: " + city.getName());
            if (city.getPathTo() == "") {
                System.out.println();
                routes = city.getRoutes();
                for (int j=0; j < routes.length; j++) {
                    route = routes[j];
                    System.out.println("    Route to: " + cities[route.getDestination()].getName() + " for $" + route.getCost());
                }
            }
            else
            {
                System.out.println(" ($" + city.getCostTo() + ")" + city.getPathTo());
            }
            System.out.println();
        }
        System.out.println(cities.length + " cities and " + routeCounter + " routes loaded.");
        System.out.println();        
    }

    // start() method - you will need to amend this method here to call your own traversal methods above
    public void start() {
        maxCities = -1;
        int choice = -1;
        while (choice !=0) {
            maxCities = -1;
            choice = -1;
            String maxCityChoice;
            while ((maxCities < MIN_CITIES) || (maxCities > MAX_CITIES)) {
                System.out.print("Number of cities to initialize (25 to " + MAX_CITIES + ") [" + MAX_CITIES + "]: ");
                maxCityChoice = Keyboard.readInput();
                if (maxCityChoice.length() == 0) {
                    maxCities = MAX_CITIES;
                }
                else
                {
                    maxCities = Integer.parseInt(maxCityChoice);
                }
                System.out.println();
            }
            sortSteps = 0;
            steps = 0;
            initialiseCities();
            printCitiesAndPaths();
            System.out.println();
            System.out.println("1) Perform plain DFS");
            System.out.println("2) Perform DFS with cheapest edge first");
            System.out.println("3) Perform DFS with path optimisation");
            System.out.println("4) Perform DFS with path optimisation and cheapest edge first");
            System.out.println("5) Perform plain BFS");
            System.out.println("6) Perform BFS with cheapest edge first");
            System.out.println("7) Perform BFS with path optimisation");
            System.out.println("8) Perform BFS with path optimisation and cheapest edge first");
            System.out.println("9) Perform the Dijkstra algorithm (optional - you do not need to implement this)");
            System.out.println("0) Quit");
            System.out.print("Your choice: ");
            choice = Integer.parseInt(Keyboard.readInput());
            System.out.println();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("*** Plain DFS ***");
                    // PUT YOUR METHOD CALL HERE
                    task1();
                    break;
                case 2:
                    System.out.println("*** DFS with cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    task2();
                    break;
                case 3:
                    System.out.println("*** DFS with path optimisation ***");
                    task3();
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 4:
                    System.out.println("*** DFS with path optimisation and cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    task4();
                    break;
                case 5:
                    System.out.println("*** Plain BFS ***");
                    // PUT YOUR METHOD CALL HERE
                    task5();
                    break;
                case 6:
                    System.out.println("*** BFS with cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    task6();
                    break;
                case 7:
                    System.out.println("*** BFS with path optimisation ***");
                    // PUT YOUR METHOD CALL HERE
                    task7();
                    break;
                case 8:
                    System.out.println("*** BFS with path optimisation and cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    task8();
                    break;
                case 9:
                    System.out.println("*** Dijkstra ***");
                    // OPTIONAL: PUT YOUR METHOD CALL HERE
                    
                    break;
                case 0: return;
            }
            printCitiesAndPaths();
            System.out.println("Sorting steps taken (if any): " + sortSteps);
            System.out.println("Total steps taken: " + steps);
        }
    }
    
    // The following method initialises the cities and routes
    public void initialiseCities() {
        cities = new City[maxCities];
        // Prices individualised for AUID 5398177.
        // Submit your assignment with these values.
        // Add cities to the cities array 
        // for AUID 5398177 
        if (0 < maxCities) {
            cities[0] = new City("Auckland");
        }
        if (1 < maxCities) {
            cities[1] = new City("Adelaide");
        }
        if (2 < maxCities) {
            cities[2] = new City("Apia");
        }
        if (3 < maxCities) {
            cities[3] = new City("Athens");
        }
        if (4 < maxCities) {
            cities[4] = new City("Beijing");
        }
        if (5 < maxCities) {
            cities[5] = new City("Berlin");
        }
        if (6 < maxCities) {
            cities[6] = new City("Buenos Aires");
        }
        if (7 < maxCities) {
            cities[7] = new City("Cairo");
        }
        if (8 < maxCities) {
            cities[8] = new City("Cancun");
        }
        if (9 < maxCities) {
            cities[9] = new City("Chongqing");
        }
        if (10 < maxCities) {
            cities[10] = new City("Denpasar");
        }
        if (11 < maxCities) {
            cities[11] = new City("Dubai");
        }
        if (12 < maxCities) {
            cities[12] = new City("El Paso");
        }
        if (13 < maxCities) {
            cities[13] = new City("Frankfurt");
        }
        if (14 < maxCities) {
            cities[14] = new City("Geneva");
        }
        if (15 < maxCities) {
            cities[15] = new City("Guangdong");
        }
        if (16 < maxCities) {
            cities[16] = new City("Hong Kong");
        }
        if (17 < maxCities) {
            cities[17] = new City("Honolulu");
        }
        if (18 < maxCities) {
            cities[18] = new City("Istanbul");
        }
        if (19 < maxCities) {
            cities[19] = new City("Jakarta");
        }
        if (20 < maxCities) {
            cities[20] = new City("Jerusalem");
        }
        if (21 < maxCities) {
            cities[21] = new City("Johannesburg");
        }
        if (22 < maxCities) {
            cities[22] = new City("Kolkata");
        }
        if (23 < maxCities) {
            cities[23] = new City("London");
        }
        if (24 < maxCities) {
            cities[24] = new City("Los Angeles");
        }
        if (25 < maxCities) {
            cities[25] = new City("Manila");
        }
        if (26 < maxCities) {
            cities[26] = new City("Melbourne");
        }
        if (27 < maxCities) {
            cities[27] = new City("Mexico City");
        }
        if (28 < maxCities) {
            cities[28] = new City("Milan");
        }
        if (29 < maxCities) {
            cities[29] = new City("Moscow");
        }
        if (30 < maxCities) {
            cities[30] = new City("Mumbai");
        }
        if (31 < maxCities) {
            cities[31] = new City("Nairobi");
        }
        if (32 < maxCities) {
            cities[32] = new City("New York");
        }
        if (33 < maxCities) {
            cities[33] = new City("Nice");
        }
        if (34 < maxCities) {
            cities[34] = new City("Oslo");
        }
        if (35 < maxCities) {
            cities[35] = new City("Paris");
        }
        if (36 < maxCities) {
            cities[36] = new City("Perth");
        }
        if (37 < maxCities) {
            cities[37] = new City("Quebec");
        }
        if (38 < maxCities) {
            cities[38] = new City("Rome");
        }
        if (39 < maxCities) {
            cities[39] = new City("Santiago");
        }
        if (40 < maxCities) {
            cities[40] = new City("Seoul");
        }
        if (41 < maxCities) {
            cities[41] = new City("Shanghai");
        }
        if (42 < maxCities) {
            cities[42] = new City("Singapore");
        }
        if (43 < maxCities) {
            cities[43] = new City("Sydney");
        }
        if (44 < maxCities) {
            cities[44] = new City("Tangiers");
        }
        if (45 < maxCities) {
            cities[45] = new City("Tokyo");
        }
        if (46 < maxCities) {
            cities[46] = new City("Ulan Bataar");
        }
        if (47 < maxCities) {
            cities[47] = new City("Vancouver");
        }
        if (48 < maxCities) {
            cities[48] = new City("Venice");
        }
        if (49 < maxCities) {
            cities[49] = new City("Vienna");
        }
        if (50 < maxCities) {
            cities[50] = new City("Zurich");
        }

        // Add routes to cities
        // for AUID 5398177 

        routeCounter = 0;
        if ((0 < maxCities) && (1 < maxCities)) {
            cities[0].addRoute(1,309); // Auckland to Adelaide
            routeCounter++;
            cities[1].addRoute(0,309); // Adelaide to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (2 < maxCities)) {
            cities[0].addRoute(2,185.4); // Auckland to Apia
            routeCounter++;
            cities[2].addRoute(0,185.4); // Apia to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (6 < maxCities)) {
            cities[0].addRoute(6,1152); // Auckland to Buenos Aires
            routeCounter++;
            cities[6].addRoute(0,1152); // Buenos Aires to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (17 < maxCities)) {
            cities[0].addRoute(17,721); // Auckland to Honolulu
            routeCounter++;
            cities[17].addRoute(0,721); // Honolulu to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (24 < maxCities)) {
            cities[0].addRoute(24,1184.5); // Auckland to Los Angeles
            routeCounter++;
            cities[24].addRoute(0,1184.5); // Los Angeles to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (36 < maxCities)) {
            cities[0].addRoute(36,556.2); // Auckland to Perth
            routeCounter++;
            cities[36].addRoute(0,556.2); // Perth to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (39 < maxCities)) {
            cities[0].addRoute(39,1133); // Auckland to Santiago
            routeCounter++;
            cities[39].addRoute(0,1133); // Santiago to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (40 < maxCities)) {
            cities[0].addRoute(40,978.5); // Auckland to Seoul
            routeCounter++;
            cities[40].addRoute(0,978.5); // Seoul to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (42 < maxCities)) {
            cities[0].addRoute(42,1030); // Auckland to Singapore
            routeCounter++;
            cities[42].addRoute(0,1030); // Singapore to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (41 < maxCities)) {
            cities[0].addRoute(41,1133); // Auckland to Shanghai
            routeCounter++;
            cities[41].addRoute(0,1133); // Shanghai to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (43 < maxCities)) {
            cities[0].addRoute(43,220.8); // Auckland to Sydney
            routeCounter++;
            cities[43].addRoute(0,220.8); // Sydney to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (45 < maxCities)) {
            cities[0].addRoute(45,927); // Auckland to Tokyo
            routeCounter++;
            cities[45].addRoute(0,927); // Tokyo to Auckland
            routeCounter++;
        }
        if ((1 < maxCities) && (26 < maxCities)) {
            cities[1].addRoute(26,96); // Adelaide to Melbourne
            routeCounter++;
            cities[26].addRoute(1,96); // Melbourne to Adelaide
            routeCounter++;
        }
        if ((1 < maxCities) && (42 < maxCities)) {
            cities[1].addRoute(42,768); // Adelaide to Singapore
            routeCounter++;
            cities[42].addRoute(1,768); // Singapore to Adelaide
            routeCounter++;
        }
        if ((1 < maxCities) && (43 < maxCities)) {
            cities[1].addRoute(43,144); // Adelaide to Sydney
            routeCounter++;
            cities[43].addRoute(1,144); // Sydney to Adelaide
            routeCounter++;
        }
        if ((1 < maxCities) && (36 < maxCities)) {
            cities[1].addRoute(36,240); // Adelaide to Perth
            routeCounter++;
            cities[36].addRoute(1,240); // Perth to Adelaide
            routeCounter++;
        }
        if ((2 < maxCities) && (43 < maxCities)) {
            cities[2].addRoute(43,192); // Apia to Sydney
            routeCounter++;
            cities[43].addRoute(2,192); // Sydney to Apia
            routeCounter++;
        }
        if ((2 < maxCities) && (26 < maxCities)) {
            cities[2].addRoute(26,226.6); // Apia to Melbourne
            routeCounter++;
            cities[26].addRoute(2,226.6); // Melbourne to Apia
            routeCounter++;
        }
        if ((3 < maxCities) && (42 < maxCities)) {
            cities[3].addRoute(42,927); // Athens to Singapore
            routeCounter++;
            cities[42].addRoute(3,927); // Singapore to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (5 < maxCities)) {
            cities[3].addRoute(5,154.5); // Athens to Berlin
            routeCounter++;
            cities[5].addRoute(3,154.5); // Berlin to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (7 < maxCities)) {
            cities[3].addRoute(7,257.5); // Athens to Cairo
            routeCounter++;
            cities[7].addRoute(3,257.5); // Cairo to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (11 < maxCities)) {
            cities[3].addRoute(11,268.8); // Athens to Dubai
            routeCounter++;
            cities[11].addRoute(3,268.8); // Dubai to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (13 < maxCities)) {
            cities[3].addRoute(13,154.5); // Athens to Frankfurt
            routeCounter++;
            cities[13].addRoute(3,154.5); // Frankfurt to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (20 < maxCities)) {
            cities[3].addRoute(20,257.5); // Athens to Jerusalem
            routeCounter++;
            cities[20].addRoute(3,257.5); // Jerusalem to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (23 < maxCities)) {
            cities[3].addRoute(23,257.5); // Athens to London
            routeCounter++;
            cities[23].addRoute(3,257.5); // London to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (28 < maxCities)) {
            cities[3].addRoute(28,103); // Athens to Milan
            routeCounter++;
            cities[28].addRoute(3,103); // Milan to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (29 < maxCities)) {
            cities[3].addRoute(29,309); // Athens to Moscow
            routeCounter++;
            cities[29].addRoute(3,309); // Moscow to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (32 < maxCities)) {
            cities[3].addRoute(32,1133); // Athens to New York
            routeCounter++;
            cities[32].addRoute(3,1133); // New York to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (35 < maxCities)) {
            cities[3].addRoute(35,206); // Athens to Paris
            routeCounter++;
            cities[35].addRoute(3,206); // Paris to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (49 < maxCities)) {
            cities[3].addRoute(49,144.2); // Athens to Vienna
            routeCounter++;
            cities[49].addRoute(3,144.2); // Vienna to Athens
            routeCounter++;
        }
        if ((4 < maxCities) && (5 < maxCities)) {
            cities[4].addRoute(5,960); // Beijing to Berlin
            routeCounter++;
            cities[5].addRoute(4,960); // Berlin to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (9 < maxCities)) {
            cities[4].addRoute(9,192); // Beijing to Chongqing
            routeCounter++;
            cities[9].addRoute(4,192); // Chongqing to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (11 < maxCities)) {
            cities[4].addRoute(11,912); // Beijing to Dubai
            routeCounter++;
            cities[11].addRoute(4,912); // Dubai to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (14 < maxCities)) {
            cities[4].addRoute(14,1008); // Beijing to Geneva
            routeCounter++;
            cities[14].addRoute(4,1008); // Geneva to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (15 < maxCities)) {
            cities[4].addRoute(15,144); // Beijing to Guangdong
            routeCounter++;
            cities[15].addRoute(4,144); // Guangdong to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (16 < maxCities)) {
            cities[4].addRoute(16,153.6); // Beijing to Hong Kong
            routeCounter++;
            cities[16].addRoute(4,153.6); // Hong Kong to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (17 < maxCities)) {
            cities[4].addRoute(17,624); // Beijing to Honolulu
            routeCounter++;
            cities[17].addRoute(4,624); // Honolulu to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (19 < maxCities)) {
            cities[4].addRoute(19,360.5); // Beijing to Jakarta
            routeCounter++;
            cities[19].addRoute(4,360.5); // Jakarta to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (22 < maxCities)) {
            cities[4].addRoute(22,384); // Beijing to Kolkata
            routeCounter++;
            cities[22].addRoute(4,384); // Kolkata to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (23 < maxCities)) {
            cities[4].addRoute(23,1008); // Beijing to London
            routeCounter++;
            cities[23].addRoute(4,1008); // London to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (24 < maxCities)) {
            cities[4].addRoute(24,1200); // Beijing to Los Angeles
            routeCounter++;
            cities[24].addRoute(4,1200); // Los Angeles to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (25 < maxCities)) {
            cities[4].addRoute(25,206); // Beijing to Manila
            routeCounter++;
            cities[25].addRoute(4,206); // Manila to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (26 < maxCities)) {
            cities[4].addRoute(26,624); // Beijing to Melbourne
            routeCounter++;
            cities[26].addRoute(4,624); // Melbourne to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (27 < maxCities)) {
            cities[4].addRoute(27,1248); // Beijing to Mexico City
            routeCounter++;
            cities[27].addRoute(4,1248); // Mexico City to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (28 < maxCities)) {
            cities[4].addRoute(28,1152); // Beijing to Milan
            routeCounter++;
            cities[28].addRoute(4,1152); // Milan to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (29 < maxCities)) {
            cities[4].addRoute(29,960); // Beijing to Moscow
            routeCounter++;
            cities[29].addRoute(4,960); // Moscow to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (30 < maxCities)) {
            cities[4].addRoute(30,432); // Beijing to Mumbai
            routeCounter++;
            cities[30].addRoute(4,432); // Mumbai to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (32 < maxCities)) {
            cities[4].addRoute(32,1152); // Beijing to New York
            routeCounter++;
            cities[32].addRoute(4,1152); // New York to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (34 < maxCities)) {
            cities[4].addRoute(34,960); // Beijing to Oslo
            routeCounter++;
            cities[34].addRoute(4,960); // Oslo to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (35 < maxCities)) {
            cities[4].addRoute(35,1133); // Beijing to Paris
            routeCounter++;
            cities[35].addRoute(4,1133); // Paris to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (38 < maxCities)) {
            cities[4].addRoute(38,1104); // Beijing to Rome
            routeCounter++;
            cities[38].addRoute(4,1104); // Rome to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (32 < maxCities)) {
            cities[4].addRoute(32,1152); // Beijing to New York
            routeCounter++;
            cities[32].addRoute(4,1152); // New York to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (41 < maxCities)) {
            cities[4].addRoute(41,192); // Beijing to Shanghai
            routeCounter++;
            cities[41].addRoute(4,192); // Shanghai to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (42 < maxCities)) {
            cities[4].addRoute(42,336); // Beijing to Singapore
            routeCounter++;
            cities[42].addRoute(4,336); // Singapore to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (45 < maxCities)) {
            cities[4].addRoute(45,192); // Beijing to Tokyo
            routeCounter++;
            cities[45].addRoute(4,192); // Tokyo to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (46 < maxCities)) {
            cities[4].addRoute(46,240); // Beijing to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(4,240); // Ulan Bataar to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (47 < maxCities)) {
            cities[4].addRoute(47,927); // Beijing to Vancouver
            routeCounter++;
            cities[47].addRoute(4,927); // Vancouver to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (50 < maxCities)) {
            cities[4].addRoute(50,1248); // Beijing to Zurich
            routeCounter++;
            cities[50].addRoute(4,1248); // Zurich to Beijing
            routeCounter++;
        }
        if ((5 < maxCities) && (13 < maxCities)) {
            cities[5].addRoute(13,96); // Berlin to Frankfurt
            routeCounter++;
            cities[13].addRoute(5,96); // Frankfurt to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (14 < maxCities)) {
            cities[5].addRoute(14,115.2); // Berlin to Geneva
            routeCounter++;
            cities[14].addRoute(5,115.2); // Geneva to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (18 < maxCities)) {
            cities[5].addRoute(18,211.2); // Berlin to Istanbul
            routeCounter++;
            cities[18].addRoute(5,211.2); // Istanbul to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (20 < maxCities)) {
            cities[5].addRoute(20,240); // Berlin to Jerusalem
            routeCounter++;
            cities[20].addRoute(5,240); // Jerusalem to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (23 < maxCities)) {
            cities[5].addRoute(23,144); // Berlin to London
            routeCounter++;
            cities[23].addRoute(5,144); // London to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (28 < maxCities)) {
            cities[5].addRoute(28,240); // Berlin to Milan
            routeCounter++;
            cities[28].addRoute(5,240); // Milan to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (29 < maxCities)) {
            cities[5].addRoute(29,336); // Berlin to Moscow
            routeCounter++;
            cities[29].addRoute(5,336); // Moscow to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (31 < maxCities)) {
            cities[5].addRoute(31,824); // Berlin to Nairobi
            routeCounter++;
            cities[31].addRoute(5,824); // Nairobi to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (32 < maxCities)) {
            cities[5].addRoute(32,672); // Berlin to New York
            routeCounter++;
            cities[32].addRoute(5,672); // New York to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (34 < maxCities)) {
            cities[5].addRoute(34,115.2); // Berlin to Oslo
            routeCounter++;
            cities[34].addRoute(5,115.2); // Oslo to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (35 < maxCities)) {
            cities[5].addRoute(35,154.5); // Berlin to Paris
            routeCounter++;
            cities[35].addRoute(5,154.5); // Paris to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (38 < maxCities)) {
            cities[5].addRoute(38,384); // Berlin to Rome
            routeCounter++;
            cities[38].addRoute(5,384); // Rome to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (45 < maxCities)) {
            cities[5].addRoute(45,1440); // Berlin to Tokyo
            routeCounter++;
            cities[45].addRoute(5,1440); // Tokyo to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (49 < maxCities)) {
            cities[5].addRoute(49,288); // Berlin to Vienna
            routeCounter++;
            cities[49].addRoute(5,288); // Vienna to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (50 < maxCities)) {
            cities[5].addRoute(50,192); // Berlin to Zurich
            routeCounter++;
            cities[50].addRoute(5,192); // Zurich to Berlin
            routeCounter++;
        }
        if ((6 < maxCities) && (13 < maxCities)) {
            cities[6].addRoute(13,1545); // Buenos Aires to Frankfurt
            routeCounter++;
            cities[13].addRoute(6,1545); // Frankfurt to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (14 < maxCities)) {
            cities[6].addRoute(14,1442); // Buenos Aires to Geneva
            routeCounter++;
            cities[14].addRoute(6,1442); // Geneva to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (17 < maxCities)) {
            cities[6].addRoute(17,1236); // Buenos Aires to Honolulu
            routeCounter++;
            cities[17].addRoute(6,1236); // Honolulu to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (21 < maxCities)) {
            cities[6].addRoute(21,1030); // Buenos Aires to Johannesburg
            routeCounter++;
            cities[21].addRoute(6,1030); // Johannesburg to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (23 < maxCities)) {
            cities[6].addRoute(23,1493.5); // Buenos Aires to London
            routeCounter++;
            cities[23].addRoute(6,1493.5); // London to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (24 < maxCities)) {
            cities[6].addRoute(24,1030); // Buenos Aires to Los Angeles
            routeCounter++;
            cities[24].addRoute(6,1030); // Los Angeles to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (27 < maxCities)) {
            cities[6].addRoute(27,1030); // Buenos Aires to Mexico City
            routeCounter++;
            cities[27].addRoute(6,1030); // Mexico City to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (32 < maxCities)) {
            cities[6].addRoute(32,1442); // Buenos Aires to New York
            routeCounter++;
            cities[32].addRoute(6,1442); // New York to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (35 < maxCities)) {
            cities[6].addRoute(35,1442); // Buenos Aires to Paris
            routeCounter++;
            cities[35].addRoute(6,1442); // Paris to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (37 < maxCities)) {
            cities[6].addRoute(37,1440); // Buenos Aires to Quebec
            routeCounter++;
            cities[37].addRoute(6,1440); // Quebec to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (38 < maxCities)) {
            cities[6].addRoute(38,1493.5); // Buenos Aires to Rome
            routeCounter++;
            cities[38].addRoute(6,1493.5); // Rome to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (39 < maxCities)) {
            cities[6].addRoute(39,463.5); // Buenos Aires to Santiago
            routeCounter++;
            cities[39].addRoute(6,463.5); // Santiago to Buenos Aires
            routeCounter++;
        }
        if ((7 < maxCities) && (11 < maxCities)) {
            cities[7].addRoute(11,240); // Cairo to Dubai
            routeCounter++;
            cities[11].addRoute(7,240); // Dubai to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (13 < maxCities)) {
            cities[7].addRoute(13,240); // Cairo to Frankfurt
            routeCounter++;
            cities[13].addRoute(7,240); // Frankfurt to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (14 < maxCities)) {
            cities[7].addRoute(14,336); // Cairo to Geneva
            routeCounter++;
            cities[14].addRoute(7,336); // Geneva to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (16 < maxCities)) {
            cities[7].addRoute(16,1296); // Cairo to Hong Kong
            routeCounter++;
            cities[16].addRoute(7,1296); // Hong Kong to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (18 < maxCities)) {
            cities[7].addRoute(18,192); // Cairo to Istanbul
            routeCounter++;
            cities[18].addRoute(7,192); // Istanbul to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (20 < maxCities)) {
            cities[7].addRoute(20,384); // Cairo to Jerusalem
            routeCounter++;
            cities[20].addRoute(7,384); // Jerusalem to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (21 < maxCities)) {
            cities[7].addRoute(21,864); // Cairo to Johannesburg
            routeCounter++;
            cities[21].addRoute(7,864); // Johannesburg to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (23 < maxCities)) {
            cities[7].addRoute(23,288); // Cairo to London
            routeCounter++;
            cities[23].addRoute(7,288); // London to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (28 < maxCities)) {
            cities[7].addRoute(28,288); // Cairo to Milan
            routeCounter++;
            cities[28].addRoute(7,288); // Milan to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (31 < maxCities)) {
            cities[7].addRoute(31,336); // Cairo to Nairobi
            routeCounter++;
            cities[31].addRoute(7,336); // Nairobi to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (32 < maxCities)) {
            cities[7].addRoute(32,1440); // Cairo to New York
            routeCounter++;
            cities[32].addRoute(7,1440); // New York to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (35 < maxCities)) {
            cities[7].addRoute(35,480); // Cairo to Paris
            routeCounter++;
            cities[35].addRoute(7,480); // Paris to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (38 < maxCities)) {
            cities[7].addRoute(38,288); // Cairo to Rome
            routeCounter++;
            cities[38].addRoute(7,288); // Rome to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (44 < maxCities)) {
            cities[7].addRoute(44,336); // Cairo to Tangiers
            routeCounter++;
            cities[44].addRoute(7,336); // Tangiers to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (49 < maxCities)) {
            cities[7].addRoute(49,384); // Cairo to Vienna
            routeCounter++;
            cities[49].addRoute(7,384); // Vienna to Cairo
            routeCounter++;
        }
        if ((8 < maxCities) && (27 < maxCities)) {
            cities[8].addRoute(27,144); // Cancun to Mexico City
            routeCounter++;
            cities[27].addRoute(8,144); // Mexico City to Cancun
            routeCounter++;
        }
        if ((8 < maxCities) && (32 < maxCities)) {
            cities[8].addRoute(32,384); // Cancun to New York
            routeCounter++;
            cities[32].addRoute(8,384); // New York to Cancun
            routeCounter++;
        }
        if ((8 < maxCities) && (24 < maxCities)) {
            cities[8].addRoute(24,336); // Cancun to Los Angeles
            routeCounter++;
            cities[24].addRoute(8,336); // Los Angeles to Cancun
            routeCounter++;
        }
        if ((8 < maxCities) && (47 < maxCities)) {
            cities[8].addRoute(47,480); // Cancun to Vancouver
            routeCounter++;
            cities[47].addRoute(8,480); // Vancouver to Cancun
            routeCounter++;
        }
        if ((9 < maxCities) && (11 < maxCities)) {
            cities[9].addRoute(11,1152); // Chongqing to Dubai
            routeCounter++;
            cities[11].addRoute(9,1152); // Dubai to Chongqing
            routeCounter++;
        }
        if ((9 < maxCities) && (15 < maxCities)) {
            cities[9].addRoute(15,144); // Chongqing to Guangdong
            routeCounter++;
            cities[15].addRoute(9,144); // Guangdong to Chongqing
            routeCounter++;
        }
        if ((9 < maxCities) && (41 < maxCities)) {
            cities[9].addRoute(41,192); // Chongqing to Shanghai
            routeCounter++;
            cities[41].addRoute(9,192); // Shanghai to Chongqing
            routeCounter++;
        }
        if ((10 < maxCities) && (11 < maxCities)) {
            cities[10].addRoute(11,1152); // Denpasar to Dubai
            routeCounter++;
            cities[11].addRoute(10,1152); // Dubai to Denpasar
            routeCounter++;
        }
        if ((10 < maxCities) && (42 < maxCities)) {
            cities[10].addRoute(42,384); // Denpasar to Singapore
            routeCounter++;
            cities[42].addRoute(10,384); // Singapore to Denpasar
            routeCounter++;
        }
        if ((10 < maxCities) && (19 < maxCities)) {
            cities[10].addRoute(19,206); // Denpasar to Jakarta
            routeCounter++;
            cities[19].addRoute(10,206); // Jakarta to Denpasar
            routeCounter++;
        }
        if ((11 < maxCities) && (13 < maxCities)) {
            cities[11].addRoute(13,927); // Dubai to Frankfurt
            routeCounter++;
            cities[13].addRoute(11,927); // Frankfurt to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (14 < maxCities)) {
            cities[11].addRoute(14,824); // Dubai to Geneva
            routeCounter++;
            cities[14].addRoute(11,824); // Geneva to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (16 < maxCities)) {
            cities[11].addRoute(16,1030); // Dubai to Hong Kong
            routeCounter++;
            cities[16].addRoute(11,1030); // Hong Kong to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (18 < maxCities)) {
            cities[11].addRoute(18,257.5); // Dubai to Istanbul
            routeCounter++;
            cities[18].addRoute(11,257.5); // Istanbul to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (19 < maxCities)) {
            cities[11].addRoute(19,1030); // Dubai to Jakarta
            routeCounter++;
            cities[19].addRoute(11,1030); // Jakarta to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (21 < maxCities)) {
            cities[11].addRoute(21,1236); // Dubai to Johannesburg
            routeCounter++;
            cities[21].addRoute(11,1236); // Johannesburg to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (22 < maxCities)) {
            cities[11].addRoute(22,927); // Dubai to Kolkata
            routeCounter++;
            cities[22].addRoute(11,927); // Kolkata to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (23 < maxCities)) {
            cities[11].addRoute(23,978.5); // Dubai to London
            routeCounter++;
            cities[23].addRoute(11,978.5); // London to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (24 < maxCities)) {
            cities[11].addRoute(24,1648); // Dubai to Los Angeles
            routeCounter++;
            cities[24].addRoute(11,1648); // Los Angeles to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (26 < maxCities)) {
            cities[11].addRoute(26,1339); // Dubai to Melbourne
            routeCounter++;
            cities[26].addRoute(11,1339); // Melbourne to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (28 < maxCities)) {
            cities[11].addRoute(28,824); // Dubai to Milan
            routeCounter++;
            cities[28].addRoute(11,824); // Milan to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (29 < maxCities)) {
            cities[11].addRoute(29,824); // Dubai to Moscow
            routeCounter++;
            cities[29].addRoute(11,824); // Moscow to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (30 < maxCities)) {
            cities[11].addRoute(30,768); // Dubai to Mumbai
            routeCounter++;
            cities[30].addRoute(11,768); // Mumbai to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (31 < maxCities)) {
            cities[11].addRoute(31,515); // Dubai to Nairobi
            routeCounter++;
            cities[31].addRoute(11,515); // Nairobi to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (32 < maxCities)) {
            cities[11].addRoute(32,1442); // Dubai to New York
            routeCounter++;
            cities[32].addRoute(11,1442); // New York to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (33 < maxCities)) {
            cities[11].addRoute(33,824); // Dubai to Nice
            routeCounter++;
            cities[33].addRoute(11,824); // Nice to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (34 < maxCities)) {
            cities[11].addRoute(34,927); // Dubai to Oslo
            routeCounter++;
            cities[34].addRoute(11,927); // Oslo to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (35 < maxCities)) {
            cities[11].addRoute(35,875.5); // Dubai to Paris
            routeCounter++;
            cities[35].addRoute(11,875.5); // Paris to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (36 < maxCities)) {
            cities[11].addRoute(36,978.5); // Dubai to Perth
            routeCounter++;
            cities[36].addRoute(11,978.5); // Perth to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (38 < maxCities)) {
            cities[11].addRoute(38,463.5); // Dubai to Rome
            routeCounter++;
            cities[38].addRoute(11,463.5); // Rome to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (42 < maxCities)) {
            cities[11].addRoute(42,927); // Dubai to Singapore
            routeCounter++;
            cities[42].addRoute(11,927); // Singapore to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (43 < maxCities)) {
            cities[11].addRoute(43,1200); // Dubai to Sydney
            routeCounter++;
            cities[43].addRoute(11,1200); // Sydney to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (44 < maxCities)) {
            cities[11].addRoute(44,618); // Dubai to Tangiers
            routeCounter++;
            cities[44].addRoute(11,618); // Tangiers to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (45 < maxCities)) {
            cities[11].addRoute(45,1339); // Dubai to Tokyo
            routeCounter++;
            cities[45].addRoute(11,1339); // Tokyo to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (49 < maxCities)) {
            cities[11].addRoute(49,772.5); // Dubai to Vienna
            routeCounter++;
            cities[49].addRoute(11,772.5); // Vienna to Dubai
            routeCounter++;
        }
        if ((12 < maxCities) && (24 < maxCities)) {
            cities[12].addRoute(24,144); // El Paso to Los Angeles
            routeCounter++;
            cities[24].addRoute(12,144); // Los Angeles to El Paso
            routeCounter++;
        }
        if ((12 < maxCities) && (27 < maxCities)) {
            cities[12].addRoute(27,257.5); // El Paso to Mexico City
            routeCounter++;
            cities[27].addRoute(12,257.5); // Mexico City to El Paso
            routeCounter++;
        }
        if ((12 < maxCities) && (32 < maxCities)) {
            cities[12].addRoute(32,360.5); // El Paso to New York
            routeCounter++;
            cities[32].addRoute(12,360.5); // New York to El Paso
            routeCounter++;
        }
        if ((13 < maxCities) && (14 < maxCities)) {
            cities[13].addRoute(14,206); // Frankfurt to Geneva
            routeCounter++;
            cities[14].addRoute(13,206); // Geneva to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (15 < maxCities)) {
            cities[13].addRoute(15,1152); // Frankfurt to Guangdong
            routeCounter++;
            cities[15].addRoute(13,1152); // Guangdong to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (16 < maxCities)) {
            cities[13].addRoute(16,1339); // Frankfurt to Hong Kong
            routeCounter++;
            cities[16].addRoute(13,1339); // Hong Kong to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (18 < maxCities)) {
            cities[13].addRoute(18,192); // Frankfurt to Istanbul
            routeCounter++;
            cities[18].addRoute(13,192); // Istanbul to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (19 < maxCities)) {
            cities[13].addRoute(19,1545); // Frankfurt to Jakarta
            routeCounter++;
            cities[19].addRoute(13,1545); // Jakarta to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (20 < maxCities)) {
            cities[13].addRoute(20,309); // Frankfurt to Jerusalem
            routeCounter++;
            cities[20].addRoute(13,309); // Jerusalem to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (21 < maxCities)) {
            cities[13].addRoute(21,978.5); // Frankfurt to Johannesburg
            routeCounter++;
            cities[21].addRoute(13,978.5); // Johannesburg to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (22 < maxCities)) {
            cities[13].addRoute(22,1133); // Frankfurt to Kolkata
            routeCounter++;
            cities[22].addRoute(13,1133); // Kolkata to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (23 < maxCities)) {
            cities[13].addRoute(23,206); // Frankfurt to London
            routeCounter++;
            cities[23].addRoute(13,206); // London to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (24 < maxCities)) {
            cities[13].addRoute(24,927); // Frankfurt to Los Angeles
            routeCounter++;
            cities[24].addRoute(13,927); // Los Angeles to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (25 < maxCities)) {
            cities[13].addRoute(25,1339); // Frankfurt to Manila
            routeCounter++;
            cities[25].addRoute(13,1339); // Manila to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (27 < maxCities)) {
            cities[13].addRoute(27,1133); // Frankfurt to Mexico City
            routeCounter++;
            cities[27].addRoute(13,1133); // Mexico City to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (28 < maxCities)) {
            cities[13].addRoute(28,257.5); // Frankfurt to Milan
            routeCounter++;
            cities[28].addRoute(13,257.5); // Milan to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (30 < maxCities)) {
            cities[13].addRoute(30,960); // Frankfurt to Mumbai
            routeCounter++;
            cities[30].addRoute(13,960); // Mumbai to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (31 < maxCities)) {
            cities[13].addRoute(31,824); // Frankfurt to Nairobi
            routeCounter++;
            cities[31].addRoute(13,824); // Nairobi to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (32 < maxCities)) {
            cities[13].addRoute(32,618); // Frankfurt to New York
            routeCounter++;
            cities[32].addRoute(13,618); // New York to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (33 < maxCities)) {
            cities[13].addRoute(33,206); // Frankfurt to Nice
            routeCounter++;
            cities[33].addRoute(13,206); // Nice to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (34 < maxCities)) {
            cities[13].addRoute(34,240); // Frankfurt to Oslo
            routeCounter++;
            cities[34].addRoute(13,240); // Oslo to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (35 < maxCities)) {
            cities[13].addRoute(35,154.5); // Frankfurt to Paris
            routeCounter++;
            cities[35].addRoute(13,154.5); // Paris to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (37 < maxCities)) {
            cities[13].addRoute(37,720); // Frankfurt to Quebec
            routeCounter++;
            cities[37].addRoute(13,720); // Quebec to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (38 < maxCities)) {
            cities[13].addRoute(38,257.5); // Frankfurt to Rome
            routeCounter++;
            cities[38].addRoute(13,257.5); // Rome to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (40 < maxCities)) {
            cities[13].addRoute(40,1153.6); // Frankfurt to Seoul
            routeCounter++;
            cities[40].addRoute(13,1153.6); // Seoul to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (41 < maxCities)) {
            cities[13].addRoute(41,1081.5); // Frankfurt to Shanghai
            routeCounter++;
            cities[41].addRoute(13,1081.5); // Shanghai to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (42 < maxCities)) {
            cities[13].addRoute(42,1153.6); // Frankfurt to Singapore
            routeCounter++;
            cities[42].addRoute(13,1153.6); // Singapore to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (44 < maxCities)) {
            cities[13].addRoute(44,309); // Frankfurt to Tangiers
            routeCounter++;
            cities[44].addRoute(13,309); // Tangiers to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (45 < maxCities)) {
            cities[13].addRoute(45,1184.5); // Frankfurt to Tokyo
            routeCounter++;
            cities[45].addRoute(13,1184.5); // Tokyo to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (47 < maxCities)) {
            cities[13].addRoute(47,875.5); // Frankfurt to Vancouver
            routeCounter++;
            cities[47].addRoute(13,875.5); // Vancouver to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (48 < maxCities)) {
            cities[13].addRoute(48,257.5); // Frankfurt to Venice
            routeCounter++;
            cities[48].addRoute(13,257.5); // Venice to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (49 < maxCities)) {
            cities[13].addRoute(49,206); // Frankfurt to Vienna
            routeCounter++;
            cities[49].addRoute(13,206); // Vienna to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (50 < maxCities)) {
            cities[13].addRoute(50,172.8); // Frankfurt to Zurich
            routeCounter++;
            cities[50].addRoute(13,172.8); // Zurich to Frankfurt
            routeCounter++;
        }
        if ((14 < maxCities) && (16 < maxCities)) {
            cities[14].addRoute(16,1152); // Geneva to Hong Kong
            routeCounter++;
            cities[16].addRoute(14,1152); // Hong Kong to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (20 < maxCities)) {
            cities[14].addRoute(20,432); // Geneva to Jerusalem
            routeCounter++;
            cities[20].addRoute(14,432); // Jerusalem to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (23 < maxCities)) {
            cities[14].addRoute(23,432); // Geneva to London
            routeCounter++;
            cities[23].addRoute(14,432); // London to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (28 < maxCities)) {
            cities[14].addRoute(28,115.2); // Geneva to Milan
            routeCounter++;
            cities[28].addRoute(14,115.2); // Milan to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (32 < maxCities)) {
            cities[14].addRoute(32,768); // Geneva to New York
            routeCounter++;
            cities[32].addRoute(14,768); // New York to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (34 < maxCities)) {
            cities[14].addRoute(34,288); // Geneva to Oslo
            routeCounter++;
            cities[34].addRoute(14,288); // Oslo to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (35 < maxCities)) {
            cities[14].addRoute(35,103); // Geneva to Paris
            routeCounter++;
            cities[35].addRoute(14,103); // Paris to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (37 < maxCities)) {
            cities[14].addRoute(37,960); // Geneva to Quebec
            routeCounter++;
            cities[37].addRoute(14,960); // Quebec to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (38 < maxCities)) {
            cities[14].addRoute(38,96); // Geneva to Rome
            routeCounter++;
            cities[38].addRoute(14,96); // Rome to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (49 < maxCities)) {
            cities[14].addRoute(49,96); // Geneva to Vienna
            routeCounter++;
            cities[49].addRoute(14,96); // Vienna to Geneva
            routeCounter++;
        }
        if ((15 < maxCities) && (16 < maxCities)) {
            cities[15].addRoute(16,51.5); // Guangdong to Hong Kong
            routeCounter++;
            cities[16].addRoute(15,51.5); // Hong Kong to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (19 < maxCities)) {
            cities[15].addRoute(19,257.5); // Guangdong to Jakarta
            routeCounter++;
            cities[19].addRoute(15,257.5); // Jakarta to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (22 < maxCities)) {
            cities[15].addRoute(22,257.5); // Guangdong to Kolkata
            routeCounter++;
            cities[22].addRoute(15,257.5); // Kolkata to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (23 < maxCities)) {
            cities[15].addRoute(23,824); // Guangdong to London
            routeCounter++;
            cities[23].addRoute(15,824); // London to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (25 < maxCities)) {
            cities[15].addRoute(25,412); // Guangdong to Manila
            routeCounter++;
            cities[25].addRoute(15,412); // Manila to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (26 < maxCities)) {
            cities[15].addRoute(26,824); // Guangdong to Melbourne
            routeCounter++;
            cities[26].addRoute(15,824); // Melbourne to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (35 < maxCities)) {
            cities[15].addRoute(35,824); // Guangdong to Paris
            routeCounter++;
            cities[35].addRoute(15,824); // Paris to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (36 < maxCities)) {
            cities[15].addRoute(36,721); // Guangdong to Perth
            routeCounter++;
            cities[36].addRoute(15,721); // Perth to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (42 < maxCities)) {
            cities[15].addRoute(42,309); // Guangdong to Singapore
            routeCounter++;
            cities[42].addRoute(15,309); // Singapore to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (43 < maxCities)) {
            cities[15].addRoute(43,816); // Guangdong to Sydney
            routeCounter++;
            cities[43].addRoute(15,816); // Sydney to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (50 < maxCities)) {
            cities[15].addRoute(50,1248); // Guangdong to Zurich
            routeCounter++;
            cities[50].addRoute(15,1248); // Zurich to Guangdong
            routeCounter++;
        }
        if ((16 < maxCities) && (17 < maxCities)) {
            cities[16].addRoute(17,960); // Hong Kong to Honolulu
            routeCounter++;
            cities[17].addRoute(16,960); // Honolulu to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (18 < maxCities)) {
            cities[16].addRoute(18,816); // Hong Kong to Istanbul
            routeCounter++;
            cities[18].addRoute(16,816); // Istanbul to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (19 < maxCities)) {
            cities[16].addRoute(19,206); // Hong Kong to Jakarta
            routeCounter++;
            cities[19].addRoute(16,206); // Jakarta to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (22 < maxCities)) {
            cities[16].addRoute(22,240); // Hong Kong to Kolkata
            routeCounter++;
            cities[22].addRoute(16,240); // Kolkata to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (23 < maxCities)) {
            cities[16].addRoute(23,720); // Hong Kong to London
            routeCounter++;
            cities[23].addRoute(16,720); // London to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (25 < maxCities)) {
            cities[16].addRoute(25,257.5); // Hong Kong to Manila
            routeCounter++;
            cities[25].addRoute(16,257.5); // Manila to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (26 < maxCities)) {
            cities[16].addRoute(26,844.6); // Hong Kong to Melbourne
            routeCounter++;
            cities[26].addRoute(16,844.6); // Melbourne to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (35 < maxCities)) {
            cities[16].addRoute(35,844.6); // Hong Kong to Paris
            routeCounter++;
            cities[35].addRoute(16,844.6); // Paris to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (36 < maxCities)) {
            cities[16].addRoute(36,741.6); // Hong Kong to Perth
            routeCounter++;
            cities[36].addRoute(16,741.6); // Perth to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (38 < maxCities)) {
            cities[16].addRoute(38,768); // Hong Kong to Rome
            routeCounter++;
            cities[38].addRoute(16,768); // Rome to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (40 < maxCities)) {
            cities[16].addRoute(40,412); // Hong Kong to Seoul
            routeCounter++;
            cities[40].addRoute(16,412); // Seoul to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (41 < maxCities)) {
            cities[16].addRoute(41,154.5); // Hong Kong to Shanghai
            routeCounter++;
            cities[41].addRoute(16,154.5); // Shanghai to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (42 < maxCities)) {
            cities[16].addRoute(42,370.8); // Hong Kong to Singapore
            routeCounter++;
            cities[42].addRoute(16,370.8); // Singapore to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (43 < maxCities)) {
            cities[16].addRoute(43,854.4); // Hong Kong to Sydney
            routeCounter++;
            cities[43].addRoute(16,854.4); // Sydney to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (45 < maxCities)) {
            cities[16].addRoute(45,403.2); // Hong Kong to Tokyo
            routeCounter++;
            cities[45].addRoute(16,403.2); // Tokyo to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (46 < maxCities)) {
            cities[16].addRoute(46,690.1); // Hong Kong to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(16,690.1); // Ulan Bataar to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (47 < maxCities)) {
            cities[16].addRoute(47,1236); // Hong Kong to Vancouver
            routeCounter++;
            cities[47].addRoute(16,1236); // Vancouver to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (49 < maxCities)) {
            cities[16].addRoute(49,1030); // Hong Kong to Vienna
            routeCounter++;
            cities[49].addRoute(16,1030); // Vienna to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (50 < maxCities)) {
            cities[16].addRoute(50,1238.4); // Hong Kong to Zurich
            routeCounter++;
            cities[50].addRoute(16,1238.4); // Zurich to Hong Kong
            routeCounter++;
        }
        if ((17 < maxCities) && (24 < maxCities)) {
            cities[17].addRoute(24,576); // Honolulu to Los Angeles
            routeCounter++;
            cities[24].addRoute(17,576); // Los Angeles to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (27 < maxCities)) {
            cities[17].addRoute(27,669.5); // Honolulu to Mexico City
            routeCounter++;
            cities[27].addRoute(17,669.5); // Mexico City to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (32 < maxCities)) {
            cities[17].addRoute(32,1030); // Honolulu to New York
            routeCounter++;
            cities[32].addRoute(17,1030); // New York to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (40 < maxCities)) {
            cities[17].addRoute(40,824); // Honolulu to Seoul
            routeCounter++;
            cities[40].addRoute(17,824); // Seoul to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (43 < maxCities)) {
            cities[17].addRoute(43,912); // Honolulu to Sydney
            routeCounter++;
            cities[43].addRoute(17,912); // Sydney to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (45 < maxCities)) {
            cities[17].addRoute(45,720); // Honolulu to Tokyo
            routeCounter++;
            cities[45].addRoute(17,720); // Tokyo to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (47 < maxCities)) {
            cities[17].addRoute(47,875.5); // Honolulu to Vancouver
            routeCounter++;
            cities[47].addRoute(17,875.5); // Vancouver to Honolulu
            routeCounter++;
        }
        if ((18 < maxCities) && (23 < maxCities)) {
            cities[18].addRoute(23,463.5); // Istanbul to London
            routeCounter++;
            cities[23].addRoute(18,463.5); // London to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (28 < maxCities)) {
            cities[18].addRoute(28,309); // Istanbul to Milan
            routeCounter++;
            cities[28].addRoute(18,309); // Milan to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (30 < maxCities)) {
            cities[18].addRoute(30,624); // Istanbul to Mumbai
            routeCounter++;
            cities[30].addRoute(18,624); // Mumbai to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (32 < maxCities)) {
            cities[18].addRoute(32,1009.4); // Istanbul to New York
            routeCounter++;
            cities[32].addRoute(18,1009.4); // New York to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (34 < maxCities)) {
            cities[18].addRoute(34,528); // Istanbul to Oslo
            routeCounter++;
            cities[34].addRoute(18,528); // Oslo to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (35 < maxCities)) {
            cities[18].addRoute(35,360.5); // Istanbul to Paris
            routeCounter++;
            cities[35].addRoute(18,360.5); // Paris to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (38 < maxCities)) {
            cities[18].addRoute(38,288.4); // Istanbul to Rome
            routeCounter++;
            cities[38].addRoute(18,288.4); // Rome to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (42 < maxCities)) {
            cities[18].addRoute(42,793.1); // Istanbul to Singapore
            routeCounter++;
            cities[42].addRoute(18,793.1); // Singapore to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (44 < maxCities)) {
            cities[18].addRoute(44,484.1); // Istanbul to Tangiers
            routeCounter++;
            cities[44].addRoute(18,484.1); // Tangiers to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (49 < maxCities)) {
            cities[18].addRoute(49,257.5); // Istanbul to Vienna
            routeCounter++;
            cities[49].addRoute(18,257.5); // Vienna to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (50 < maxCities)) {
            cities[18].addRoute(50,288); // Istanbul to Zurich
            routeCounter++;
            cities[50].addRoute(18,288); // Zurich to Istanbul
            routeCounter++;
        }
        if ((19 < maxCities) && (22 < maxCities)) {
            cities[19].addRoute(22,240); // Jakarta to Kolkata
            routeCounter++;
            cities[22].addRoute(19,240); // Kolkata to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (23 < maxCities)) {
            cities[19].addRoute(23,1036.8); // Jakarta to London
            routeCounter++;
            cities[23].addRoute(19,1036.8); // London to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (26 < maxCities)) {
            cities[19].addRoute(26,672); // Jakarta to Melbourne
            routeCounter++;
            cities[26].addRoute(19,672); // Melbourne to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (30 < maxCities)) {
            cities[19].addRoute(30,336); // Jakarta to Mumbai
            routeCounter++;
            cities[30].addRoute(19,336); // Mumbai to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (41 < maxCities)) {
            cities[19].addRoute(41,364.8); // Jakarta to Shanghai
            routeCounter++;
            cities[41].addRoute(19,364.8); // Shanghai to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (42 < maxCities)) {
            cities[19].addRoute(42,144); // Jakarta to Singapore
            routeCounter++;
            cities[42].addRoute(19,144); // Singapore to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (45 < maxCities)) {
            cities[19].addRoute(45,432); // Jakarta to Tokyo
            routeCounter++;
            cities[45].addRoute(19,432); // Tokyo to Jakarta
            routeCounter++;
        }
        if ((20 < maxCities) && (21 < maxCities)) {
            cities[20].addRoute(21,816); // Jerusalem to Johannesburg
            routeCounter++;
            cities[21].addRoute(20,816); // Johannesburg to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (23 < maxCities)) {
            cities[20].addRoute(23,528); // Jerusalem to London
            routeCounter++;
            cities[23].addRoute(20,528); // London to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (28 < maxCities)) {
            cities[20].addRoute(28,384); // Jerusalem to Milan
            routeCounter++;
            cities[28].addRoute(20,384); // Milan to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (31 < maxCities)) {
            cities[20].addRoute(31,463.5); // Jerusalem to Nairobi
            routeCounter++;
            cities[31].addRoute(20,463.5); // Nairobi to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (32 < maxCities)) {
            cities[20].addRoute(32,1200); // Jerusalem to New York
            routeCounter++;
            cities[32].addRoute(20,1200); // New York to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (33 < maxCities)) {
            cities[20].addRoute(33,432); // Jerusalem to Nice
            routeCounter++;
            cities[33].addRoute(20,432); // Nice to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (34 < maxCities)) {
            cities[20].addRoute(34,576); // Jerusalem to Oslo
            routeCounter++;
            cities[34].addRoute(20,576); // Oslo to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (35 < maxCities)) {
            cities[20].addRoute(35,515); // Jerusalem to Paris
            routeCounter++;
            cities[35].addRoute(20,515); // Paris to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (38 < maxCities)) {
            cities[20].addRoute(38,432); // Jerusalem to Rome
            routeCounter++;
            cities[38].addRoute(20,432); // Rome to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (49 < maxCities)) {
            cities[20].addRoute(49,432); // Jerusalem to Vienna
            routeCounter++;
            cities[49].addRoute(20,432); // Vienna to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (50 < maxCities)) {
            cities[20].addRoute(50,432); // Jerusalem to Zurich
            routeCounter++;
            cities[50].addRoute(20,432); // Zurich to Jerusalem
            routeCounter++;
        }
        if ((21 < maxCities) && (23 < maxCities)) {
            cities[21].addRoute(23,1056); // Johannesburg to London
            routeCounter++;
            cities[23].addRoute(21,1056); // London to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (31 < maxCities)) {
            cities[21].addRoute(31,576.8); // Johannesburg to Nairobi
            routeCounter++;
            cities[31].addRoute(21,576.8); // Nairobi to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (35 < maxCities)) {
            cities[21].addRoute(35,1019.7); // Johannesburg to Paris
            routeCounter++;
            cities[35].addRoute(21,1019.7); // Paris to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (36 < maxCities)) {
            cities[21].addRoute(36,1019.7); // Johannesburg to Perth
            routeCounter++;
            cities[36].addRoute(21,1019.7); // Perth to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (42 < maxCities)) {
            cities[21].addRoute(42,1236); // Johannesburg to Singapore
            routeCounter++;
            cities[42].addRoute(21,1236); // Singapore to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (43 < maxCities)) {
            cities[21].addRoute(43,1104); // Johannesburg to Sydney
            routeCounter++;
            cities[43].addRoute(21,1104); // Sydney to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (50 < maxCities)) {
            cities[21].addRoute(50,1008); // Johannesburg to Zurich
            routeCounter++;
            cities[50].addRoute(21,1008); // Zurich to Johannesburg
            routeCounter++;
        }
        if ((22 < maxCities) && (23 < maxCities)) {
            cities[22].addRoute(23,960); // Kolkata to London
            routeCounter++;
            cities[23].addRoute(22,960); // London to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (25 < maxCities)) {
            cities[22].addRoute(25,618); // Kolkata to Manila
            routeCounter++;
            cities[25].addRoute(22,618); // Manila to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (30 < maxCities)) {
            cities[22].addRoute(30,192); // Kolkata to Mumbai
            routeCounter++;
            cities[30].addRoute(22,192); // Mumbai to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (31 < maxCities)) {
            cities[22].addRoute(31,566.5); // Kolkata to Nairobi
            routeCounter++;
            cities[31].addRoute(22,566.5); // Nairobi to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (35 < maxCities)) {
            cities[22].addRoute(35,978.5); // Kolkata to Paris
            routeCounter++;
            cities[35].addRoute(22,978.5); // Paris to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (42 < maxCities)) {
            cities[22].addRoute(42,360.5); // Kolkata to Singapore
            routeCounter++;
            cities[42].addRoute(22,360.5); // Singapore to Kolkata
            routeCounter++;
        }
        if ((23 < maxCities) && (24 < maxCities)) {
            cities[23].addRoute(24,1056); // London to Los Angeles
            routeCounter++;
            cities[24].addRoute(23,1056); // Los Angeles to London
            routeCounter++;
        }
        if ((23 < maxCities) && (25 < maxCities)) {
            cities[23].addRoute(25,1236); // London to Manila
            routeCounter++;
            cities[25].addRoute(23,1236); // Manila to London
            routeCounter++;
        }
        if ((23 < maxCities) && (27 < maxCities)) {
            cities[23].addRoute(27,927); // London to Mexico City
            routeCounter++;
            cities[27].addRoute(23,927); // Mexico City to London
            routeCounter++;
        }
        if ((23 < maxCities) && (28 < maxCities)) {
            cities[23].addRoute(28,309); // London to Milan
            routeCounter++;
            cities[28].addRoute(23,309); // Milan to London
            routeCounter++;
        }
        if ((23 < maxCities) && (30 < maxCities)) {
            cities[23].addRoute(30,768); // London to Mumbai
            routeCounter++;
            cities[30].addRoute(23,768); // Mumbai to London
            routeCounter++;
        }
        if ((23 < maxCities) && (31 < maxCities)) {
            cities[23].addRoute(31,669.5); // London to Nairobi
            routeCounter++;
            cities[31].addRoute(23,669.5); // Nairobi to London
            routeCounter++;
        }
        if ((23 < maxCities) && (32 < maxCities)) {
            cities[23].addRoute(32,618); // London to New York
            routeCounter++;
            cities[32].addRoute(23,618); // New York to London
            routeCounter++;
        }
        if ((23 < maxCities) && (33 < maxCities)) {
            cities[23].addRoute(33,257.5); // London to Nice
            routeCounter++;
            cities[33].addRoute(23,257.5); // Nice to London
            routeCounter++;
        }
        if ((23 < maxCities) && (34 < maxCities)) {
            cities[23].addRoute(34,192); // London to Oslo
            routeCounter++;
            cities[34].addRoute(23,192); // Oslo to London
            routeCounter++;
        }
        if ((23 < maxCities) && (35 < maxCities)) {
            cities[23].addRoute(35,154.5); // London to Paris
            routeCounter++;
            cities[35].addRoute(23,154.5); // Paris to London
            routeCounter++;
        }
        if ((23 < maxCities) && (37 < maxCities)) {
            cities[23].addRoute(37,672); // London to Quebec
            routeCounter++;
            cities[37].addRoute(23,672); // Quebec to London
            routeCounter++;
        }
        if ((23 < maxCities) && (38 < maxCities)) {
            cities[23].addRoute(38,384); // London to Rome
            routeCounter++;
            cities[38].addRoute(23,384); // Rome to London
            routeCounter++;
        }
        if ((23 < maxCities) && (39 < maxCities)) {
            cities[23].addRoute(39,1184.5); // London to Santiago
            routeCounter++;
            cities[39].addRoute(23,1184.5); // Santiago to London
            routeCounter++;
        }
        if ((23 < maxCities) && (40 < maxCities)) {
            cities[23].addRoute(40,1030); // London to Seoul
            routeCounter++;
            cities[40].addRoute(23,1030); // Seoul to London
            routeCounter++;
        }
        if ((23 < maxCities) && (41 < maxCities)) {
            cities[23].addRoute(41,1019.7); // London to Shanghai
            routeCounter++;
            cities[41].addRoute(23,1019.7); // Shanghai to London
            routeCounter++;
        }
        if ((23 < maxCities) && (42 < maxCities)) {
            cities[23].addRoute(42,1133); // London to Singapore
            routeCounter++;
            cities[42].addRoute(23,1133); // Singapore to London
            routeCounter++;
        }
        if ((23 < maxCities) && (44 < maxCities)) {
            cities[23].addRoute(44,463.5); // London to Tangiers
            routeCounter++;
            cities[44].addRoute(23,463.5); // Tangiers to London
            routeCounter++;
        }
        if ((23 < maxCities) && (45 < maxCities)) {
            cities[23].addRoute(45,1008); // London to Tokyo
            routeCounter++;
            cities[45].addRoute(23,1008); // Tokyo to London
            routeCounter++;
        }
        if ((23 < maxCities) && (47 < maxCities)) {
            cities[23].addRoute(47,978.5); // London to Vancouver
            routeCounter++;
            cities[47].addRoute(23,978.5); // Vancouver to London
            routeCounter++;
        }
        if ((23 < maxCities) && (48 < maxCities)) {
            cities[23].addRoute(48,412); // London to Venice
            routeCounter++;
            cities[48].addRoute(23,412); // Venice to London
            routeCounter++;
        }
        if ((23 < maxCities) && (49 < maxCities)) {
            cities[23].addRoute(49,329.6); // London to Vienna
            routeCounter++;
            cities[49].addRoute(23,329.6); // Vienna to London
            routeCounter++;
        }
        if ((23 < maxCities) && (50 < maxCities)) {
            cities[23].addRoute(50,278.4); // London to Zurich
            routeCounter++;
            cities[50].addRoute(23,278.4); // Zurich to London
            routeCounter++;
        }
        if ((24 < maxCities) && (25 < maxCities)) {
            cities[24].addRoute(25,824); // Los Angeles to Manila
            routeCounter++;
            cities[25].addRoute(24,824); // Manila to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (27 < maxCities)) {
            cities[24].addRoute(27,309); // Los Angeles to Mexico City
            routeCounter++;
            cities[27].addRoute(24,309); // Mexico City to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (32 < maxCities)) {
            cities[24].addRoute(32,360.5); // Los Angeles to New York
            routeCounter++;
            cities[32].addRoute(24,360.5); // New York to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (34 < maxCities)) {
            cities[24].addRoute(34,950.4); // Los Angeles to Oslo
            routeCounter++;
            cities[34].addRoute(24,950.4); // Oslo to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (40 < maxCities)) {
            cities[24].addRoute(40,927); // Los Angeles to Seoul
            routeCounter++;
            cities[40].addRoute(24,927); // Seoul to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (42 < maxCities)) {
            cities[24].addRoute(42,1339); // Los Angeles to Singapore
            routeCounter++;
            cities[42].addRoute(24,1339); // Singapore to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (43 < maxCities)) {
            cities[24].addRoute(43,1248); // Los Angeles to Sydney
            routeCounter++;
            cities[43].addRoute(24,1248); // Sydney to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (45 < maxCities)) {
            cities[24].addRoute(45,960); // Los Angeles to Tokyo
            routeCounter++;
            cities[45].addRoute(24,960); // Tokyo to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (50 < maxCities)) {
            cities[24].addRoute(50,1036.8); // Los Angeles to Zurich
            routeCounter++;
            cities[50].addRoute(24,1036.8); // Zurich to Los Angeles
            routeCounter++;
        }
        if ((25 < maxCities) && (41 < maxCities)) {
            cities[25].addRoute(41,192); // Manila to Shanghai
            routeCounter++;
            cities[41].addRoute(25,192); // Shanghai to Manila
            routeCounter++;
        }
        if ((25 < maxCities) && (45 < maxCities)) {
            cities[25].addRoute(45,288); // Manila to Tokyo
            routeCounter++;
            cities[45].addRoute(25,288); // Tokyo to Manila
            routeCounter++;
        }
        if ((26 < maxCities) && (36 < maxCities)) {
            cities[26].addRoute(36,480); // Melbourne to Perth
            routeCounter++;
            cities[36].addRoute(26,480); // Perth to Melbourne
            routeCounter++;
        }
        if ((26 < maxCities) && (41 < maxCities)) {
            cities[26].addRoute(41,768); // Melbourne to Shanghai
            routeCounter++;
            cities[41].addRoute(26,768); // Shanghai to Melbourne
            routeCounter++;
        }
        if ((26 < maxCities) && (43 < maxCities)) {
            cities[26].addRoute(43,192); // Melbourne to Sydney
            routeCounter++;
            cities[43].addRoute(26,192); // Sydney to Melbourne
            routeCounter++;
        }
        if ((26 < maxCities) && (45 < maxCities)) {
            cities[26].addRoute(45,864); // Melbourne to Tokyo
            routeCounter++;
            cities[45].addRoute(26,864); // Tokyo to Melbourne
            routeCounter++;
        }
        if ((27 < maxCities) && (32 < maxCities)) {
            cities[27].addRoute(32,576); // Mexico City to New York
            routeCounter++;
            cities[32].addRoute(27,576); // New York to Mexico City
            routeCounter++;
        }
        if ((27 < maxCities) && (39 < maxCities)) {
            cities[27].addRoute(39,669.5); // Mexico City to Santiago
            routeCounter++;
            cities[39].addRoute(27,669.5); // Santiago to Mexico City
            routeCounter++;
        }
        if ((27 < maxCities) && (47 < maxCities)) {
            cities[27].addRoute(47,978.5); // Mexico City to Vancouver
            routeCounter++;
            cities[47].addRoute(27,978.5); // Vancouver to Mexico City
            routeCounter++;
        }
        if ((27 < maxCities) && (50 < maxCities)) {
            cities[27].addRoute(50,979.2); // Mexico City to Zurich
            routeCounter++;
            cities[50].addRoute(27,979.2); // Zurich to Mexico City
            routeCounter++;
        }
        if ((28 < maxCities) && (32 < maxCities)) {
            cities[28].addRoute(32,978.5); // Milan to New York
            routeCounter++;
            cities[32].addRoute(28,978.5); // New York to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (33 < maxCities)) {
            cities[28].addRoute(33,192); // Milan to Nice
            routeCounter++;
            cities[33].addRoute(28,192); // Nice to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (34 < maxCities)) {
            cities[28].addRoute(34,432); // Milan to Oslo
            routeCounter++;
            cities[34].addRoute(28,432); // Oslo to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (48 < maxCities)) {
            cities[28].addRoute(48,72.1); // Milan to Venice
            routeCounter++;
            cities[48].addRoute(28,72.1); // Venice to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (50 < maxCities)) {
            cities[28].addRoute(50,86.4); // Milan to Zurich
            routeCounter++;
            cities[50].addRoute(28,86.4); // Zurich to Milan
            routeCounter++;
        }
        if ((29 < maxCities) && (5 < maxCities)) {
            cities[29].addRoute(5,463.5); // Moscow to Berlin
            routeCounter++;
            cities[5].addRoute(29,463.5); // Berlin to Moscow
            routeCounter++;
        }
        if ((30 < maxCities) && (31 < maxCities)) {
            cities[30].addRoute(31,566.5); // Mumbai to Nairobi
            routeCounter++;
            cities[31].addRoute(30,566.5); // Nairobi to Mumbai
            routeCounter++;
        }
        if ((30 < maxCities) && (41 < maxCities)) {
            cities[30].addRoute(41,597.4); // Mumbai to Shanghai
            routeCounter++;
            cities[41].addRoute(30,597.4); // Shanghai to Mumbai
            routeCounter++;
        }
        if ((30 < maxCities) && (43 < maxCities)) {
            cities[30].addRoute(43,940.8); // Mumbai to Sydney
            routeCounter++;
            cities[43].addRoute(30,940.8); // Sydney to Mumbai
            routeCounter++;
        }
        if ((31 < maxCities) && (35 < maxCities)) {
            cities[31].addRoute(35,768); // Nairobi to Paris
            routeCounter++;
            cities[35].addRoute(31,768); // Paris to Nairobi
            routeCounter++;
        }
        if ((31 < maxCities) && (38 < maxCities)) {
            cities[31].addRoute(38,432); // Nairobi to Rome
            routeCounter++;
            cities[38].addRoute(31,432); // Rome to Nairobi
            routeCounter++;
        }
        if ((31 < maxCities) && (42 < maxCities)) {
            cities[31].addRoute(42,960); // Nairobi to Singapore
            routeCounter++;
            cities[42].addRoute(31,960); // Singapore to Nairobi
            routeCounter++;
        }
        if ((32 < maxCities) && (34 < maxCities)) {
            cities[32].addRoute(34,576); // New York to Oslo
            routeCounter++;
            cities[34].addRoute(32,576); // Oslo to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (35 < maxCities)) {
            cities[32].addRoute(35,669.5); // New York to Paris
            routeCounter++;
            cities[35].addRoute(32,669.5); // Paris to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (38 < maxCities)) {
            cities[32].addRoute(38,672); // New York to Rome
            routeCounter++;
            cities[38].addRoute(32,672); // Rome to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (40 < maxCities)) {
            cities[32].addRoute(40,1075.2); // New York to Seoul
            routeCounter++;
            cities[40].addRoute(32,1075.2); // Seoul to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (45 < maxCities)) {
            cities[32].addRoute(45,1104); // New York to Tokyo
            routeCounter++;
            cities[45].addRoute(32,1104); // Tokyo to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (47 < maxCities)) {
            cities[32].addRoute(47,463.5); // New York to Vancouver
            routeCounter++;
            cities[47].addRoute(32,463.5); // Vancouver to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (48 < maxCities)) {
            cities[32].addRoute(48,912); // New York to Venice
            routeCounter++;
            cities[48].addRoute(32,912); // Venice to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (49 < maxCities)) {
            cities[32].addRoute(49,816); // New York to Vienna
            routeCounter++;
            cities[49].addRoute(32,816); // Vienna to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (50 < maxCities)) {
            cities[32].addRoute(50,432); // New York to Zurich
            routeCounter++;
            cities[50].addRoute(32,432); // Zurich to New York
            routeCounter++;
        }
        if ((33 < maxCities) && (35 < maxCities)) {
            cities[33].addRoute(35,103); // Nice to Paris
            routeCounter++;
            cities[35].addRoute(33,103); // Paris to Nice
            routeCounter++;
        }
        if ((34 < maxCities) && (35 < maxCities)) {
            cities[34].addRoute(35,257.5); // Oslo to Paris
            routeCounter++;
            cities[35].addRoute(34,257.5); // Paris to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (38 < maxCities)) {
            cities[34].addRoute(38,463.5); // Oslo to Rome
            routeCounter++;
            cities[38].addRoute(34,463.5); // Rome to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (45 < maxCities)) {
            cities[34].addRoute(45,927); // Oslo to Tokyo
            routeCounter++;
            cities[45].addRoute(34,927); // Tokyo to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (49 < maxCities)) {
            cities[34].addRoute(49,329.6); // Oslo to Vienna
            routeCounter++;
            cities[49].addRoute(34,329.6); // Vienna to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (50 < maxCities)) {
            cities[34].addRoute(50,297.6); // Oslo to Zurich
            routeCounter++;
            cities[50].addRoute(34,297.6); // Zurich to Oslo
            routeCounter++;
        }
        if ((35 < maxCities) && (37 < maxCities)) {
            cities[35].addRoute(37,624); // Paris to Quebec
            routeCounter++;
            cities[37].addRoute(35,624); // Quebec to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (40 < maxCities)) {
            cities[35].addRoute(40,1200); // Paris to Seoul
            routeCounter++;
            cities[40].addRoute(35,1200); // Seoul to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (41 < maxCities)) {
            cities[35].addRoute(41,1104); // Paris to Shanghai
            routeCounter++;
            cities[41].addRoute(35,1104); // Shanghai to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (42 < maxCities)) {
            cities[35].addRoute(42,960); // Paris to Singapore
            routeCounter++;
            cities[42].addRoute(35,960); // Singapore to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (44 < maxCities)) {
            cities[35].addRoute(44,288); // Paris to Tangiers
            routeCounter++;
            cities[44].addRoute(35,288); // Tangiers to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (45 < maxCities)) {
            cities[35].addRoute(45,1248); // Paris to Tokyo
            routeCounter++;
            cities[45].addRoute(35,1248); // Tokyo to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (48 < maxCities)) {
            cities[35].addRoute(48,192); // Paris to Venice
            routeCounter++;
            cities[48].addRoute(35,192); // Venice to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (49 < maxCities)) {
            cities[35].addRoute(49,172.8); // Paris to Vienna
            routeCounter++;
            cities[49].addRoute(35,172.8); // Vienna to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (50 < maxCities)) {
            cities[35].addRoute(50,134.4); // Paris to Zurich
            routeCounter++;
            cities[50].addRoute(35,134.4); // Zurich to Paris
            routeCounter++;
        }
        if ((36 < maxCities) && (42 < maxCities)) {
            cities[36].addRoute(42,528); // Perth to Singapore
            routeCounter++;
            cities[42].addRoute(36,528); // Singapore to Perth
            routeCounter++;
        }
        if ((36 < maxCities) && (43 < maxCities)) {
            cities[36].addRoute(43,528); // Perth to Sydney
            routeCounter++;
            cities[43].addRoute(36,528); // Sydney to Perth
            routeCounter++;
        }
        if ((37 < maxCities) && (47 < maxCities)) {
            cities[37].addRoute(47,515); // Quebec to Vancouver
            routeCounter++;
            cities[47].addRoute(37,515); // Vancouver to Quebec
            routeCounter++;
        }
        if ((38 < maxCities) && (39 < maxCities)) {
            cities[38].addRoute(39,1019.7); // Rome to Santiago
            routeCounter++;
            cities[39].addRoute(38,1019.7); // Santiago to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (42 < maxCities)) {
            cities[38].addRoute(42,1122.7); // Rome to Singapore
            routeCounter++;
            cities[42].addRoute(38,1122.7); // Singapore to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (45 < maxCities)) {
            cities[38].addRoute(45,1152); // Rome to Tokyo
            routeCounter++;
            cities[45].addRoute(38,1152); // Tokyo to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (48 < maxCities)) {
            cities[38].addRoute(48,154.5); // Rome to Venice
            routeCounter++;
            cities[48].addRoute(38,154.5); // Venice to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (49 < maxCities)) {
            cities[38].addRoute(49,257.5); // Rome to Vienna
            routeCounter++;
            cities[49].addRoute(38,257.5); // Vienna to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (50 < maxCities)) {
            cities[38].addRoute(50,240); // Rome to Zurich
            routeCounter++;
            cities[50].addRoute(38,240); // Zurich to Rome
            routeCounter++;
        }
        if ((40 < maxCities) && (41 < maxCities)) {
            cities[40].addRoute(41,384); // Seoul to Shanghai
            routeCounter++;
            cities[41].addRoute(40,384); // Shanghai to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (42 < maxCities)) {
            cities[40].addRoute(42,480); // Seoul to Singapore
            routeCounter++;
            cities[42].addRoute(40,480); // Singapore to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (43 < maxCities)) {
            cities[40].addRoute(43,940.8); // Seoul to Sydney
            routeCounter++;
            cities[43].addRoute(40,940.8); // Sydney to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (46 < maxCities)) {
            cities[40].addRoute(46,480); // Seoul to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(40,480); // Ulan Bataar to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (47 < maxCities)) {
            cities[40].addRoute(47,618); // Seoul to Vancouver
            routeCounter++;
            cities[47].addRoute(40,618); // Vancouver to Seoul
            routeCounter++;
        }
        if ((41 < maxCities) && (42 < maxCities)) {
            cities[41].addRoute(42,240); // Shanghai to Singapore
            routeCounter++;
            cities[42].addRoute(41,240); // Singapore to Shanghai
            routeCounter++;
        }
        if ((41 < maxCities) && (43 < maxCities)) {
            cities[41].addRoute(43,816); // Shanghai to Sydney
            routeCounter++;
            cities[43].addRoute(41,816); // Sydney to Shanghai
            routeCounter++;
        }
        if ((41 < maxCities) && (50 < maxCities)) {
            cities[41].addRoute(50,1008); // Shanghai to Zurich
            routeCounter++;
            cities[50].addRoute(41,1008); // Zurich to Shanghai
            routeCounter++;
        }
        if ((42 < maxCities) && (43 < maxCities)) {
            cities[42].addRoute(43,768); // Singapore to Sydney
            routeCounter++;
            cities[43].addRoute(42,768); // Sydney to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (45 < maxCities)) {
            cities[42].addRoute(45,480); // Singapore to Tokyo
            routeCounter++;
            cities[45].addRoute(42,480); // Tokyo to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (46 < maxCities)) {
            cities[42].addRoute(46,384); // Singapore to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(42,384); // Ulan Bataar to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (49 < maxCities)) {
            cities[42].addRoute(49,864); // Singapore to Vienna
            routeCounter++;
            cities[49].addRoute(42,864); // Vienna to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (50 < maxCities)) {
            cities[42].addRoute(50,912); // Singapore to Zurich
            routeCounter++;
            cities[50].addRoute(42,912); // Zurich to Singapore
            routeCounter++;
        }
        if ((43 < maxCities) && (45 < maxCities)) {
            cities[43].addRoute(45,927); // Sydney to Tokyo
            routeCounter++;
            cities[45].addRoute(43,927); // Tokyo to Sydney
            routeCounter++;
        }
        if ((44 < maxCities) && (50 < maxCities)) {
            cities[44].addRoute(50,288); // Tangiers to Zurich
            routeCounter++;
            cities[50].addRoute(44,288); // Zurich to Tangiers
            routeCounter++;
        }
        if ((45 < maxCities) && (46 < maxCities)) {
            cities[45].addRoute(46,463.5); // Tokyo to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(45,463.5); // Ulan Bataar to Tokyo
            routeCounter++;
        }
        if ((45 < maxCities) && (47 < maxCities)) {
            cities[45].addRoute(47,875.5); // Tokyo to Vancouver
            routeCounter++;
            cities[47].addRoute(45,875.5); // Vancouver to Tokyo
            routeCounter++;
        }
        if ((45 < maxCities) && (50 < maxCities)) {
            cities[45].addRoute(50,1152); // Tokyo to Zurich
            routeCounter++;
            cities[50].addRoute(45,1152); // Zurich to Tokyo
            routeCounter++;
        }
        if ((48 < maxCities) && (49 < maxCities)) {
            cities[48].addRoute(49,96); // Venice to Vienna
            routeCounter++;
            cities[49].addRoute(48,96); // Vienna to Venice
            routeCounter++;
        }
        if ((49 < maxCities) && (50 < maxCities)) {
            cities[49].addRoute(50,96); // Vienna to Zurich
            routeCounter++;
            cities[50].addRoute(49,96); // Zurich to Vienna
            routeCounter++;
        }
   
    }    
}



