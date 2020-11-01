# Pestroutes-test

The main goal of this application is to test the following functionalities:
  - Login into Pestroutes domain
  - Create a customer with first, last name and address
  - Validate the customer deatils in the overview tab
### Tech

This Maven project uses the following dependencies:

* Junit
* cucumber-junit
* selenium-java - Using Chrome Web driver
* cucumber-java

### Executing the tests

* Clone the repository from git
* Add the repo to your IDE
* If it is a mac , make sure it allows chrome driver to execute. Otherwise, 
go to system preferences -> security&privacy -> Allow apps downloaded from -> click on allow.
* Inorder to run the project we can use
```sh
$ mvn clean install
```
or
```sh
$ mvn test
```
