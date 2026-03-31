
This project is just a Java ecosystem simulation that has interactions between plants, herbivores, and carnivores in a 2D world grid.
Organisms are placed in the world, while the animals act by moving or eating nearby organisms, and they will die 
if they run out of energy from not finding food.


## Testing

JUnit tests were added to make sure  that the simulation works. 
The tests ensure that animals eat the correct organisms, do not eat the wrong organisms,
and die when their energy reaches zero.

TESTS ADDED 
A herbivore eats a plant when the plant is in a neighboring cell.
A herbivore does not eat another herbivore.
A herbivore dies after 10 ticks if it does not eat.
A carnivore dies after 20 ticks if it does not eat.
