# Web-Scrapper
A price comparison website which displays Apple smartphones and their different prices.
it facilitates the process of looking for the best price when purchasing an iPhone. This is done by first scraping data from 5 different e-commerce websites, then storing the data in the database and finally displaying it to the user through the web application.



Web scraping consists of 5 Java threads, with each thread being a website. When the Java
application is run, the threads start simultaneously and are put to sleep after they finish
scraping. The application can be stopped by typing “q” in the console.

## Table of Contents

1. [Technologies](#technologies)
2. [Features](#features)
3. [Screenshots](#screenshots)
4. [Testing](#testing)
5. [Project Structure](#project-structure)
6. [License](#license)
7. [Contact](#contact)


## Technologies
This project is built using the following technologies:

- Java, Maven, Spring, Hibernate, MySQL (for web scraping and backend)
- JavaScript, NodeJs, HTML, CSS (for the web application)
- Deployed using Railway.app which uses Docker (MySQL and Java containers)  

## Features 
* Web Scraping: The app scrapes iPhone prices from five predefined websites. This ensures you have access to real-time pricing information.

* Data Storage: The scraped data is stored in a database for easy access and retrieval. (Deployed in Railway.app)

* Price Comparison: The app calculates and displays the cheapest iPhone retailer among the five websites, simplifying purchase decisions. 

* User-Friendly Interface: The web interface is designed for user-friendliness, allowing the user to quickly and easily view price comparisons as well as access the specific iPhone in the particular website.

### Screenshots

- Home Page
![Screenshot of Home Page](https://github.com/GurPreetSinghKaur/Web-Application/blob/master/img/Home.jpg)

- Search Feature
![Screenshot of Search Feature](https://github.com/GurPreetSinghKaur/Web-Application/blob/master/img/Search.jpg)

- View all Phones
![Screenshot of View all Page](https://github.com/GurPreetSinghKaur/Web-Application/blob/master/img/Phones.jpg)

## Testing

This project uses Junit 5 testing with Maven and Mocha/Chai for the REST API. 

- HibernateTest.java -Initialise a session factory before and executes tests in a certain order.
![Screenshot of HibernateTest.java](https://github.com/GurPreetSinghKaur/Web-Application/blob/master/img/HibernateTest.jpg)

- UtilityTest.java -Test Java class which is used in the scrappers.
![Screenshot of UtilityTest.java](https://github.com/GurPreetSinghKaur/Web-Application/blob/master/img/UtilityTest.jpg)

- API.test.js -REST API Testing with Mocha/Chai
![Screenshot of API.test.js](https://github.com/GurPreetSinghKaur/Web-Application/blob/master/img/API_test.jpg)
  


## Project Structure

- `/Web-Scraper`: Contains the Java source code for scraping data.
- `/Web-Scraper/src/test/java/en/gurpreet/cst3130`: Contains test files for Java and JavaScript
- `RestfulAPI/`: Restful API directory can be found [here](https://github.com/GurPreetSinghKaur/RestfulAPI)
- `Web-Application/`: Web-Application directory can be found [here](https://github.com/GurPreetSinghKaur/Web-Application)


## License

This project is licensed under the MIT Licence. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or inquiries, feel free to reach out:
 - GurPreetSinghKaur@Outlook.com
 
