# Game of Life
This project is a simple JavaFX Game of Life simulator

## Conway's Game of Life
The Game of Life is a simulations of the "life" of cells which follows three simple rules

* An alive cell surrounded by two or three alive cells survive
* A dead cell surrounded by three alive cells comes back to lives
* Otherwise, the cell dies or remain dead

## The UI
The UI is divided in four main panels.

### The Field panel
![The Field panel](https://github.com/lsonnino/gameOfLife/blob/master/screenshots/fieldPanel.jpg "Field panel") The Field panel contains the main simulation. By clicking on a cell, the cell dies if it wall alive or comes back to lives if it was dead.

### The Bottom bar
![The Bottom bar](https://github.com/lsonnino/gameOfLife/blob/master/screenshots/bottomPanel.jpg "Bottom bar") The Bottom bar contains from the left to the right:
* The darkmode swich which allows to swich between the dark mode and the light mode
* The iterations number or the number of times the simulations refreshed
* The start/stop button to start or pause the simulation

#### Darkmode
![The darkmode swich](https://github.com/lsonnino/gameOfLife/blob/master/screenshots/darkmode.jpg "Dark/Light mode") The darkmode swich allows to swich between a dark and a light mode.

### The Properties panel
![The Properties panel](https://github.com/lsonnino/gameOfLife/blob/master/screenshots/propertiesPanel.jpg "Properties panel") The Properties panel contains some simple properties. From top to bottom, left to right:
* The "alive borders" swich. If pressed, the borders are considered as alive cells
* The speed slider changes the simulation's speed. It goes from 100ms/iteration up to 1s/iteration
* The "s" button or "save" button saves the properties (the darkmode is considered as a properties, even if it stand in the bottom bar)
* The "r" button or "reset" button reset the properties (this button does not affect the darkmode)

### The Files panel
![The Files panel](https://github.com/lsonnino/gameOfLife/blob/master/screenshots/filesPanel.jpg "Files panel") The Files panel allows the user to save, export, import and open some game configurations. It contains from top to bottom, left to right:
* A list containing all the saved games. By dobble-clicking onn a game, the game will open
* The "+" button saves the game in the saved games
* The "-" button removes the selected game from the saved games
* The "<" button or "import" button allows the user to import a game
* The ">" button or "export" button allows the user to export the selected game

## Sponsorship
*The Game of Life* is open source software. Ongoing development is made possible by generous contributions.

## Credits
 - [Lorenzo Sonnino](https://github.com/lsonnino)

# License
[The GPLv3 license](https://www.gnu.org/licenses/gpl-3.0.en.html)
