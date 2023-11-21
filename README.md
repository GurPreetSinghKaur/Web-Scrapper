# Web-Scrapper
A price comparison website which displays Apple smartphones and their different prices.
it facilitates the process of looking for the best price when purchasing an iPhone. This is done by first scraping data from 5 different e-commerce websites, then storing the data in the database and finally displaying it to the user through the web application.



Web scraping consists of 5 Java threads, with each thread being a website. When the Java
application is run, the threads start simultaneously and are put to sleep after they finish
scraping. The application can be stopped by typing “q” in the console.

## Table of Contents


## Technologies
This project is built using the following technologies:

- Java, Maven, Spring, Hibernate, MySQL (for web scraping and backend)
- JavaScript, NodeJs, HTML, CSS (for the web application)
- Deployed using Railway.app (MySQL and Java containers)  

## Features 
* Web Scraping: The app scrapes iPhone prices from five predefined websites. This ensures you have access to real-time pricing information.

* Data Storage: The scraped data is stored in a database for easy access and retrieval. (Deployed in Railway.app)

* Price Comparison: The app calculates and displays the cheapest iPhone retailer among the five websites, simplifying purchase decisions. 

* User-Friendly Interface: The web interface is designed for user-friendliness, allowing the user to quickly and easily view price comparisons as well as access the specific iPhone in the particular website.


## Project Structure

- `/Web-Scraper`: Contains the Java source code for scraping data.
- `RestfulAPI/`: Restful API directory can be found [here](https://github.com/GurPreetSinghKaur/RestfulAPI)
- `/assets`: Contains public assets, e.g., images.  

