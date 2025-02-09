## About
As a software engineering student, I've spent a great deal of time trying to master various sorting algorithms starting from my first semester programming course where a very basic (and unnamed) variant of *selection-sort* was introduced simply for the sake of introducing practice and test problems more complicated than what we had before. During my second semester, we expanded on this greatly, having been abruptly introduce to over half a dozen standard sorting algorithms that we were expected to implement and use at will. A more expansive DSA course during my third semester touched onto most of these as well (with a few extra), however, given the limited time particularly during the aforementioned second semester course, the natural way to learn most of these algorithms was mostly just pure memorization.

I've now decided to use the knowledge I have about these algorithms and solidify it entirely (possibly by also filling gaps in it) by making this project. Taking these algorithms and applying them in a concurrent way with emphasis on the user experience/perception over a level of abstraction provided by Swing's JLabels requires their complete understanding. Moreover this also served me as good practice of working with Swing, particularly how to write multithreaded code with it, what to avoid and how to set up a decent UI with an intuitive layout for the average user.

I hope that this project will reach a greater audience that will use it to strengthen their own understanding of these algorithms and come out on top in our ever more competitive industry.

## User Interface

### What you're presented with upon running:
![image](https://github.com/user-attachments/assets/f8f0ec74-0c8c-408f-a244-dc9e2ba5583d)

#### Input field
![image](https://github.com/user-attachments/assets/1b40c3a2-1f97-4e31-81db-bad92e14e59c)

The input field takes up to 20 comma-separated integer (whole number) values. Extra spaces or commas will be ignored accordingly.

#### Setting the values
![image](https://github.com/user-attachments/assets/e6a6dcc2-316d-42b8-ac25-82f5215dcb65)

Upon pressing "Set" with a properly defined input field, the values will be displayed on the center panel. They're now ready to sort.

#### Randomizing the values
![image](https://github.com/user-attachments/assets/46e80b18-2c4a-4ef3-9e3b-4140adbe75c3)

Upon pressing "Randomize", 20 random values from the segment [0, 100) will be displayed on the center panel. They're now ready to sort.

#### Selecting the sorting algorithm
![image](https://github.com/user-attachments/assets/9fb59cfd-e47c-4f1b-ae4b-07d1ded58e89)

A dropdown menu (combo box) at the bottom can be used to select a sorting algorithm.

#### Selecting the sorting order
![image](https://github.com/user-attachments/assets/17570e02-172e-4c0b-b928-194dee05146a)
![image](https://github.com/user-attachments/assets/3b09dcfd-df31-4d1d-a98d-a9fbda36c95d)

The button next to the sorting algorithm selection menu can be used to toggle between (monotonic) ascending and descending order of sorting.

#### Selecting the simulation speed
![image](https://github.com/user-attachments/assets/9907fa82-7466-4bb1-a240-989ae976460a)

The simulation will run in real time, therefore having the ability to change the speed at which it happens is a must-have feature. This can be achieved by moving the slider labeled with "Simulation speed" on the bottom. Moving it to the left will cause the simulation to run slower and vice versa. **Note: This can be changed mid-simulation.**.

#### Sorting the values
![image](https://github.com/user-attachments/assets/8475fac5-2da5-4da8-9fb6-3e7625606474)

Upon pressing "Sort" with an adequate list of values already set on the center panel, the simulation will commence as the selected algorithm will be applied onto the list and it will finish when the list is sorted in the selected order. On the example above, we used *selection-sort* on values we set under "Setting the values". 

While the simulation is ongoing, all controls will be locked except exiting and changing the simulation speed. They will subsequently be unlocked after the simulation concludes.

In our concrete example using *selection-sort*, the sorted portion of the collection will be labelled in green. The element that's currently being iterated and having values compared against to find the smallest smaller or greatest greater value will be labelled in blue, and the current smallest/biggest value from the rest of the collection will be marked in red, and subsequently swapped with it if no other smaller/greater values are found in that iteration.

Every sorting algorithm will have its own way of being labeled to allow for clear visualization of how it works.
