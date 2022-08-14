# Deck Of Cards
Spring application for a deck of cards

Diagram and initial design of the cards with implementation
![Screenshot 2022-08-14 152350](https://user-images.githubusercontent.com/31741107/184551803-a5001aaf-0c7a-4c25-9053-1401c4aa7f91.png)

Initial template for the repository (sole purpose was to get Swagger UI setup properly and proper CORS validation)
All other classes/packages were deleted
https://github.com/rahul-ghadge/spring-boot-h2-crud

# Description 
This is a demo of a Spring Data JPA application that uses CRUD operations to write and 
fetch data from a database.  This particular example uses in-memory H2 databases.

# How to run the application 
This application uses Swagger UI to facilitate testing the RESTful functions. To run this application, import the project to an IDE (i.e. IntelliJ) and run the DeckOfCardsApplication.java class.  Once the application is booted, open a browswer and type localhost:8080.  The Swagger UI should appear and you should be able to test all functions. 
![swag](https://user-images.githubusercontent.com/31741107/184551840-646e9d92-e669-4d64-9c13-aebaffc7eef8.png)

Example of a deal card function to a player:
![deal](https://user-images.githubusercontent.com/31741107/184551885-2fc989db-0b49-4d7b-8b8f-68cafb3c9851.png)

# Application Design
The application uses Spring Data JPA with combination of H2 as a database platform.  The database can be manipulated via the application.properties file.
The player and the game are persisted to the database.  The game itself is manipulated all in memory.  If a game is shut down, all decks, hands and any other game properties are lost.  As I designed this application, I believe that it would be more beneficial as those actions would be accessed often and would impact performance if cards, hands and deck were persisted to the database.  It was a design choice to keep as much as possible in memory.  I used controllers for the player and game to persist data and manipulate game properties to send back the information as JSON to the front end.

# Unit Test, Integration Test
Integration tests were written for the persistance of the application.  The Game and Player CRUD operations were tested to ensure that data was properly written and fetched from the databases. 

Any other game logic were either tested in Unit Tests.

I wanted to get some Selenium testing done, but I could not finish the front end properly as there were multiple bugs.

# Improvements
- More validation for valid data can be done.
- A lot of loops are used to run through decks/hands to produce data, better algorithms may be produced for this.
- Code could be reviewed and decoupling functions may be done.
- The front end was not fully complete and functional, this would be the next step to complete the front-end. (in a separate repository).

# Known Bugs
There are no validations for empty strings/invalid characters when entering new game/player which can cause issues and/or prone to malicious attacks.
The deal, add deck and get suit/card count seem to be pulling called even if the proper shoe is being called to manipulate the deck/cards.



