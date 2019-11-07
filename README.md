## Rock paper scissors game

## Features
* Spring Boot 
* REST API
* Storage: in-memory
* Swagger UI

> Game rules
 - Rock beats Scissors
 - Scissors beats Paper
 - Paper beats Rock
 
 > Limitations:
 - Player vs Computer mode only

 > Example flow:
 - Create a `game` and obtain its `id` :
 ```POST /games?playerName=player```   Or you can utilize Swagger to easily query necessary endpoints
 - Start playing with computer by specifying your `choice` which is either `ROCK, PAPER, SCISSORS`. Assuming that you got `id=0` from previous endpoint:
 ```POST /games/0?choice=PAPER```
 - Keep playing until one of you get score of 3. In that case `gameStatus` will change from `PLAYING` to either `PLAYER_WON` or `COMPUTER_WON` depending on who won. Further requests will result in 409 error as this particular game is now over. 
 - You can get you current/prevous game by ```GET /games{id}``` 

## Running application
```
./gradlew clean bootRun
```


## Swagger REST documentation:
Once app has started, go to the following endpoint for API documentation  
```
http://localhost:8080/swagger-ui.html#/game-controller
```
