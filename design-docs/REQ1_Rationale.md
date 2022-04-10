####Tree Class
For this requirement, we use the existing Tree class to code all the functionalities. We could have used an interface for the methods to spawn all the different growth cycles of trees and have the Tree class implement that interface, and on paper, this would successfully fulfill the requirements of the Dependency Inversion Principle. In reality, however, this is rather unnecessary since the sprout, sapling, and mature phases are used by the Tree class and the Tree class only.


Instead, we used an ENUM class called TreeCycleStage class which already helps split the cycle stages which can be used within every single method to set a new stage using the setters in the class. The use of enumeration here fulfills the criteria of requiring a predefined list of values that refer to some textual/numerical data and thus justifies our choice of the ENUM class.Furthermore, the dedicated method for all the different stages fullfils the criteria for the Single Responsibility Principle.

We also increment and reset turn counters within each method so to say, every single method is responsible to fulfill its own functionalities while also keeping track of when to switch to the next stage.

