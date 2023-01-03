Game.java is your main

Disclaimer: Have done limited testing since Refactoring NumberGame.java to Game.java
I believe all the references to NumberGame.java have been translated to Game.java.


[Russian Roulette]
I think it's stable now. Through 5 rounds of testing, Computer made logical guesses. Very excited.

Okay.. so further testing shows Computer logic needs more work. Store its guesses in an array to avoid repeats.

QOL Plans:
(FINISHED) Use an array to print out the log of previous guesses and clear it once the round is over

Bugs:
Computer will repeat guesses. (ie, when guessing 100, it will guess 50 based on algorithm.

Plans to fix:
Look closer at the computer guessing algorithm. Brainstorm a way for computer to check if its next guess will be too high/too low
based on previous boolean (cpuTooHigh) values.

[GameEngine.java]
Very excited about this.
The experimental feature for an enhanced "coin flip" is fully tested and working.
Will finalize as a feature to return a boolean value for the result. Will take int values to determine the min/max of times the arrow changes directions


[User input now needs to stay in range]
-If user input is out of range, an error message appears "reminding" them what the range is
-If the user is having trouble picking a number in the range, the terminal prints out ALL numbers in range

[Optimization]
-Removed NumberGuesser.postGameScreen(); from NormalGame/ComputerGuesses.youWin/Lose() methods. 
-.postGameScreen() is only called upon in the main method now for better optimization.

[Stability]
-Stable enough to show off to friends/peers/family.

[Bug Fixes]
-a lot.. lost track