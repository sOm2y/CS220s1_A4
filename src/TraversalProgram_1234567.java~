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
                    break;
                case 2:
                    System.out.println("*** DFS with cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 3:
                    System.out.println("*** DFS with path optimisation ***");
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 4:
                    System.out.println("*** DFS with path optimisation and cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 5:
                    System.out.println("*** Plain BFS ***");
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 6:
                    System.out.println("*** BFS with cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 7:
                    System.out.println("*** BFS with path optimisation ***");
                    // PUT YOUR METHOD CALL HERE
                    break;
                case 8:
                    System.out.println("*** BFS with path optimisation and cheapest edge first ***");
                    // PUT YOUR METHOD CALL HERE
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
        // Prices individualised for AUID 1234567.
        // Submit your assignment with these values.
        // Add cities to the cities array 
        // for AUID 1234567 
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
        // for AUID 1234567 

        routeCounter = 0;
        if ((0 < maxCities) && (1 < maxCities)) {
            cities[0].addRoute(1,297); // Auckland to Adelaide
            routeCounter++;
            cities[1].addRoute(0,297); // Adelaide to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (2 < maxCities)) {
            cities[0].addRoute(2,178.2); // Auckland to Apia
            routeCounter++;
            cities[2].addRoute(0,178.2); // Apia to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (6 < maxCities)) {
            cities[0].addRoute(6,1200); // Auckland to Buenos Aires
            routeCounter++;
            cities[6].addRoute(0,1200); // Buenos Aires to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (17 < maxCities)) {
            cities[0].addRoute(17,693); // Auckland to Honolulu
            routeCounter++;
            cities[17].addRoute(0,693); // Honolulu to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (24 < maxCities)) {
            cities[0].addRoute(24,1138.5); // Auckland to Los Angeles
            routeCounter++;
            cities[24].addRoute(0,1138.5); // Los Angeles to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (36 < maxCities)) {
            cities[0].addRoute(36,534.6); // Auckland to Perth
            routeCounter++;
            cities[36].addRoute(0,534.6); // Perth to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (39 < maxCities)) {
            cities[0].addRoute(39,1089); // Auckland to Santiago
            routeCounter++;
            cities[39].addRoute(0,1089); // Santiago to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (40 < maxCities)) {
            cities[0].addRoute(40,940.5); // Auckland to Seoul
            routeCounter++;
            cities[40].addRoute(0,940.5); // Seoul to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (42 < maxCities)) {
            cities[0].addRoute(42,990); // Auckland to Singapore
            routeCounter++;
            cities[42].addRoute(0,990); // Singapore to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (41 < maxCities)) {
            cities[0].addRoute(41,1089); // Auckland to Shanghai
            routeCounter++;
            cities[41].addRoute(0,1089); // Shanghai to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (43 < maxCities)) {
            cities[0].addRoute(43,230); // Auckland to Sydney
            routeCounter++;
            cities[43].addRoute(0,230); // Sydney to Auckland
            routeCounter++;
        }
        if ((0 < maxCities) && (45 < maxCities)) {
            cities[0].addRoute(45,891); // Auckland to Tokyo
            routeCounter++;
            cities[45].addRoute(0,891); // Tokyo to Auckland
            routeCounter++;
        }
        if ((1 < maxCities) && (26 < maxCities)) {
            cities[1].addRoute(26,100); // Adelaide to Melbourne
            routeCounter++;
            cities[26].addRoute(1,100); // Melbourne to Adelaide
            routeCounter++;
        }
        if ((1 < maxCities) && (42 < maxCities)) {
            cities[1].addRoute(42,800); // Adelaide to Singapore
            routeCounter++;
            cities[42].addRoute(1,800); // Singapore to Adelaide
            routeCounter++;
        }
        if ((1 < maxCities) && (43 < maxCities)) {
            cities[1].addRoute(43,150); // Adelaide to Sydney
            routeCounter++;
            cities[43].addRoute(1,150); // Sydney to Adelaide
            routeCounter++;
        }
        if ((1 < maxCities) && (36 < maxCities)) {
            cities[1].addRoute(36,250); // Adelaide to Perth
            routeCounter++;
            cities[36].addRoute(1,250); // Perth to Adelaide
            routeCounter++;
        }
        if ((2 < maxCities) && (43 < maxCities)) {
            cities[2].addRoute(43,200); // Apia to Sydney
            routeCounter++;
            cities[43].addRoute(2,200); // Sydney to Apia
            routeCounter++;
        }
        if ((2 < maxCities) && (26 < maxCities)) {
            cities[2].addRoute(26,217.8); // Apia to Melbourne
            routeCounter++;
            cities[26].addRoute(2,217.8); // Melbourne to Apia
            routeCounter++;
        }
        if ((3 < maxCities) && (42 < maxCities)) {
            cities[3].addRoute(42,891); // Athens to Singapore
            routeCounter++;
            cities[42].addRoute(3,891); // Singapore to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (5 < maxCities)) {
            cities[3].addRoute(5,148.5); // Athens to Berlin
            routeCounter++;
            cities[5].addRoute(3,148.5); // Berlin to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (7 < maxCities)) {
            cities[3].addRoute(7,247.5); // Athens to Cairo
            routeCounter++;
            cities[7].addRoute(3,247.5); // Cairo to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (11 < maxCities)) {
            cities[3].addRoute(11,280); // Athens to Dubai
            routeCounter++;
            cities[11].addRoute(3,280); // Dubai to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (13 < maxCities)) {
            cities[3].addRoute(13,148.5); // Athens to Frankfurt
            routeCounter++;
            cities[13].addRoute(3,148.5); // Frankfurt to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (20 < maxCities)) {
            cities[3].addRoute(20,247.5); // Athens to Jerusalem
            routeCounter++;
            cities[20].addRoute(3,247.5); // Jerusalem to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (23 < maxCities)) {
            cities[3].addRoute(23,247.5); // Athens to London
            routeCounter++;
            cities[23].addRoute(3,247.5); // London to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (28 < maxCities)) {
            cities[3].addRoute(28,99); // Athens to Milan
            routeCounter++;
            cities[28].addRoute(3,99); // Milan to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (29 < maxCities)) {
            cities[3].addRoute(29,297); // Athens to Moscow
            routeCounter++;
            cities[29].addRoute(3,297); // Moscow to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (32 < maxCities)) {
            cities[3].addRoute(32,1089); // Athens to New York
            routeCounter++;
            cities[32].addRoute(3,1089); // New York to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (35 < maxCities)) {
            cities[3].addRoute(35,198); // Athens to Paris
            routeCounter++;
            cities[35].addRoute(3,198); // Paris to Athens
            routeCounter++;
        }
        if ((3 < maxCities) && (49 < maxCities)) {
            cities[3].addRoute(49,138.6); // Athens to Vienna
            routeCounter++;
            cities[49].addRoute(3,138.6); // Vienna to Athens
            routeCounter++;
        }
        if ((4 < maxCities) && (5 < maxCities)) {
            cities[4].addRoute(5,1000); // Beijing to Berlin
            routeCounter++;
            cities[5].addRoute(4,1000); // Berlin to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (9 < maxCities)) {
            cities[4].addRoute(9,200); // Beijing to Chongqing
            routeCounter++;
            cities[9].addRoute(4,200); // Chongqing to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (11 < maxCities)) {
            cities[4].addRoute(11,950); // Beijing to Dubai
            routeCounter++;
            cities[11].addRoute(4,950); // Dubai to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (14 < maxCities)) {
            cities[4].addRoute(14,1050); // Beijing to Geneva
            routeCounter++;
            cities[14].addRoute(4,1050); // Geneva to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (15 < maxCities)) {
            cities[4].addRoute(15,150); // Beijing to Guangdong
            routeCounter++;
            cities[15].addRoute(4,150); // Guangdong to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (16 < maxCities)) {
            cities[4].addRoute(16,160); // Beijing to Hong Kong
            routeCounter++;
            cities[16].addRoute(4,160); // Hong Kong to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (17 < maxCities)) {
            cities[4].addRoute(17,650); // Beijing to Honolulu
            routeCounter++;
            cities[17].addRoute(4,650); // Honolulu to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (19 < maxCities)) {
            cities[4].addRoute(19,346.5); // Beijing to Jakarta
            routeCounter++;
            cities[19].addRoute(4,346.5); // Jakarta to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (22 < maxCities)) {
            cities[4].addRoute(22,400); // Beijing to Kolkata
            routeCounter++;
            cities[22].addRoute(4,400); // Kolkata to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (23 < maxCities)) {
            cities[4].addRoute(23,1050); // Beijing to London
            routeCounter++;
            cities[23].addRoute(4,1050); // London to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (24 < maxCities)) {
            cities[4].addRoute(24,1250); // Beijing to Los Angeles
            routeCounter++;
            cities[24].addRoute(4,1250); // Los Angeles to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (25 < maxCities)) {
            cities[4].addRoute(25,198); // Beijing to Manila
            routeCounter++;
            cities[25].addRoute(4,198); // Manila to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (26 < maxCities)) {
            cities[4].addRoute(26,650); // Beijing to Melbourne
            routeCounter++;
            cities[26].addRoute(4,650); // Melbourne to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (27 < maxCities)) {
            cities[4].addRoute(27,1300); // Beijing to Mexico City
            routeCounter++;
            cities[27].addRoute(4,1300); // Mexico City to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (28 < maxCities)) {
            cities[4].addRoute(28,1200); // Beijing to Milan
            routeCounter++;
            cities[28].addRoute(4,1200); // Milan to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (29 < maxCities)) {
            cities[4].addRoute(29,1000); // Beijing to Moscow
            routeCounter++;
            cities[29].addRoute(4,1000); // Moscow to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (30 < maxCities)) {
            cities[4].addRoute(30,450); // Beijing to Mumbai
            routeCounter++;
            cities[30].addRoute(4,450); // Mumbai to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (32 < maxCities)) {
            cities[4].addRoute(32,1200); // Beijing to New York
            routeCounter++;
            cities[32].addRoute(4,1200); // New York to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (34 < maxCities)) {
            cities[4].addRoute(34,1000); // Beijing to Oslo
            routeCounter++;
            cities[34].addRoute(4,1000); // Oslo to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (35 < maxCities)) {
            cities[4].addRoute(35,1089); // Beijing to Paris
            routeCounter++;
            cities[35].addRoute(4,1089); // Paris to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (38 < maxCities)) {
            cities[4].addRoute(38,1150); // Beijing to Rome
            routeCounter++;
            cities[38].addRoute(4,1150); // Rome to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (32 < maxCities)) {
            cities[4].addRoute(32,1200); // Beijing to New York
            routeCounter++;
            cities[32].addRoute(4,1200); // New York to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (41 < maxCities)) {
            cities[4].addRoute(41,200); // Beijing to Shanghai
            routeCounter++;
            cities[41].addRoute(4,200); // Shanghai to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (42 < maxCities)) {
            cities[4].addRoute(42,350); // Beijing to Singapore
            routeCounter++;
            cities[42].addRoute(4,350); // Singapore to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (45 < maxCities)) {
            cities[4].addRoute(45,200); // Beijing to Tokyo
            routeCounter++;
            cities[45].addRoute(4,200); // Tokyo to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (46 < maxCities)) {
            cities[4].addRoute(46,250); // Beijing to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(4,250); // Ulan Bataar to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (47 < maxCities)) {
            cities[4].addRoute(47,891); // Beijing to Vancouver
            routeCounter++;
            cities[47].addRoute(4,891); // Vancouver to Beijing
            routeCounter++;
        }
        if ((4 < maxCities) && (50 < maxCities)) {
            cities[4].addRoute(50,1300); // Beijing to Zurich
            routeCounter++;
            cities[50].addRoute(4,1300); // Zurich to Beijing
            routeCounter++;
        }
        if ((5 < maxCities) && (13 < maxCities)) {
            cities[5].addRoute(13,100); // Berlin to Frankfurt
            routeCounter++;
            cities[13].addRoute(5,100); // Frankfurt to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (14 < maxCities)) {
            cities[5].addRoute(14,120); // Berlin to Geneva
            routeCounter++;
            cities[14].addRoute(5,120); // Geneva to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (18 < maxCities)) {
            cities[5].addRoute(18,220); // Berlin to Istanbul
            routeCounter++;
            cities[18].addRoute(5,220); // Istanbul to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (20 < maxCities)) {
            cities[5].addRoute(20,250); // Berlin to Jerusalem
            routeCounter++;
            cities[20].addRoute(5,250); // Jerusalem to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (23 < maxCities)) {
            cities[5].addRoute(23,150); // Berlin to London
            routeCounter++;
            cities[23].addRoute(5,150); // London to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (28 < maxCities)) {
            cities[5].addRoute(28,250); // Berlin to Milan
            routeCounter++;
            cities[28].addRoute(5,250); // Milan to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (29 < maxCities)) {
            cities[5].addRoute(29,350); // Berlin to Moscow
            routeCounter++;
            cities[29].addRoute(5,350); // Moscow to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (31 < maxCities)) {
            cities[5].addRoute(31,792); // Berlin to Nairobi
            routeCounter++;
            cities[31].addRoute(5,792); // Nairobi to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (32 < maxCities)) {
            cities[5].addRoute(32,700); // Berlin to New York
            routeCounter++;
            cities[32].addRoute(5,700); // New York to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (34 < maxCities)) {
            cities[5].addRoute(34,120); // Berlin to Oslo
            routeCounter++;
            cities[34].addRoute(5,120); // Oslo to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (35 < maxCities)) {
            cities[5].addRoute(35,148.5); // Berlin to Paris
            routeCounter++;
            cities[35].addRoute(5,148.5); // Paris to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (38 < maxCities)) {
            cities[5].addRoute(38,400); // Berlin to Rome
            routeCounter++;
            cities[38].addRoute(5,400); // Rome to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (45 < maxCities)) {
            cities[5].addRoute(45,1500); // Berlin to Tokyo
            routeCounter++;
            cities[45].addRoute(5,1500); // Tokyo to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (49 < maxCities)) {
            cities[5].addRoute(49,300); // Berlin to Vienna
            routeCounter++;
            cities[49].addRoute(5,300); // Vienna to Berlin
            routeCounter++;
        }
        if ((5 < maxCities) && (50 < maxCities)) {
            cities[5].addRoute(50,200); // Berlin to Zurich
            routeCounter++;
            cities[50].addRoute(5,200); // Zurich to Berlin
            routeCounter++;
        }
        if ((6 < maxCities) && (13 < maxCities)) {
            cities[6].addRoute(13,1485); // Buenos Aires to Frankfurt
            routeCounter++;
            cities[13].addRoute(6,1485); // Frankfurt to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (14 < maxCities)) {
            cities[6].addRoute(14,1386); // Buenos Aires to Geneva
            routeCounter++;
            cities[14].addRoute(6,1386); // Geneva to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (17 < maxCities)) {
            cities[6].addRoute(17,1188); // Buenos Aires to Honolulu
            routeCounter++;
            cities[17].addRoute(6,1188); // Honolulu to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (21 < maxCities)) {
            cities[6].addRoute(21,990); // Buenos Aires to Johannesburg
            routeCounter++;
            cities[21].addRoute(6,990); // Johannesburg to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (23 < maxCities)) {
            cities[6].addRoute(23,1435.5); // Buenos Aires to London
            routeCounter++;
            cities[23].addRoute(6,1435.5); // London to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (24 < maxCities)) {
            cities[6].addRoute(24,990); // Buenos Aires to Los Angeles
            routeCounter++;
            cities[24].addRoute(6,990); // Los Angeles to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (27 < maxCities)) {
            cities[6].addRoute(27,990); // Buenos Aires to Mexico City
            routeCounter++;
            cities[27].addRoute(6,990); // Mexico City to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (32 < maxCities)) {
            cities[6].addRoute(32,1386); // Buenos Aires to New York
            routeCounter++;
            cities[32].addRoute(6,1386); // New York to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (35 < maxCities)) {
            cities[6].addRoute(35,1386); // Buenos Aires to Paris
            routeCounter++;
            cities[35].addRoute(6,1386); // Paris to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (37 < maxCities)) {
            cities[6].addRoute(37,1500); // Buenos Aires to Quebec
            routeCounter++;
            cities[37].addRoute(6,1500); // Quebec to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (38 < maxCities)) {
            cities[6].addRoute(38,1435.5); // Buenos Aires to Rome
            routeCounter++;
            cities[38].addRoute(6,1435.5); // Rome to Buenos Aires
            routeCounter++;
        }
        if ((6 < maxCities) && (39 < maxCities)) {
            cities[6].addRoute(39,445.5); // Buenos Aires to Santiago
            routeCounter++;
            cities[39].addRoute(6,445.5); // Santiago to Buenos Aires
            routeCounter++;
        }
        if ((7 < maxCities) && (11 < maxCities)) {
            cities[7].addRoute(11,250); // Cairo to Dubai
            routeCounter++;
            cities[11].addRoute(7,250); // Dubai to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (13 < maxCities)) {
            cities[7].addRoute(13,250); // Cairo to Frankfurt
            routeCounter++;
            cities[13].addRoute(7,250); // Frankfurt to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (14 < maxCities)) {
            cities[7].addRoute(14,350); // Cairo to Geneva
            routeCounter++;
            cities[14].addRoute(7,350); // Geneva to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (16 < maxCities)) {
            cities[7].addRoute(16,1350); // Cairo to Hong Kong
            routeCounter++;
            cities[16].addRoute(7,1350); // Hong Kong to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (18 < maxCities)) {
            cities[7].addRoute(18,200); // Cairo to Istanbul
            routeCounter++;
            cities[18].addRoute(7,200); // Istanbul to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (20 < maxCities)) {
            cities[7].addRoute(20,400); // Cairo to Jerusalem
            routeCounter++;
            cities[20].addRoute(7,400); // Jerusalem to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (21 < maxCities)) {
            cities[7].addRoute(21,900); // Cairo to Johannesburg
            routeCounter++;
            cities[21].addRoute(7,900); // Johannesburg to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (23 < maxCities)) {
            cities[7].addRoute(23,300); // Cairo to London
            routeCounter++;
            cities[23].addRoute(7,300); // London to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (28 < maxCities)) {
            cities[7].addRoute(28,300); // Cairo to Milan
            routeCounter++;
            cities[28].addRoute(7,300); // Milan to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (31 < maxCities)) {
            cities[7].addRoute(31,350); // Cairo to Nairobi
            routeCounter++;
            cities[31].addRoute(7,350); // Nairobi to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (32 < maxCities)) {
            cities[7].addRoute(32,1500); // Cairo to New York
            routeCounter++;
            cities[32].addRoute(7,1500); // New York to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (35 < maxCities)) {
            cities[7].addRoute(35,500); // Cairo to Paris
            routeCounter++;
            cities[35].addRoute(7,500); // Paris to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (38 < maxCities)) {
            cities[7].addRoute(38,300); // Cairo to Rome
            routeCounter++;
            cities[38].addRoute(7,300); // Rome to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (44 < maxCities)) {
            cities[7].addRoute(44,350); // Cairo to Tangiers
            routeCounter++;
            cities[44].addRoute(7,350); // Tangiers to Cairo
            routeCounter++;
        }
        if ((7 < maxCities) && (49 < maxCities)) {
            cities[7].addRoute(49,400); // Cairo to Vienna
            routeCounter++;
            cities[49].addRoute(7,400); // Vienna to Cairo
            routeCounter++;
        }
        if ((8 < maxCities) && (27 < maxCities)) {
            cities[8].addRoute(27,150); // Cancun to Mexico City
            routeCounter++;
            cities[27].addRoute(8,150); // Mexico City to Cancun
            routeCounter++;
        }
        if ((8 < maxCities) && (32 < maxCities)) {
            cities[8].addRoute(32,400); // Cancun to New York
            routeCounter++;
            cities[32].addRoute(8,400); // New York to Cancun
            routeCounter++;
        }
        if ((8 < maxCities) && (24 < maxCities)) {
            cities[8].addRoute(24,350); // Cancun to Los Angeles
            routeCounter++;
            cities[24].addRoute(8,350); // Los Angeles to Cancun
            routeCounter++;
        }
        if ((8 < maxCities) && (47 < maxCities)) {
            cities[8].addRoute(47,500); // Cancun to Vancouver
            routeCounter++;
            cities[47].addRoute(8,500); // Vancouver to Cancun
            routeCounter++;
        }
        if ((9 < maxCities) && (11 < maxCities)) {
            cities[9].addRoute(11,1200); // Chongqing to Dubai
            routeCounter++;
            cities[11].addRoute(9,1200); // Dubai to Chongqing
            routeCounter++;
        }
        if ((9 < maxCities) && (15 < maxCities)) {
            cities[9].addRoute(15,150); // Chongqing to Guangdong
            routeCounter++;
            cities[15].addRoute(9,150); // Guangdong to Chongqing
            routeCounter++;
        }
        if ((9 < maxCities) && (41 < maxCities)) {
            cities[9].addRoute(41,200); // Chongqing to Shanghai
            routeCounter++;
            cities[41].addRoute(9,200); // Shanghai to Chongqing
            routeCounter++;
        }
        if ((10 < maxCities) && (11 < maxCities)) {
            cities[10].addRoute(11,1200); // Denpasar to Dubai
            routeCounter++;
            cities[11].addRoute(10,1200); // Dubai to Denpasar
            routeCounter++;
        }
        if ((10 < maxCities) && (42 < maxCities)) {
            cities[10].addRoute(42,400); // Denpasar to Singapore
            routeCounter++;
            cities[42].addRoute(10,400); // Singapore to Denpasar
            routeCounter++;
        }
        if ((10 < maxCities) && (19 < maxCities)) {
            cities[10].addRoute(19,198); // Denpasar to Jakarta
            routeCounter++;
            cities[19].addRoute(10,198); // Jakarta to Denpasar
            routeCounter++;
        }
        if ((11 < maxCities) && (13 < maxCities)) {
            cities[11].addRoute(13,891); // Dubai to Frankfurt
            routeCounter++;
            cities[13].addRoute(11,891); // Frankfurt to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (14 < maxCities)) {
            cities[11].addRoute(14,792); // Dubai to Geneva
            routeCounter++;
            cities[14].addRoute(11,792); // Geneva to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (16 < maxCities)) {
            cities[11].addRoute(16,990); // Dubai to Hong Kong
            routeCounter++;
            cities[16].addRoute(11,990); // Hong Kong to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (18 < maxCities)) {
            cities[11].addRoute(18,247.5); // Dubai to Istanbul
            routeCounter++;
            cities[18].addRoute(11,247.5); // Istanbul to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (19 < maxCities)) {
            cities[11].addRoute(19,990); // Dubai to Jakarta
            routeCounter++;
            cities[19].addRoute(11,990); // Jakarta to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (21 < maxCities)) {
            cities[11].addRoute(21,1188); // Dubai to Johannesburg
            routeCounter++;
            cities[21].addRoute(11,1188); // Johannesburg to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (22 < maxCities)) {
            cities[11].addRoute(22,891); // Dubai to Kolkata
            routeCounter++;
            cities[22].addRoute(11,891); // Kolkata to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (23 < maxCities)) {
            cities[11].addRoute(23,940.5); // Dubai to London
            routeCounter++;
            cities[23].addRoute(11,940.5); // London to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (24 < maxCities)) {
            cities[11].addRoute(24,1584); // Dubai to Los Angeles
            routeCounter++;
            cities[24].addRoute(11,1584); // Los Angeles to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (26 < maxCities)) {
            cities[11].addRoute(26,1287); // Dubai to Melbourne
            routeCounter++;
            cities[26].addRoute(11,1287); // Melbourne to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (28 < maxCities)) {
            cities[11].addRoute(28,792); // Dubai to Milan
            routeCounter++;
            cities[28].addRoute(11,792); // Milan to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (29 < maxCities)) {
            cities[11].addRoute(29,792); // Dubai to Moscow
            routeCounter++;
            cities[29].addRoute(11,792); // Moscow to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (30 < maxCities)) {
            cities[11].addRoute(30,800); // Dubai to Mumbai
            routeCounter++;
            cities[30].addRoute(11,800); // Mumbai to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (31 < maxCities)) {
            cities[11].addRoute(31,495); // Dubai to Nairobi
            routeCounter++;
            cities[31].addRoute(11,495); // Nairobi to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (32 < maxCities)) {
            cities[11].addRoute(32,1386); // Dubai to New York
            routeCounter++;
            cities[32].addRoute(11,1386); // New York to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (33 < maxCities)) {
            cities[11].addRoute(33,792); // Dubai to Nice
            routeCounter++;
            cities[33].addRoute(11,792); // Nice to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (34 < maxCities)) {
            cities[11].addRoute(34,891); // Dubai to Oslo
            routeCounter++;
            cities[34].addRoute(11,891); // Oslo to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (35 < maxCities)) {
            cities[11].addRoute(35,841.5); // Dubai to Paris
            routeCounter++;
            cities[35].addRoute(11,841.5); // Paris to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (36 < maxCities)) {
            cities[11].addRoute(36,940.5); // Dubai to Perth
            routeCounter++;
            cities[36].addRoute(11,940.5); // Perth to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (38 < maxCities)) {
            cities[11].addRoute(38,445.5); // Dubai to Rome
            routeCounter++;
            cities[38].addRoute(11,445.5); // Rome to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (42 < maxCities)) {
            cities[11].addRoute(42,891); // Dubai to Singapore
            routeCounter++;
            cities[42].addRoute(11,891); // Singapore to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (43 < maxCities)) {
            cities[11].addRoute(43,1250); // Dubai to Sydney
            routeCounter++;
            cities[43].addRoute(11,1250); // Sydney to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (44 < maxCities)) {
            cities[11].addRoute(44,594); // Dubai to Tangiers
            routeCounter++;
            cities[44].addRoute(11,594); // Tangiers to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (45 < maxCities)) {
            cities[11].addRoute(45,1287); // Dubai to Tokyo
            routeCounter++;
            cities[45].addRoute(11,1287); // Tokyo to Dubai
            routeCounter++;
        }
        if ((11 < maxCities) && (49 < maxCities)) {
            cities[11].addRoute(49,742.5); // Dubai to Vienna
            routeCounter++;
            cities[49].addRoute(11,742.5); // Vienna to Dubai
            routeCounter++;
        }
        if ((12 < maxCities) && (24 < maxCities)) {
            cities[12].addRoute(24,150); // El Paso to Los Angeles
            routeCounter++;
            cities[24].addRoute(12,150); // Los Angeles to El Paso
            routeCounter++;
        }
        if ((12 < maxCities) && (27 < maxCities)) {
            cities[12].addRoute(27,247.5); // El Paso to Mexico City
            routeCounter++;
            cities[27].addRoute(12,247.5); // Mexico City to El Paso
            routeCounter++;
        }
        if ((12 < maxCities) && (32 < maxCities)) {
            cities[12].addRoute(32,346.5); // El Paso to New York
            routeCounter++;
            cities[32].addRoute(12,346.5); // New York to El Paso
            routeCounter++;
        }
        if ((13 < maxCities) && (14 < maxCities)) {
            cities[13].addRoute(14,198); // Frankfurt to Geneva
            routeCounter++;
            cities[14].addRoute(13,198); // Geneva to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (15 < maxCities)) {
            cities[13].addRoute(15,1200); // Frankfurt to Guangdong
            routeCounter++;
            cities[15].addRoute(13,1200); // Guangdong to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (16 < maxCities)) {
            cities[13].addRoute(16,1287); // Frankfurt to Hong Kong
            routeCounter++;
            cities[16].addRoute(13,1287); // Hong Kong to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (18 < maxCities)) {
            cities[13].addRoute(18,200); // Frankfurt to Istanbul
            routeCounter++;
            cities[18].addRoute(13,200); // Istanbul to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (19 < maxCities)) {
            cities[13].addRoute(19,1485); // Frankfurt to Jakarta
            routeCounter++;
            cities[19].addRoute(13,1485); // Jakarta to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (20 < maxCities)) {
            cities[13].addRoute(20,297); // Frankfurt to Jerusalem
            routeCounter++;
            cities[20].addRoute(13,297); // Jerusalem to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (21 < maxCities)) {
            cities[13].addRoute(21,940.5); // Frankfurt to Johannesburg
            routeCounter++;
            cities[21].addRoute(13,940.5); // Johannesburg to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (22 < maxCities)) {
            cities[13].addRoute(22,1089); // Frankfurt to Kolkata
            routeCounter++;
            cities[22].addRoute(13,1089); // Kolkata to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (23 < maxCities)) {
            cities[13].addRoute(23,198); // Frankfurt to London
            routeCounter++;
            cities[23].addRoute(13,198); // London to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (24 < maxCities)) {
            cities[13].addRoute(24,891); // Frankfurt to Los Angeles
            routeCounter++;
            cities[24].addRoute(13,891); // Los Angeles to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (25 < maxCities)) {
            cities[13].addRoute(25,1287); // Frankfurt to Manila
            routeCounter++;
            cities[25].addRoute(13,1287); // Manila to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (27 < maxCities)) {
            cities[13].addRoute(27,1089); // Frankfurt to Mexico City
            routeCounter++;
            cities[27].addRoute(13,1089); // Mexico City to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (28 < maxCities)) {
            cities[13].addRoute(28,247.5); // Frankfurt to Milan
            routeCounter++;
            cities[28].addRoute(13,247.5); // Milan to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (30 < maxCities)) {
            cities[13].addRoute(30,1000); // Frankfurt to Mumbai
            routeCounter++;
            cities[30].addRoute(13,1000); // Mumbai to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (31 < maxCities)) {
            cities[13].addRoute(31,792); // Frankfurt to Nairobi
            routeCounter++;
            cities[31].addRoute(13,792); // Nairobi to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (32 < maxCities)) {
            cities[13].addRoute(32,594); // Frankfurt to New York
            routeCounter++;
            cities[32].addRoute(13,594); // New York to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (33 < maxCities)) {
            cities[13].addRoute(33,198); // Frankfurt to Nice
            routeCounter++;
            cities[33].addRoute(13,198); // Nice to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (34 < maxCities)) {
            cities[13].addRoute(34,250); // Frankfurt to Oslo
            routeCounter++;
            cities[34].addRoute(13,250); // Oslo to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (35 < maxCities)) {
            cities[13].addRoute(35,148.5); // Frankfurt to Paris
            routeCounter++;
            cities[35].addRoute(13,148.5); // Paris to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (37 < maxCities)) {
            cities[13].addRoute(37,750); // Frankfurt to Quebec
            routeCounter++;
            cities[37].addRoute(13,750); // Quebec to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (38 < maxCities)) {
            cities[13].addRoute(38,247.5); // Frankfurt to Rome
            routeCounter++;
            cities[38].addRoute(13,247.5); // Rome to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (40 < maxCities)) {
            cities[13].addRoute(40,1108.8); // Frankfurt to Seoul
            routeCounter++;
            cities[40].addRoute(13,1108.8); // Seoul to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (41 < maxCities)) {
            cities[13].addRoute(41,1039.5); // Frankfurt to Shanghai
            routeCounter++;
            cities[41].addRoute(13,1039.5); // Shanghai to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (42 < maxCities)) {
            cities[13].addRoute(42,1108.8); // Frankfurt to Singapore
            routeCounter++;
            cities[42].addRoute(13,1108.8); // Singapore to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (44 < maxCities)) {
            cities[13].addRoute(44,297); // Frankfurt to Tangiers
            routeCounter++;
            cities[44].addRoute(13,297); // Tangiers to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (45 < maxCities)) {
            cities[13].addRoute(45,1138.5); // Frankfurt to Tokyo
            routeCounter++;
            cities[45].addRoute(13,1138.5); // Tokyo to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (47 < maxCities)) {
            cities[13].addRoute(47,841.5); // Frankfurt to Vancouver
            routeCounter++;
            cities[47].addRoute(13,841.5); // Vancouver to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (48 < maxCities)) {
            cities[13].addRoute(48,247.5); // Frankfurt to Venice
            routeCounter++;
            cities[48].addRoute(13,247.5); // Venice to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (49 < maxCities)) {
            cities[13].addRoute(49,198); // Frankfurt to Vienna
            routeCounter++;
            cities[49].addRoute(13,198); // Vienna to Frankfurt
            routeCounter++;
        }
        if ((13 < maxCities) && (50 < maxCities)) {
            cities[13].addRoute(50,180); // Frankfurt to Zurich
            routeCounter++;
            cities[50].addRoute(13,180); // Zurich to Frankfurt
            routeCounter++;
        }
        if ((14 < maxCities) && (16 < maxCities)) {
            cities[14].addRoute(16,1200); // Geneva to Hong Kong
            routeCounter++;
            cities[16].addRoute(14,1200); // Hong Kong to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (20 < maxCities)) {
            cities[14].addRoute(20,450); // Geneva to Jerusalem
            routeCounter++;
            cities[20].addRoute(14,450); // Jerusalem to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (23 < maxCities)) {
            cities[14].addRoute(23,450); // Geneva to London
            routeCounter++;
            cities[23].addRoute(14,450); // London to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (28 < maxCities)) {
            cities[14].addRoute(28,120); // Geneva to Milan
            routeCounter++;
            cities[28].addRoute(14,120); // Milan to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (32 < maxCities)) {
            cities[14].addRoute(32,800); // Geneva to New York
            routeCounter++;
            cities[32].addRoute(14,800); // New York to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (34 < maxCities)) {
            cities[14].addRoute(34,300); // Geneva to Oslo
            routeCounter++;
            cities[34].addRoute(14,300); // Oslo to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (35 < maxCities)) {
            cities[14].addRoute(35,99); // Geneva to Paris
            routeCounter++;
            cities[35].addRoute(14,99); // Paris to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (37 < maxCities)) {
            cities[14].addRoute(37,1000); // Geneva to Quebec
            routeCounter++;
            cities[37].addRoute(14,1000); // Quebec to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (38 < maxCities)) {
            cities[14].addRoute(38,100); // Geneva to Rome
            routeCounter++;
            cities[38].addRoute(14,100); // Rome to Geneva
            routeCounter++;
        }
        if ((14 < maxCities) && (49 < maxCities)) {
            cities[14].addRoute(49,100); // Geneva to Vienna
            routeCounter++;
            cities[49].addRoute(14,100); // Vienna to Geneva
            routeCounter++;
        }
        if ((15 < maxCities) && (16 < maxCities)) {
            cities[15].addRoute(16,49.5); // Guangdong to Hong Kong
            routeCounter++;
            cities[16].addRoute(15,49.5); // Hong Kong to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (19 < maxCities)) {
            cities[15].addRoute(19,247.5); // Guangdong to Jakarta
            routeCounter++;
            cities[19].addRoute(15,247.5); // Jakarta to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (22 < maxCities)) {
            cities[15].addRoute(22,247.5); // Guangdong to Kolkata
            routeCounter++;
            cities[22].addRoute(15,247.5); // Kolkata to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (23 < maxCities)) {
            cities[15].addRoute(23,792); // Guangdong to London
            routeCounter++;
            cities[23].addRoute(15,792); // London to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (25 < maxCities)) {
            cities[15].addRoute(25,396); // Guangdong to Manila
            routeCounter++;
            cities[25].addRoute(15,396); // Manila to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (26 < maxCities)) {
            cities[15].addRoute(26,792); // Guangdong to Melbourne
            routeCounter++;
            cities[26].addRoute(15,792); // Melbourne to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (35 < maxCities)) {
            cities[15].addRoute(35,792); // Guangdong to Paris
            routeCounter++;
            cities[35].addRoute(15,792); // Paris to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (36 < maxCities)) {
            cities[15].addRoute(36,693); // Guangdong to Perth
            routeCounter++;
            cities[36].addRoute(15,693); // Perth to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (42 < maxCities)) {
            cities[15].addRoute(42,297); // Guangdong to Singapore
            routeCounter++;
            cities[42].addRoute(15,297); // Singapore to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (43 < maxCities)) {
            cities[15].addRoute(43,850); // Guangdong to Sydney
            routeCounter++;
            cities[43].addRoute(15,850); // Sydney to Guangdong
            routeCounter++;
        }
        if ((15 < maxCities) && (50 < maxCities)) {
            cities[15].addRoute(50,1300); // Guangdong to Zurich
            routeCounter++;
            cities[50].addRoute(15,1300); // Zurich to Guangdong
            routeCounter++;
        }
        if ((16 < maxCities) && (17 < maxCities)) {
            cities[16].addRoute(17,1000); // Hong Kong to Honolulu
            routeCounter++;
            cities[17].addRoute(16,1000); // Honolulu to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (18 < maxCities)) {
            cities[16].addRoute(18,850); // Hong Kong to Istanbul
            routeCounter++;
            cities[18].addRoute(16,850); // Istanbul to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (19 < maxCities)) {
            cities[16].addRoute(19,198); // Hong Kong to Jakarta
            routeCounter++;
            cities[19].addRoute(16,198); // Jakarta to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (22 < maxCities)) {
            cities[16].addRoute(22,250); // Hong Kong to Kolkata
            routeCounter++;
            cities[22].addRoute(16,250); // Kolkata to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (23 < maxCities)) {
            cities[16].addRoute(23,750); // Hong Kong to London
            routeCounter++;
            cities[23].addRoute(16,750); // London to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (25 < maxCities)) {
            cities[16].addRoute(25,247.5); // Hong Kong to Manila
            routeCounter++;
            cities[25].addRoute(16,247.5); // Manila to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (26 < maxCities)) {
            cities[16].addRoute(26,811.8); // Hong Kong to Melbourne
            routeCounter++;
            cities[26].addRoute(16,811.8); // Melbourne to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (35 < maxCities)) {
            cities[16].addRoute(35,811.8); // Hong Kong to Paris
            routeCounter++;
            cities[35].addRoute(16,811.8); // Paris to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (36 < maxCities)) {
            cities[16].addRoute(36,712.8); // Hong Kong to Perth
            routeCounter++;
            cities[36].addRoute(16,712.8); // Perth to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (38 < maxCities)) {
            cities[16].addRoute(38,800); // Hong Kong to Rome
            routeCounter++;
            cities[38].addRoute(16,800); // Rome to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (40 < maxCities)) {
            cities[16].addRoute(40,396); // Hong Kong to Seoul
            routeCounter++;
            cities[40].addRoute(16,396); // Seoul to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (41 < maxCities)) {
            cities[16].addRoute(41,148.5); // Hong Kong to Shanghai
            routeCounter++;
            cities[41].addRoute(16,148.5); // Shanghai to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (42 < maxCities)) {
            cities[16].addRoute(42,356.4); // Hong Kong to Singapore
            routeCounter++;
            cities[42].addRoute(16,356.4); // Singapore to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (43 < maxCities)) {
            cities[16].addRoute(43,890); // Hong Kong to Sydney
            routeCounter++;
            cities[43].addRoute(16,890); // Sydney to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (45 < maxCities)) {
            cities[16].addRoute(45,420); // Hong Kong to Tokyo
            routeCounter++;
            cities[45].addRoute(16,420); // Tokyo to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (46 < maxCities)) {
            cities[16].addRoute(46,663.3); // Hong Kong to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(16,663.3); // Ulan Bataar to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (47 < maxCities)) {
            cities[16].addRoute(47,1188); // Hong Kong to Vancouver
            routeCounter++;
            cities[47].addRoute(16,1188); // Vancouver to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (49 < maxCities)) {
            cities[16].addRoute(49,990); // Hong Kong to Vienna
            routeCounter++;
            cities[49].addRoute(16,990); // Vienna to Hong Kong
            routeCounter++;
        }
        if ((16 < maxCities) && (50 < maxCities)) {
            cities[16].addRoute(50,1290); // Hong Kong to Zurich
            routeCounter++;
            cities[50].addRoute(16,1290); // Zurich to Hong Kong
            routeCounter++;
        }
        if ((17 < maxCities) && (24 < maxCities)) {
            cities[17].addRoute(24,600); // Honolulu to Los Angeles
            routeCounter++;
            cities[24].addRoute(17,600); // Los Angeles to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (27 < maxCities)) {
            cities[17].addRoute(27,643.5); // Honolulu to Mexico City
            routeCounter++;
            cities[27].addRoute(17,643.5); // Mexico City to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (32 < maxCities)) {
            cities[17].addRoute(32,990); // Honolulu to New York
            routeCounter++;
            cities[32].addRoute(17,990); // New York to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (40 < maxCities)) {
            cities[17].addRoute(40,792); // Honolulu to Seoul
            routeCounter++;
            cities[40].addRoute(17,792); // Seoul to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (43 < maxCities)) {
            cities[17].addRoute(43,950); // Honolulu to Sydney
            routeCounter++;
            cities[43].addRoute(17,950); // Sydney to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (45 < maxCities)) {
            cities[17].addRoute(45,750); // Honolulu to Tokyo
            routeCounter++;
            cities[45].addRoute(17,750); // Tokyo to Honolulu
            routeCounter++;
        }
        if ((17 < maxCities) && (47 < maxCities)) {
            cities[17].addRoute(47,841.5); // Honolulu to Vancouver
            routeCounter++;
            cities[47].addRoute(17,841.5); // Vancouver to Honolulu
            routeCounter++;
        }
        if ((18 < maxCities) && (23 < maxCities)) {
            cities[18].addRoute(23,445.5); // Istanbul to London
            routeCounter++;
            cities[23].addRoute(18,445.5); // London to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (28 < maxCities)) {
            cities[18].addRoute(28,297); // Istanbul to Milan
            routeCounter++;
            cities[28].addRoute(18,297); // Milan to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (30 < maxCities)) {
            cities[18].addRoute(30,650); // Istanbul to Mumbai
            routeCounter++;
            cities[30].addRoute(18,650); // Mumbai to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (32 < maxCities)) {
            cities[18].addRoute(32,970.2); // Istanbul to New York
            routeCounter++;
            cities[32].addRoute(18,970.2); // New York to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (34 < maxCities)) {
            cities[18].addRoute(34,550); // Istanbul to Oslo
            routeCounter++;
            cities[34].addRoute(18,550); // Oslo to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (35 < maxCities)) {
            cities[18].addRoute(35,346.5); // Istanbul to Paris
            routeCounter++;
            cities[35].addRoute(18,346.5); // Paris to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (38 < maxCities)) {
            cities[18].addRoute(38,277.2); // Istanbul to Rome
            routeCounter++;
            cities[38].addRoute(18,277.2); // Rome to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (42 < maxCities)) {
            cities[18].addRoute(42,762.3); // Istanbul to Singapore
            routeCounter++;
            cities[42].addRoute(18,762.3); // Singapore to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (44 < maxCities)) {
            cities[18].addRoute(44,465.3); // Istanbul to Tangiers
            routeCounter++;
            cities[44].addRoute(18,465.3); // Tangiers to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (49 < maxCities)) {
            cities[18].addRoute(49,247.5); // Istanbul to Vienna
            routeCounter++;
            cities[49].addRoute(18,247.5); // Vienna to Istanbul
            routeCounter++;
        }
        if ((18 < maxCities) && (50 < maxCities)) {
            cities[18].addRoute(50,300); // Istanbul to Zurich
            routeCounter++;
            cities[50].addRoute(18,300); // Zurich to Istanbul
            routeCounter++;
        }
        if ((19 < maxCities) && (22 < maxCities)) {
            cities[19].addRoute(22,250); // Jakarta to Kolkata
            routeCounter++;
            cities[22].addRoute(19,250); // Kolkata to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (23 < maxCities)) {
            cities[19].addRoute(23,1080); // Jakarta to London
            routeCounter++;
            cities[23].addRoute(19,1080); // London to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (26 < maxCities)) {
            cities[19].addRoute(26,700); // Jakarta to Melbourne
            routeCounter++;
            cities[26].addRoute(19,700); // Melbourne to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (30 < maxCities)) {
            cities[19].addRoute(30,350); // Jakarta to Mumbai
            routeCounter++;
            cities[30].addRoute(19,350); // Mumbai to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (41 < maxCities)) {
            cities[19].addRoute(41,380); // Jakarta to Shanghai
            routeCounter++;
            cities[41].addRoute(19,380); // Shanghai to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (42 < maxCities)) {
            cities[19].addRoute(42,150); // Jakarta to Singapore
            routeCounter++;
            cities[42].addRoute(19,150); // Singapore to Jakarta
            routeCounter++;
        }
        if ((19 < maxCities) && (45 < maxCities)) {
            cities[19].addRoute(45,450); // Jakarta to Tokyo
            routeCounter++;
            cities[45].addRoute(19,450); // Tokyo to Jakarta
            routeCounter++;
        }
        if ((20 < maxCities) && (21 < maxCities)) {
            cities[20].addRoute(21,850); // Jerusalem to Johannesburg
            routeCounter++;
            cities[21].addRoute(20,850); // Johannesburg to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (23 < maxCities)) {
            cities[20].addRoute(23,550); // Jerusalem to London
            routeCounter++;
            cities[23].addRoute(20,550); // London to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (28 < maxCities)) {
            cities[20].addRoute(28,400); // Jerusalem to Milan
            routeCounter++;
            cities[28].addRoute(20,400); // Milan to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (31 < maxCities)) {
            cities[20].addRoute(31,445.5); // Jerusalem to Nairobi
            routeCounter++;
            cities[31].addRoute(20,445.5); // Nairobi to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (32 < maxCities)) {
            cities[20].addRoute(32,1250); // Jerusalem to New York
            routeCounter++;
            cities[32].addRoute(20,1250); // New York to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (33 < maxCities)) {
            cities[20].addRoute(33,450); // Jerusalem to Nice
            routeCounter++;
            cities[33].addRoute(20,450); // Nice to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (34 < maxCities)) {
            cities[20].addRoute(34,600); // Jerusalem to Oslo
            routeCounter++;
            cities[34].addRoute(20,600); // Oslo to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (35 < maxCities)) {
            cities[20].addRoute(35,495); // Jerusalem to Paris
            routeCounter++;
            cities[35].addRoute(20,495); // Paris to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (38 < maxCities)) {
            cities[20].addRoute(38,450); // Jerusalem to Rome
            routeCounter++;
            cities[38].addRoute(20,450); // Rome to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (49 < maxCities)) {
            cities[20].addRoute(49,450); // Jerusalem to Vienna
            routeCounter++;
            cities[49].addRoute(20,450); // Vienna to Jerusalem
            routeCounter++;
        }
        if ((20 < maxCities) && (50 < maxCities)) {
            cities[20].addRoute(50,450); // Jerusalem to Zurich
            routeCounter++;
            cities[50].addRoute(20,450); // Zurich to Jerusalem
            routeCounter++;
        }
        if ((21 < maxCities) && (23 < maxCities)) {
            cities[21].addRoute(23,1100); // Johannesburg to London
            routeCounter++;
            cities[23].addRoute(21,1100); // London to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (31 < maxCities)) {
            cities[21].addRoute(31,554.4); // Johannesburg to Nairobi
            routeCounter++;
            cities[31].addRoute(21,554.4); // Nairobi to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (35 < maxCities)) {
            cities[21].addRoute(35,980.1); // Johannesburg to Paris
            routeCounter++;
            cities[35].addRoute(21,980.1); // Paris to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (36 < maxCities)) {
            cities[21].addRoute(36,980.1); // Johannesburg to Perth
            routeCounter++;
            cities[36].addRoute(21,980.1); // Perth to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (42 < maxCities)) {
            cities[21].addRoute(42,1188); // Johannesburg to Singapore
            routeCounter++;
            cities[42].addRoute(21,1188); // Singapore to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (43 < maxCities)) {
            cities[21].addRoute(43,1150); // Johannesburg to Sydney
            routeCounter++;
            cities[43].addRoute(21,1150); // Sydney to Johannesburg
            routeCounter++;
        }
        if ((21 < maxCities) && (50 < maxCities)) {
            cities[21].addRoute(50,1050); // Johannesburg to Zurich
            routeCounter++;
            cities[50].addRoute(21,1050); // Zurich to Johannesburg
            routeCounter++;
        }
        if ((22 < maxCities) && (23 < maxCities)) {
            cities[22].addRoute(23,1000); // Kolkata to London
            routeCounter++;
            cities[23].addRoute(22,1000); // London to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (25 < maxCities)) {
            cities[22].addRoute(25,594); // Kolkata to Manila
            routeCounter++;
            cities[25].addRoute(22,594); // Manila to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (30 < maxCities)) {
            cities[22].addRoute(30,200); // Kolkata to Mumbai
            routeCounter++;
            cities[30].addRoute(22,200); // Mumbai to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (31 < maxCities)) {
            cities[22].addRoute(31,544.5); // Kolkata to Nairobi
            routeCounter++;
            cities[31].addRoute(22,544.5); // Nairobi to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (35 < maxCities)) {
            cities[22].addRoute(35,940.5); // Kolkata to Paris
            routeCounter++;
            cities[35].addRoute(22,940.5); // Paris to Kolkata
            routeCounter++;
        }
        if ((22 < maxCities) && (42 < maxCities)) {
            cities[22].addRoute(42,346.5); // Kolkata to Singapore
            routeCounter++;
            cities[42].addRoute(22,346.5); // Singapore to Kolkata
            routeCounter++;
        }
        if ((23 < maxCities) && (24 < maxCities)) {
            cities[23].addRoute(24,1100); // London to Los Angeles
            routeCounter++;
            cities[24].addRoute(23,1100); // Los Angeles to London
            routeCounter++;
        }
        if ((23 < maxCities) && (25 < maxCities)) {
            cities[23].addRoute(25,1188); // London to Manila
            routeCounter++;
            cities[25].addRoute(23,1188); // Manila to London
            routeCounter++;
        }
        if ((23 < maxCities) && (27 < maxCities)) {
            cities[23].addRoute(27,891); // London to Mexico City
            routeCounter++;
            cities[27].addRoute(23,891); // Mexico City to London
            routeCounter++;
        }
        if ((23 < maxCities) && (28 < maxCities)) {
            cities[23].addRoute(28,297); // London to Milan
            routeCounter++;
            cities[28].addRoute(23,297); // Milan to London
            routeCounter++;
        }
        if ((23 < maxCities) && (30 < maxCities)) {
            cities[23].addRoute(30,800); // London to Mumbai
            routeCounter++;
            cities[30].addRoute(23,800); // Mumbai to London
            routeCounter++;
        }
        if ((23 < maxCities) && (31 < maxCities)) {
            cities[23].addRoute(31,643.5); // London to Nairobi
            routeCounter++;
            cities[31].addRoute(23,643.5); // Nairobi to London
            routeCounter++;
        }
        if ((23 < maxCities) && (32 < maxCities)) {
            cities[23].addRoute(32,594); // London to New York
            routeCounter++;
            cities[32].addRoute(23,594); // New York to London
            routeCounter++;
        }
        if ((23 < maxCities) && (33 < maxCities)) {
            cities[23].addRoute(33,247.5); // London to Nice
            routeCounter++;
            cities[33].addRoute(23,247.5); // Nice to London
            routeCounter++;
        }
        if ((23 < maxCities) && (34 < maxCities)) {
            cities[23].addRoute(34,200); // London to Oslo
            routeCounter++;
            cities[34].addRoute(23,200); // Oslo to London
            routeCounter++;
        }
        if ((23 < maxCities) && (35 < maxCities)) {
            cities[23].addRoute(35,148.5); // London to Paris
            routeCounter++;
            cities[35].addRoute(23,148.5); // Paris to London
            routeCounter++;
        }
        if ((23 < maxCities) && (37 < maxCities)) {
            cities[23].addRoute(37,700); // London to Quebec
            routeCounter++;
            cities[37].addRoute(23,700); // Quebec to London
            routeCounter++;
        }
        if ((23 < maxCities) && (38 < maxCities)) {
            cities[23].addRoute(38,400); // London to Rome
            routeCounter++;
            cities[38].addRoute(23,400); // Rome to London
            routeCounter++;
        }
        if ((23 < maxCities) && (39 < maxCities)) {
            cities[23].addRoute(39,1138.5); // London to Santiago
            routeCounter++;
            cities[39].addRoute(23,1138.5); // Santiago to London
            routeCounter++;
        }
        if ((23 < maxCities) && (40 < maxCities)) {
            cities[23].addRoute(40,990); // London to Seoul
            routeCounter++;
            cities[40].addRoute(23,990); // Seoul to London
            routeCounter++;
        }
        if ((23 < maxCities) && (41 < maxCities)) {
            cities[23].addRoute(41,980.1); // London to Shanghai
            routeCounter++;
            cities[41].addRoute(23,980.1); // Shanghai to London
            routeCounter++;
        }
        if ((23 < maxCities) && (42 < maxCities)) {
            cities[23].addRoute(42,1089); // London to Singapore
            routeCounter++;
            cities[42].addRoute(23,1089); // Singapore to London
            routeCounter++;
        }
        if ((23 < maxCities) && (44 < maxCities)) {
            cities[23].addRoute(44,445.5); // London to Tangiers
            routeCounter++;
            cities[44].addRoute(23,445.5); // Tangiers to London
            routeCounter++;
        }
        if ((23 < maxCities) && (45 < maxCities)) {
            cities[23].addRoute(45,1050); // London to Tokyo
            routeCounter++;
            cities[45].addRoute(23,1050); // Tokyo to London
            routeCounter++;
        }
        if ((23 < maxCities) && (47 < maxCities)) {
            cities[23].addRoute(47,940.5); // London to Vancouver
            routeCounter++;
            cities[47].addRoute(23,940.5); // Vancouver to London
            routeCounter++;
        }
        if ((23 < maxCities) && (48 < maxCities)) {
            cities[23].addRoute(48,396); // London to Venice
            routeCounter++;
            cities[48].addRoute(23,396); // Venice to London
            routeCounter++;
        }
        if ((23 < maxCities) && (49 < maxCities)) {
            cities[23].addRoute(49,316.8); // London to Vienna
            routeCounter++;
            cities[49].addRoute(23,316.8); // Vienna to London
            routeCounter++;
        }
        if ((23 < maxCities) && (50 < maxCities)) {
            cities[23].addRoute(50,290); // London to Zurich
            routeCounter++;
            cities[50].addRoute(23,290); // Zurich to London
            routeCounter++;
        }
        if ((24 < maxCities) && (25 < maxCities)) {
            cities[24].addRoute(25,792); // Los Angeles to Manila
            routeCounter++;
            cities[25].addRoute(24,792); // Manila to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (27 < maxCities)) {
            cities[24].addRoute(27,297); // Los Angeles to Mexico City
            routeCounter++;
            cities[27].addRoute(24,297); // Mexico City to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (32 < maxCities)) {
            cities[24].addRoute(32,346.5); // Los Angeles to New York
            routeCounter++;
            cities[32].addRoute(24,346.5); // New York to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (34 < maxCities)) {
            cities[24].addRoute(34,990); // Los Angeles to Oslo
            routeCounter++;
            cities[34].addRoute(24,990); // Oslo to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (40 < maxCities)) {
            cities[24].addRoute(40,891); // Los Angeles to Seoul
            routeCounter++;
            cities[40].addRoute(24,891); // Seoul to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (42 < maxCities)) {
            cities[24].addRoute(42,1287); // Los Angeles to Singapore
            routeCounter++;
            cities[42].addRoute(24,1287); // Singapore to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (43 < maxCities)) {
            cities[24].addRoute(43,1300); // Los Angeles to Sydney
            routeCounter++;
            cities[43].addRoute(24,1300); // Sydney to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (45 < maxCities)) {
            cities[24].addRoute(45,1000); // Los Angeles to Tokyo
            routeCounter++;
            cities[45].addRoute(24,1000); // Tokyo to Los Angeles
            routeCounter++;
        }
        if ((24 < maxCities) && (50 < maxCities)) {
            cities[24].addRoute(50,1080); // Los Angeles to Zurich
            routeCounter++;
            cities[50].addRoute(24,1080); // Zurich to Los Angeles
            routeCounter++;
        }
        if ((25 < maxCities) && (41 < maxCities)) {
            cities[25].addRoute(41,200); // Manila to Shanghai
            routeCounter++;
            cities[41].addRoute(25,200); // Shanghai to Manila
            routeCounter++;
        }
        if ((25 < maxCities) && (45 < maxCities)) {
            cities[25].addRoute(45,300); // Manila to Tokyo
            routeCounter++;
            cities[45].addRoute(25,300); // Tokyo to Manila
            routeCounter++;
        }
        if ((26 < maxCities) && (36 < maxCities)) {
            cities[26].addRoute(36,500); // Melbourne to Perth
            routeCounter++;
            cities[36].addRoute(26,500); // Perth to Melbourne
            routeCounter++;
        }
        if ((26 < maxCities) && (41 < maxCities)) {
            cities[26].addRoute(41,800); // Melbourne to Shanghai
            routeCounter++;
            cities[41].addRoute(26,800); // Shanghai to Melbourne
            routeCounter++;
        }
        if ((26 < maxCities) && (43 < maxCities)) {
            cities[26].addRoute(43,200); // Melbourne to Sydney
            routeCounter++;
            cities[43].addRoute(26,200); // Sydney to Melbourne
            routeCounter++;
        }
        if ((26 < maxCities) && (45 < maxCities)) {
            cities[26].addRoute(45,900); // Melbourne to Tokyo
            routeCounter++;
            cities[45].addRoute(26,900); // Tokyo to Melbourne
            routeCounter++;
        }
        if ((27 < maxCities) && (32 < maxCities)) {
            cities[27].addRoute(32,600); // Mexico City to New York
            routeCounter++;
            cities[32].addRoute(27,600); // New York to Mexico City
            routeCounter++;
        }
        if ((27 < maxCities) && (39 < maxCities)) {
            cities[27].addRoute(39,643.5); // Mexico City to Santiago
            routeCounter++;
            cities[39].addRoute(27,643.5); // Santiago to Mexico City
            routeCounter++;
        }
        if ((27 < maxCities) && (47 < maxCities)) {
            cities[27].addRoute(47,940.5); // Mexico City to Vancouver
            routeCounter++;
            cities[47].addRoute(27,940.5); // Vancouver to Mexico City
            routeCounter++;
        }
        if ((27 < maxCities) && (50 < maxCities)) {
            cities[27].addRoute(50,1020); // Mexico City to Zurich
            routeCounter++;
            cities[50].addRoute(27,1020); // Zurich to Mexico City
            routeCounter++;
        }
        if ((28 < maxCities) && (32 < maxCities)) {
            cities[28].addRoute(32,940.5); // Milan to New York
            routeCounter++;
            cities[32].addRoute(28,940.5); // New York to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (33 < maxCities)) {
            cities[28].addRoute(33,200); // Milan to Nice
            routeCounter++;
            cities[33].addRoute(28,200); // Nice to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (34 < maxCities)) {
            cities[28].addRoute(34,450); // Milan to Oslo
            routeCounter++;
            cities[34].addRoute(28,450); // Oslo to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (48 < maxCities)) {
            cities[28].addRoute(48,69.3); // Milan to Venice
            routeCounter++;
            cities[48].addRoute(28,69.3); // Venice to Milan
            routeCounter++;
        }
        if ((28 < maxCities) && (50 < maxCities)) {
            cities[28].addRoute(50,90); // Milan to Zurich
            routeCounter++;
            cities[50].addRoute(28,90); // Zurich to Milan
            routeCounter++;
        }
        if ((29 < maxCities) && (5 < maxCities)) {
            cities[29].addRoute(5,445.5); // Moscow to Berlin
            routeCounter++;
            cities[5].addRoute(29,445.5); // Berlin to Moscow
            routeCounter++;
        }
        if ((30 < maxCities) && (31 < maxCities)) {
            cities[30].addRoute(31,544.5); // Mumbai to Nairobi
            routeCounter++;
            cities[31].addRoute(30,544.5); // Nairobi to Mumbai
            routeCounter++;
        }
        if ((30 < maxCities) && (41 < maxCities)) {
            cities[30].addRoute(41,574.2); // Mumbai to Shanghai
            routeCounter++;
            cities[41].addRoute(30,574.2); // Shanghai to Mumbai
            routeCounter++;
        }
        if ((30 < maxCities) && (43 < maxCities)) {
            cities[30].addRoute(43,980); // Mumbai to Sydney
            routeCounter++;
            cities[43].addRoute(30,980); // Sydney to Mumbai
            routeCounter++;
        }
        if ((31 < maxCities) && (35 < maxCities)) {
            cities[31].addRoute(35,800); // Nairobi to Paris
            routeCounter++;
            cities[35].addRoute(31,800); // Paris to Nairobi
            routeCounter++;
        }
        if ((31 < maxCities) && (38 < maxCities)) {
            cities[31].addRoute(38,450); // Nairobi to Rome
            routeCounter++;
            cities[38].addRoute(31,450); // Rome to Nairobi
            routeCounter++;
        }
        if ((31 < maxCities) && (42 < maxCities)) {
            cities[31].addRoute(42,1000); // Nairobi to Singapore
            routeCounter++;
            cities[42].addRoute(31,1000); // Singapore to Nairobi
            routeCounter++;
        }
        if ((32 < maxCities) && (34 < maxCities)) {
            cities[32].addRoute(34,600); // New York to Oslo
            routeCounter++;
            cities[34].addRoute(32,600); // Oslo to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (35 < maxCities)) {
            cities[32].addRoute(35,643.5); // New York to Paris
            routeCounter++;
            cities[35].addRoute(32,643.5); // Paris to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (38 < maxCities)) {
            cities[32].addRoute(38,700); // New York to Rome
            routeCounter++;
            cities[38].addRoute(32,700); // Rome to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (40 < maxCities)) {
            cities[32].addRoute(40,1120); // New York to Seoul
            routeCounter++;
            cities[40].addRoute(32,1120); // Seoul to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (45 < maxCities)) {
            cities[32].addRoute(45,1150); // New York to Tokyo
            routeCounter++;
            cities[45].addRoute(32,1150); // Tokyo to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (47 < maxCities)) {
            cities[32].addRoute(47,445.5); // New York to Vancouver
            routeCounter++;
            cities[47].addRoute(32,445.5); // Vancouver to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (48 < maxCities)) {
            cities[32].addRoute(48,950); // New York to Venice
            routeCounter++;
            cities[48].addRoute(32,950); // Venice to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (49 < maxCities)) {
            cities[32].addRoute(49,850); // New York to Vienna
            routeCounter++;
            cities[49].addRoute(32,850); // Vienna to New York
            routeCounter++;
        }
        if ((32 < maxCities) && (50 < maxCities)) {
            cities[32].addRoute(50,450); // New York to Zurich
            routeCounter++;
            cities[50].addRoute(32,450); // Zurich to New York
            routeCounter++;
        }
        if ((33 < maxCities) && (35 < maxCities)) {
            cities[33].addRoute(35,99); // Nice to Paris
            routeCounter++;
            cities[35].addRoute(33,99); // Paris to Nice
            routeCounter++;
        }
        if ((34 < maxCities) && (35 < maxCities)) {
            cities[34].addRoute(35,247.5); // Oslo to Paris
            routeCounter++;
            cities[35].addRoute(34,247.5); // Paris to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (38 < maxCities)) {
            cities[34].addRoute(38,445.5); // Oslo to Rome
            routeCounter++;
            cities[38].addRoute(34,445.5); // Rome to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (45 < maxCities)) {
            cities[34].addRoute(45,891); // Oslo to Tokyo
            routeCounter++;
            cities[45].addRoute(34,891); // Tokyo to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (49 < maxCities)) {
            cities[34].addRoute(49,316.8); // Oslo to Vienna
            routeCounter++;
            cities[49].addRoute(34,316.8); // Vienna to Oslo
            routeCounter++;
        }
        if ((34 < maxCities) && (50 < maxCities)) {
            cities[34].addRoute(50,310); // Oslo to Zurich
            routeCounter++;
            cities[50].addRoute(34,310); // Zurich to Oslo
            routeCounter++;
        }
        if ((35 < maxCities) && (37 < maxCities)) {
            cities[35].addRoute(37,650); // Paris to Quebec
            routeCounter++;
            cities[37].addRoute(35,650); // Quebec to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (40 < maxCities)) {
            cities[35].addRoute(40,1250); // Paris to Seoul
            routeCounter++;
            cities[40].addRoute(35,1250); // Seoul to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (41 < maxCities)) {
            cities[35].addRoute(41,1150); // Paris to Shanghai
            routeCounter++;
            cities[41].addRoute(35,1150); // Shanghai to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (42 < maxCities)) {
            cities[35].addRoute(42,1000); // Paris to Singapore
            routeCounter++;
            cities[42].addRoute(35,1000); // Singapore to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (44 < maxCities)) {
            cities[35].addRoute(44,300); // Paris to Tangiers
            routeCounter++;
            cities[44].addRoute(35,300); // Tangiers to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (45 < maxCities)) {
            cities[35].addRoute(45,1300); // Paris to Tokyo
            routeCounter++;
            cities[45].addRoute(35,1300); // Tokyo to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (48 < maxCities)) {
            cities[35].addRoute(48,200); // Paris to Venice
            routeCounter++;
            cities[48].addRoute(35,200); // Venice to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (49 < maxCities)) {
            cities[35].addRoute(49,180); // Paris to Vienna
            routeCounter++;
            cities[49].addRoute(35,180); // Vienna to Paris
            routeCounter++;
        }
        if ((35 < maxCities) && (50 < maxCities)) {
            cities[35].addRoute(50,140); // Paris to Zurich
            routeCounter++;
            cities[50].addRoute(35,140); // Zurich to Paris
            routeCounter++;
        }
        if ((36 < maxCities) && (42 < maxCities)) {
            cities[36].addRoute(42,550); // Perth to Singapore
            routeCounter++;
            cities[42].addRoute(36,550); // Singapore to Perth
            routeCounter++;
        }
        if ((36 < maxCities) && (43 < maxCities)) {
            cities[36].addRoute(43,550); // Perth to Sydney
            routeCounter++;
            cities[43].addRoute(36,550); // Sydney to Perth
            routeCounter++;
        }
        if ((37 < maxCities) && (47 < maxCities)) {
            cities[37].addRoute(47,495); // Quebec to Vancouver
            routeCounter++;
            cities[47].addRoute(37,495); // Vancouver to Quebec
            routeCounter++;
        }
        if ((38 < maxCities) && (39 < maxCities)) {
            cities[38].addRoute(39,980.1); // Rome to Santiago
            routeCounter++;
            cities[39].addRoute(38,980.1); // Santiago to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (42 < maxCities)) {
            cities[38].addRoute(42,1079.1); // Rome to Singapore
            routeCounter++;
            cities[42].addRoute(38,1079.1); // Singapore to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (45 < maxCities)) {
            cities[38].addRoute(45,1200); // Rome to Tokyo
            routeCounter++;
            cities[45].addRoute(38,1200); // Tokyo to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (48 < maxCities)) {
            cities[38].addRoute(48,148.5); // Rome to Venice
            routeCounter++;
            cities[48].addRoute(38,148.5); // Venice to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (49 < maxCities)) {
            cities[38].addRoute(49,247.5); // Rome to Vienna
            routeCounter++;
            cities[49].addRoute(38,247.5); // Vienna to Rome
            routeCounter++;
        }
        if ((38 < maxCities) && (50 < maxCities)) {
            cities[38].addRoute(50,250); // Rome to Zurich
            routeCounter++;
            cities[50].addRoute(38,250); // Zurich to Rome
            routeCounter++;
        }
        if ((40 < maxCities) && (41 < maxCities)) {
            cities[40].addRoute(41,400); // Seoul to Shanghai
            routeCounter++;
            cities[41].addRoute(40,400); // Shanghai to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (42 < maxCities)) {
            cities[40].addRoute(42,500); // Seoul to Singapore
            routeCounter++;
            cities[42].addRoute(40,500); // Singapore to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (43 < maxCities)) {
            cities[40].addRoute(43,980); // Seoul to Sydney
            routeCounter++;
            cities[43].addRoute(40,980); // Sydney to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (46 < maxCities)) {
            cities[40].addRoute(46,500); // Seoul to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(40,500); // Ulan Bataar to Seoul
            routeCounter++;
        }
        if ((40 < maxCities) && (47 < maxCities)) {
            cities[40].addRoute(47,594); // Seoul to Vancouver
            routeCounter++;
            cities[47].addRoute(40,594); // Vancouver to Seoul
            routeCounter++;
        }
        if ((41 < maxCities) && (42 < maxCities)) {
            cities[41].addRoute(42,250); // Shanghai to Singapore
            routeCounter++;
            cities[42].addRoute(41,250); // Singapore to Shanghai
            routeCounter++;
        }
        if ((41 < maxCities) && (43 < maxCities)) {
            cities[41].addRoute(43,850); // Shanghai to Sydney
            routeCounter++;
            cities[43].addRoute(41,850); // Sydney to Shanghai
            routeCounter++;
        }
        if ((41 < maxCities) && (50 < maxCities)) {
            cities[41].addRoute(50,1050); // Shanghai to Zurich
            routeCounter++;
            cities[50].addRoute(41,1050); // Zurich to Shanghai
            routeCounter++;
        }
        if ((42 < maxCities) && (43 < maxCities)) {
            cities[42].addRoute(43,800); // Singapore to Sydney
            routeCounter++;
            cities[43].addRoute(42,800); // Sydney to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (45 < maxCities)) {
            cities[42].addRoute(45,500); // Singapore to Tokyo
            routeCounter++;
            cities[45].addRoute(42,500); // Tokyo to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (46 < maxCities)) {
            cities[42].addRoute(46,400); // Singapore to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(42,400); // Ulan Bataar to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (49 < maxCities)) {
            cities[42].addRoute(49,900); // Singapore to Vienna
            routeCounter++;
            cities[49].addRoute(42,900); // Vienna to Singapore
            routeCounter++;
        }
        if ((42 < maxCities) && (50 < maxCities)) {
            cities[42].addRoute(50,950); // Singapore to Zurich
            routeCounter++;
            cities[50].addRoute(42,950); // Zurich to Singapore
            routeCounter++;
        }
        if ((43 < maxCities) && (45 < maxCities)) {
            cities[43].addRoute(45,891); // Sydney to Tokyo
            routeCounter++;
            cities[45].addRoute(43,891); // Tokyo to Sydney
            routeCounter++;
        }
        if ((44 < maxCities) && (50 < maxCities)) {
            cities[44].addRoute(50,300); // Tangiers to Zurich
            routeCounter++;
            cities[50].addRoute(44,300); // Zurich to Tangiers
            routeCounter++;
        }
        if ((45 < maxCities) && (46 < maxCities)) {
            cities[45].addRoute(46,445.5); // Tokyo to Ulan Bataar
            routeCounter++;
            cities[46].addRoute(45,445.5); // Ulan Bataar to Tokyo
            routeCounter++;
        }
        if ((45 < maxCities) && (47 < maxCities)) {
            cities[45].addRoute(47,841.5); // Tokyo to Vancouver
            routeCounter++;
            cities[47].addRoute(45,841.5); // Vancouver to Tokyo
            routeCounter++;
        }
        if ((45 < maxCities) && (50 < maxCities)) {
            cities[45].addRoute(50,1200); // Tokyo to Zurich
            routeCounter++;
            cities[50].addRoute(45,1200); // Zurich to Tokyo
            routeCounter++;
        }
        if ((48 < maxCities) && (49 < maxCities)) {
            cities[48].addRoute(49,100); // Venice to Vienna
            routeCounter++;
            cities[49].addRoute(48,100); // Vienna to Venice
            routeCounter++;
        }
        if ((49 < maxCities) && (50 < maxCities)) {
            cities[49].addRoute(50,100); // Vienna to Zurich
            routeCounter++;
            cities[50].addRoute(49,100); // Zurich to Vienna
            routeCounter++;
        }
   
    }    
}



