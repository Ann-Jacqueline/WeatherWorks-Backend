# WeatherWorks-Backend

The backend part of the WeatherWorks Weather App handles all server-side requests from the frontend and includes three entities:

## Entities

### CitySearch

This entity is primarily responsible for communication with the WeatherEntry and WeatherSearch components, handling POST/GET requests.

### CityHistory

This entity manages the creation and maintenance of a city history. It communicates with the WeatherAccount component in the frontend, 
processing POST/GET and DELETE requests.

### CityHistoryOwner

This entity manages all user requests and assigns city histories to the user as the owner of the City History. 
It communicates with the WeatherStartPage in the frontend to receive or provide all user data.

### ErrorController

Independently of the entities mentioned above, the ErrorController handles all client-side errors (HTTP 400 errors) 
and server-side errors (HTTP 500 errors).

## Topic: Weather App

The Weather App is designed to pull external data (via external APIs) and store and manage user input.

## App Functionalities / Use Cases

1. Provide an overview of a city using an external API key.
2. Save user input through a city search function.
3. Pin a default city.
4. Change the default city.
5. Manage locations: delete locations.
6. Settings: unit preferences (°C or °F).
7. Error handling for incorrect city searches.
8. Multiuser functionality via login and log out functionality.

## Interlinked Communication

The backend component is linked to the PostgreSQL database and communicates with the frontend component 
via the Frontend management store.ts 
to handle data retrieval, data flow management, and data exchange. 
This interlinked system ensures seamless data management between the frontend and backend.

[Frontend Repository Link](https://github.com/Ann-Jacqueline/WeatherWorks-Frontend)

## About the Project

The app was developed as part of my Web Technologies module of the third semester 
at the University of Applied Sciences for Technology and Economics Berlin (HTW Berlin).
