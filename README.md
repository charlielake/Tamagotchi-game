# My Personal Project - Tamagotchi

## What will the application do?
The goal for the application is to design a Tamagotchi-like game *(https://en.wikipedia.org/wiki/Tamagotchi)* where the
player takes care of a pet as it grows. It will start as mostly text inputs, and then eventually should evolve to have
some basic graphics by the end. I'd like to focus on simply expanding all the possible activities and features,
potentially expanding the game to have more things to do outside of just taking care of the pet. That being said, I have 
no experience developing video games, so I expect my goals to change as the project is developed as I'm unsure of what
is realistic or unrealistic at this point.

To summarize, the application will simply allow the player to take care of their pet, by feeding it, taking care of it 
in other ways, cleaning up after it, and other features inspired by the original Tamagotchi games.

## Who will use it?
I would use it along with anyone else who is interested in trying it out! 

## Why is this of interest to me?
I've always been into video games, and so I believe that attempting to develop a simpler one would give me insight into 
how these games I've played work deep down. As well, I believe that as I am interested in video games, I will be 
passionate about this project and so it will be interesting for me to work on it. I listed above features that I'd like
to incorporate into the project later if possible, and I believe that by continuing to iterate on this project even
after the course, it would help me improve my programming skills, as it would be something I'd easily be able to
continue working on.

To summarize:
- I'm passionate and interested in learning about video games.
- I think this project would allow me to learn quite a bit through continuing to improve it even 
after this course (all the features listed above).
- I think it would be pretty fun to make!

##User Stories
- As a user, I want to be able to add a new pet to a list of pets (have multiple pets at once).
- As a user, I want to be able to select what kind of pet I have.
- As a user, I want to be able to feed my pet.
- As a user, I want to be able to play with my pet and have multiple options for things to do.
- As a user, I want to have my game data be saved to file when I quit the game.
- As a user, I want to be able to load my previous game data from file when the program starts

# Instructions for Grader PHASE 3
## Note: the Main and TamagotchiApp classes are for the console-based application. Only look at GUI class for GUI.
## Grading Instructions

 - You can generate the first required event by pressing the "Add Pet" button which will allow you to add a pet to
 the list of pets.
 
 - You can generate the second required event by pressing the "Remove Pet" button which will allow you to remove a 
 pet from the list of pets.
 
 - You can trigger my audio component by clicking any button - this will trigger a click sound. As well, *nearly* any 
 action interacting with a pet (e.g. Walk, feed, play, treat, etc...) will cause either a barking/meowing sound or a
 more specific sound (e.g. drinking water/milk has a specific sound). JUST A WARNING: the cat meowing sound is pretty
 obnoxious after hearing it a couple times, and it triggers quite often so I'm sorry.
 
 - You can save the state of my application by hitting the "Save" button or the "Save and Quit" button (which will save
  then close the application)
  
 - You can load the state of my application by hitting the "Load" button.
 
 ##Additional Instructions/Information:
 The GUI contains a list of pets containing all the pets for this Tamagotchi app. Each pet has a bunch of fields 
 representing their statuses. Once a pet exists in the list, it can be selected from the dropDown menu on the 
 bottom-right-ish, which causes the bars above to display their hunger, happiness, and energy. Once there are multiple 
 pets (added with Add Pet button), you can swap between them to view each of their stats. This dropDown menu is my panel
 displaying all the X in my list Y. Although I listed the two required events above as add and remove, there are many 
 more events that can be done interacting with the pets and changing their field values (all the others that are not
 save, load, quit+save, add or remove).
 
 Thanks!
 
 
 # Instructions for Grader PHASE 4
 ## Grading Instructions
 ## Note: the Main and TamagotchiApp classes are for the console-based application. Please ignore them!
 ## Phase 4: Task 2
 My Pet abstract class is a superclass for both the Cat and Dog classes. Each of the subclasses override a couple 
 methods from the Pet class and implement different values to update. As well, the classes are handled differently in 
 the GUI, with each having a different default sound, and some methods having different sounds i.e. when you play with 
 a laser pointer with them, the dog is dumb and crashes into the wall, whereas the cat doesn't, which are handled by two
 different sounds based on the pet's class. Methods overridden specifically are petPet(), hugPet(), and laserPointer().
 
## Phase 4: Task 3
1) My GUI originally handled everything related to the GUI as well as the audio and visual feedback. I removed
all the sound related methods and created a new class called SoundManager which now contains them. Each GUI will 
initialize a SoundManager, which is an object that will now handle it, improving cohesion!

2) My GUI also used to handle EVERYTHING including all the panels. To increase cohesion I have made the GUI have one 
panel containing two smaller panels - each controlling their own buttons and objects. Specifically, the ButtonPanel 
class contains a panel with all the interactable buttons on it and the methods resulting from pushing those buttons - 
while the PetStatusPanel contains all the status bars for pets, as well the dropDown menu allowing you to swap between 
their statuses. PetStatusPanel also contains all the methods related to updating the fields presented here and the
names shown in the dropDown list - so it has everything about the bars and stuff shown on that panel. Each panel also
implements the ActionListener class and so each panel handles their own ActionEvents now and all the methods 
associated with them.

To summarize 1) separated the sound into it's own class and 2) separated the panels into their own classes, with each 
one handling ALL the methods their components are related to. The GUI class now only initializes everything and puts the
panels together - improving coupling (as there should in theory be a lot less dependance now). However, as the GUI 
contains the fields the panels act on - there is still quite a bit of dependance on each other. This could be improved
in the future. However for now, cohesion should be a lot better as GUI was split into 4 classes each having their own
specific purposes (rather than the blob GUI I had before).
