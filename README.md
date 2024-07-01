# backend-webtech

Der Backend-Teil der WeatherWorks Wetter-App kümmert sich serverseitig um alle Anfragen des Frontends und umfasst drei Entitäten:

CitySearch: Diese Entität ist primär für die Kommunikation mit der WeatherEntry-
und WeatherSearch-Komponente zuständig und nimmt POST/GET-Anfragen entgegen.

CityHistory: Diese Entität ist für die Erstellung und Verwaltung eines Städteverlaufs verantwortlich.
Sie kommuniziert im Frontend mit der WeatherAccount-Komponente und verarbeitet POST/GET sowie DELETE-Anfragen.

CityHistoryOwner: Diese Entität verwaltet alle Benutzeranfragen und dient der Zuordnung der Städteverläufe. S
ie kommuniziert mit der WeatherStartPage im Frontend, um alle Benutzerdaten zu empfangen oder bereitzustellen.

Der ErrorController behandelt, unabhängig von den oben genannten Entitäten, 
alle clientseitigen Fehler (HTTP 400er Fehler) und serverseitigen Fehler (HTTP 500er Fehler).

## Thema: Wetter App
## Die Wetter-App soll externe Daten ziehen (durch externe APIs) und User Input speichern und verwalten
## 7 tasks/ Use Cases: 
### 1: Eine Übersicht einer Stadt mittels externer API KEY 
### 2: User Input speichern durch: Möchte ich hin (searching funktion) 
### 3: Default stadt Pinnen ch
### 4: Default Stadt ändern 
### 5:(Standorte verwalten): Standorte Löschen a
### 6: Einstellungen (Einheit °C oder °F) 
### 7. Error handling bei fehlerhafter Stadtsuche.
