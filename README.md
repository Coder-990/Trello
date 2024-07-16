# SAMPLE TRELLO APP

Goal is to build a sample trello app

### Internal storage
* [H2]()
    * [Web console: http://localhost:8080/h2-console]()
    * [JDBC URL: jdbc:h2:file:./data/trellodb]()

### Migration tool
* [Flyway]()

### Run locally
* [mvn spring-boot:run]()

### Test locally with http requests
* [Path: src/test/resources/http_requests/card-requests.http]()
* [Path: src/test/resources/http_requests/cardLists-requests.http]()
* [Path: src/test/resources/http_requests/member-requests.http]()
* [Path: src/test/resources/http_requests/board-requests.http]()

### Build image
* [mvn spring-boot:build-image]()
