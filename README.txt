The application calculates a distance between two cities.
Receives two formats:
XML for upload cities, coordinates and distances between cities.
JSON receives two lists of cities to find results in DB or calculate.

Find examples of JSON and XML in src/testData

DataBase : PostgreSQL
url=jdbc:postgresql://localhost:5432/cities_db
username=postgres
password=postgres

Database holds two entities:
		City
			Name
			Latitude
			Longitude
		Distance
			From city
			To city
			Distance

1. GET http://localhost:8080/api/cities
        src/testData/testDataSQL.sql
returns JSON with ID and city names from DB

2. GET http://localhost:8080/api/distances
        consumes="application/json"
        src/testData/testDataJSON.json
        Input:
        				Calculation Type: <Crowflight, DistanceMatrix, All>
        				From City: <List of cities>
        				To City: <List of cities>
returns JSON with cities and distances (from DB or calculated)

3.POST http://localhost:8080/api/distances/uploadXML
        consumes="application/xml"
        src/testData/testDataXML.xml
returns empty body (upload to DB)

