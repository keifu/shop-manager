Please use your Google Geocoding API key and place it in src/main/resources/application.property

To run the application: 

mvn spring-boot:run

or

run the class com.shopmanager
(make sure port 8080 is free)

************************************************************************

URL add new shop:

http://localhost:8080/shop

Example payload:

{
"shopName": "Nandos",
"shopNumber": "3 Armada Way",
"postCode": "E6 7ER"
}


************************************************************************
URL to get nearest shop:

http://localhost:8080/shop?latitude=51.505145&longitude=0.019541


************************************************************************

URL to get list of shops added:

http://localhost:8080/shoplist
