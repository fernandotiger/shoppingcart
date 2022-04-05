# shoppingcart
Demonstrating a simple shopping cart - it was used https://start.spring.io to generate the this project

Basically you just need to pull the project and import it as Maven Project, then it should be good to be executed.

On the launching class ShopingApplication I have included an Sysout simple flow to check some results and also you can see some cases on ShopingApplicationTests
where you can find some methods to check the results.

I could use 2 design pattern (Builder and State) but to keep simple I decided to create the objects using their fields constructors and Enums.

The following code uses connection to H2 database + JPA. You can find the H2 configuration in the properties file.

The method findTopByOrderByClientIdDesc from cartRepository, I ma using native query only to demonstrate possibilities as I could also use the conventiona HQL.

Some improvements that could be done is to avoid register products with same name and same category; transform the controllers into web controllers(to receive web requests, search product/cients,cart by id, etc)

To build this project I have spend between 4 - 5 hours working a bit on different days
