# OOP Ariel University - Directed Weighted Graph
### Authors: Raz Gavrieli, Eran David, Amit Melamed

![DirectedWeightedGraph](https://user-images.githubusercontent.com/90526270/145264778-5ee1e3d4-823b-4295-b3b3-1c06ecfe639c.png)


## HOW TO USE 
Clone the project into your computer, head to `Ex2\OOP_Ex2\out\artifacts\OOP_Ex2_jar` in cmd, and run the command `java -jar Ex2.jar data/G1.json`
![howtorun](https://user-images.githubusercontent.com/90526270/145682574-53fd1a07-a77b-4821-9f21-87cd99405d64.png)



The GUI will pop up to your screen, with the desired graph loaded. </br>
Buttons:
### Remove - 
This button will remove a node. </br>
 You will be asked to insert a node ID to remove from the graph. 
 
 ### Add - 
 This button will add a new node. </br>
 You will be asked to enter an ID for your new node, and then enter a new location.</br> 
 (Keep in mind that you will replace an existing node if you choose an ID that already exists.  </br>
 The old edges will stay the same, they will point to the new location you entered). 

 ### Load - 
 You will be asked to insert a name of a graph you want to load to the GUI. </br>
 Note that you ONLY need to insert the name of the graph, without it's path and without the '.json' at the end. </br>
 The graph will have to be in the /data folder and be a json type. 
 
 ### Save - 
 You will be asked to give a name to the graph you want to save. </br>
 The graph will be saved at the /data folder and will be a json type. </br>
 Note that if you give it a name that already exists, it WILL override the old graph. 
 
 ### Center - 
 The center node will be colored blue. 


 ### TSP - 
 First you will be asked to enter HOW MANY nodes does the "sales man" has to visit. </br>
 After that you will be asked to enter the IDs of nodes he wants to visit. </br>
 You will receive and ordered list of the nodes he has to go through. </br>
 Also, that path will be colored purple.

![TSPexample](https://user-images.githubusercontent.com/90526270/145382286-96cf14c6-6d60-4b65-9b31-414aff1dfb55.png)

 
 ### Path Dist - 
 You will be asked to enter a source and destination IDs. </br>
 Then a message will pop with the cost of the journey between the two nodes. 
 
 ### Path - 
 The same as path distance, only this function also paint the path on the graph. </br>
 The nodes that you have to go through will be painted purple. 
  
 ### Connect -
 This button will add a new edge. </br>
 You will be asked to insert source and destination IDs, and a weighet. 
 
  ### Disconnect - 
  This button will remove an existing edge. </br>
  You will be asked to insert source and destination IDs of the edge you want to remove. </br>
  Make sure this edge exists. 
  
  ### Toggles - 
  You can toggle the printing of nodes, arrows and edges.  </br>
  Keep in mind that when loading large graphs, the GUI will toggle the edges or arrows by itself to shorten loading times.
  
  
## Analyzing and Explaining The Code

<img width="160" alt="UML EX2GUI" src="https://user-images.githubusercontent.com/93621085/145468509-40348379-f667-40dc-bf43-ccf56433ad4b.png">

DirectedWeightedGraphAlgorithms - creating an DirectedWeightedGraphAlgorithms by DirectedWeightedGraph object and all the functions algorithms that we can run on him.

DirectedWeightedGraph - creating the DirectedWeightedGraph by using NodeData EdgeData and GeoLocation.


<img width="560" alt="UML DIRECTEDALGO" src="https://user-images.githubusercontent.com/93621085/145468115-3953f7e6-c759-4d5b-8968-9c9f9151d500.png">


NodeData ,EdgeData and GeoLocation - Aggregation between NodeData ,EdgeData and GeoLocation and DirectedWeightedGraph


<img width="425" alt="UML NODEDGEOLO" src="https://user-images.githubusercontent.com/93621085/145468527-d5f5cd98-edfc-473f-b3fd-a77573cd1abb.png">
  
  ### CLASS NodeData_ -
  This class implements the interface of a node.
  It holds a `int key` for uniqe ID.   `GeoLocation location` for x,y,z coordinates. `double weight` for cost (Initialized as 0 for all nodes). </br>
  `int tag` for implementing algorithms. `String info` for holding information about the node as a string. 
  
  It also holds 2 hashmaps. 
  `outEdges` that holds edges that go out of the current node, and `inEdges` that holds edges that go into the current node. 
  </br>
  
  This class has 2 constructors, a simple copy constructor and the "main" constructor that is being used when loading a graph from a json file. 
  
  ### CLASS EdgeData_ -
  This class implements the interface of an edge. 
  It holds two IDs, one `int destination` for the destination's ID and one `int source` for the source's.  </br>
  It also holds `double weight` for the cost of going through the edge, `int tag` for implementing algorithms, `int id` for a uniqe ID for each edge and `String info` for holding information about the edge as a string.</br>
  
  The class has 2 constructors, a copy constructor and a constructor that is being used when loading a graph from a json file. 

  ### CLASS DirectedWeightedGraph_ -
This class holds 2 important HashMaps, one for nodes and one for edges. 

#### This class has 4 constructors:</br>
One is an empty constructor, this constructor is used to create random and big graphs with the add and connect functions. 
One is a simple deep copy constructor.
One is a copy constructor, it also gets a boolean value which make it to transpose the graph that is being copied. 
And the last one is the 'main' constructor, its gets a json file as an argument and creates new nodes and edges according to the information in the json file. </br>

#### The principles of creating a new graph:</br>
 First we declare the new nodes, using the 'main' node constructor. We insert each nodes into the nodes HashMap, the Map's key is the ID of the node. </br>
 Then, we declare the edges, obviusly using the 'main' edge cunstructor we mentioned earlier. </br>
 Each edge is being stored in <b>3</b> HashMaps. One is the edge's HashMap that is stored in the graph itself, the other two are stored in the source node and the destination node. Their key is the destination's ID and the source's ID accordingly. e.g. an edge going from 3 to 7, will be stored in node 3's outEdges hashmap with the key 7. It will also be stored in node 7's inEdges hashmap with the key 3. And finally it will be stored in the general edge's HashMap with it's ID as a key. </br>
 These principles reaccures in all constructors, and also, these principles influence the rest of the functions that handle edges and nodes.  </br>
 
#### Removing and adding edges and nodes:</br>
<b>Adding a node</b> only requires us to add it to the list of nodes that the graph holds. </br>
<b>Remove a node</b> is more advanced, and first requires us to look for the edges associating with the node we want to remove. Because we need to remove those too. </br>
We go through the outEdges and inEdges HashMaps that are explained above, and locate the IDs of the edges we need to remove. We first remove those edges and then remove the node itself. </br>
<b>Adding an edge</b> in a function that is called 'connect', we are given two node IDs - one for src and on for dest - and we create a new edge that connects the two. We also add that edge to the three HashMaps that should store this edge. (One is the edges HashMap of the graph, and the other two are the inEdges and outEdges in the dest and src)</br>
<b>Removing an edge</b> is simple, we locate the edge using the source and destination values given to us, and remove it from all the three HashMaps that hold that edge. </br>

#### Iterators implemintation:</br>
 For having a more simple way of going through each HashMap, we created 3 getters for iterators. </br>
 In order to do that, we created 3 private inner classes that implements Iterator<Object> Interface. We kept each function the same, execpt the remove function: </br>
 In each remove function we first check if the iterator has next, if it does we continue to by saving the next object that the Iterator has to skip to, and then we delete the current object by using the remove function of the graph itself. Then, we repair the Iterator by pointing it to the new Iterator of the HashMap, and then we skip all the way to the saved object. </br>
 That way we deleted the current object, and skip to the next object in the collection.
 
 ### CLASS DirectedWeightedGraphAlgorithms_ -
 This class holds a `DirectedWeightedGraph originalGraph`. This is the graph all the algorithms would operate on. It also holds a `DirectedWeightedGraph transposedGraph` for the inverted graph. </br>
 It also holds an important 3D Array: `double pathData[][][]`, which (after calculation) holds data about the shortest paths in this graph. This array is crucial and understanding it is required for understaing the rest of the code in this class. </br>
 
 #### Initiation:
 The constructor in this class gets a json file, create a DirectedWeightedGraph and calls the init function with this graph. </br>
 The init functions sets the size of pathData[][][] according to the amound of nodes in the graph, and then it initializes the rest of the variables. 
 
 #### calculatePath:
 This algorithm is based on Dijikstra's algorithm, its goes through the graph and calculates the path data. The data it gathers is stored in the pathData[][][] array. </br>
 The following is an example of using pathData[][][]: </br>
 `pathData[3][4][0]` - points to the weight of going from node 3 to node 4. </br>
 `pathData[1][8][1]` - points to the <b>parent</b> of node 8, when arriving to it from node 1.  </br>
 </br>
 We also have a second calculatePath, which in addition to source key, it also gets a boolean value. This value commands the function to calculate the path in the transposed graph.
 
 #### isConnected:
 We used an interesting approach in this function, if <b>all</b> the paths are calculated in the graph, then we can just return the boolean isConnected.</br>
 Else, we won't need to calculate all the paths, we can just calculate one path from a specific node, and then calculate the path again from this same specific node, only on the transposed graph. If in both, the boolean isConnected stayed true, then the graph is connected.
 
 #### ShortestPath:
 If the path is not already calculated, calculate it using the calculatePath function, then return the value of `pathData[src][dest][0]`. </br>
 For returning the path itself (and not only the cost), just create a List an go through the pathData array until `pathData[src][dest][1]` is the source. 
 
  #### Center:
  The same as in isConnected, we first check if pathData is calculated, if its not, we calculate it. </br>
  Then we go through all the array and find the source node with the lowest maximum cost to another node.
  If the graph is not connected, we return null. (There is no center node).

 #### TSP:
 The travelling salesman problem (also called the travelling salesperson problem or TSP) asks the following question: "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?" It is an NP-hard problem in combinatorial optimization, important in theoretical computer science and operations research.
 We chose a gready alghorithm to solve the problem.
 Step 1: Pick an arbitrary city and call it city 1.
 Step 2: Find a city with the smallest distance from city 1, and call it city 2.
 Step 3: Find a city in the rest of the n - 2 unvisited cities with the smallest distance from city 2.
 Step 4: Output the tour: City 1 > City 2 > ... > City n > City 1.
 if the number of cities if less then 4: we will calculate all of the permotation and give the optimal solution.
 adventages:
 1.faster running time (faster than O(n!)).
 disadventages:
 1.will now give the optimal solution (somtimes will) if n>4.
 
 #### Load and Save: 
 <b>Load</b> We use this function the load another graph to the same DirectedWeightedGraphAlgorithms_, then we init the graph again. </br>
 <b>Save</b> This function read the information about the graph from the originalGraph, and write it into a new json file. 
## Analyzing Performence
### Time Complexity of Algorithms


| FUNC  | TIME COMPLEXITY |
| ----- | ---------------|
|   removeNode()   |   O(v.degree)  |
|   isConnected()   |     O(2elogv)    |
|   shortestPathDist()   |    O(elogv)      |
|    shortestPath()  |   O(elogv)      |
| center() | O(velogv+v^2 |
|   tsp() for v<=4   |     O(v!)      |
|   tsp() for v>4   |      O(v^2)      |
|   calcPath()  |    O(elogv)     |


### Loading times of large graphs
- Loaded on a pc with 16GB of ram and an I7-7700HQ (mobile chip).
- Each node has <b> exactly </b> 10 out edges. 
- All the functions used to test <b>performence</b> are in class 'lib'. 
</br>

<b> In this assignment we for the first time ever encountered a problem with memory space, </br>
 We tried using less objects to present a graph in the branch "spacecomplexity".
 But unfortunately all of our attempts have failed as we did not plan out our code to save space, only time.

![loadproof](https://user-images.githubusercontent.com/90526270/145411886-6e996704-bff1-494b-897e-d947aafae4e8.png)

LOADING TIMES
| N. of nodes | 1k | 10k | 100k | 1000k |
| ----------- | ---- | ----- | ------ |-------- |
| MS    |  110 |  4467 |  heap |    heap |

ISCONNECTED TIMES
| N. of nodes | 1k | 10k | 100k | 1000k |
| ----------- | ---- | ----- | ------ |-------- |
| MS    | 0 |  88 |  heap |    heap |


CENTER TIMES
| N. of nodes | 1k | 10k | 100k | 1000k |
| ----------- | ---- | ----- | ------ |-------- |
| MS    |  969 |  heap |  heap |    heap |






