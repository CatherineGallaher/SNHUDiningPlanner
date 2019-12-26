# SNHUDiningPlanner

The purpose of this project was to provide easier access to Southern New Hampshire University's dining plan information for SNHU students. 
The current set up (as of 2019) only lists a current balance and transaction history.  
This project scrapes data from the website, using the user's SNHU login credentials, and stores it in a mySQL database.  
This information is then pulled from the database and parsed out to do several calculations on the data, including amount spent per
month, average amount spent per day, average amount spent per meal type (with customizable meal times), esimated initial balance (reverse
engineered from the transaction data), and estimated amount left at the end of the semester (calculated using linear regression).
We set up UI's for both an android app and a web app, depending on the user's preference. 

NOTICE: the server has since been shut down.  This code is no longer able to be run without major renovations to the server connection

Also note that the data scraping is not fully functioning on the Android app, though it works fine on the web app. We used Jsoup on the 
Android app and it, thus far, only logs on but does not actually scrape from the site.  We used HTMLUnit on the web app and it functions 
fully.  

Additionally, several modification need to be made to the database (which is, again, hosted on a server which is no longer running) to 
allow current balance data to be passed from the database to the app.  
