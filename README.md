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
* [Path: src/test/java/hr/ericsson/demo_trello/trello/http_requests/board-requests.http]()
* [Path: src/test/java/hr/ericsson/demo_trello/trello/http_requests/card-requests.http]()
* [Path: src/test/java/hr/ericsson/demo_trello/trello/http_requests/cardList-requests.http]()
* [Path: src/test/java/hr/ericsson/demo_trello/trello/http_requests/member-requests.http]()

### Build image
* [mvn spring-boot:build-image]()
