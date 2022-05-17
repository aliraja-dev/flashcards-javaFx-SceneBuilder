# Flashcards - JavaFX , Scene Builder, Serialized Persistence JAVA IO API

#### Main Scene that will show all the Decks in Flash Card App

![homepage](images/mainScene.png)

#### Add or Edit Cards in a Deck

![homepage](images/editorScene.png)

#### Play cards in a deck

![homepage](images/playerScene.png)

#### Show Answer

![homepage](images/showAnswer.png)

#### Add new Deck

![homepage](images/addNewDeck.png)

## Getting Started

### Revisions to launch.json

Using Vs code update the "vmArgs": "--module-path <path to jar files of Javafx> --add-modules=javafx.controls,javafx.fxml"

If you change the package name, update the fully qualified name of the main class using the revised package name eg: main.Main

keep this as this one, not Launch Main
"name": "Launch App"

### Main.java

Make sure you point to the correct starting point fxml file
getResource("main.fxml"));

### Import Referenced libraries of JavaFX

inside the Java Projects in VS Code, attach referenced libraries and select all the JAR files of the JavaFx. This will include them in your project. Another way is to add them to the lib folder of the project as well.
