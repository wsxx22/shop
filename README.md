# MakeUp Shop

### Table of Contents
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [Getting started](#getting-started)
* [Database](#database)
* [Default users](#default-users)
* [Heroku](#heroku)
* [Trello](#trello)

### Technologies
<b>Back-end</b>
* Java 8
* Maven
* Spring (Boot, Data)
* JPA
* H2 Database
* Lombok
* Slf4j
* Passay
* Jbcrypt
* Commons-lang3
* MapStruct pattern

<b>Front-end</b>
* Vaadin 8

<b>Tests</b>
* Spock

<b>Tools</b>
* IntelliJ IDEA

### Prerequisites
* Java 11
* Maven
* Lombok plugin

### Getting started
You can get a project from:
```
git clone https://github.com/wsxx22/shop.git
```
Run project and see my application on site: http://localhost:8080

### Database
Database can be found at http://localhost:8080/console

<b>Driver Class:</b> org.h2.Driver<br/>
<b>JDBC URL:</b> jdbc:h2:mem:shop_h2<br/>
<b>Username:</b> sa<br/>
<b>Password:</b> <br/>

### Default users

<b>Username:</b> admin<br/>
<b>Password:</b> admin<br/>
<b>Role:</b> admin<br/><hr>
<b>Username:</b> user<br/>
<b>Password:</b> user<br/>
<b>Role:</b> user<br/>

### Heroku

You can see app on heroku:
<a href="https://makeupshop.herokuapp.com" target="_blank">MakeUp Shop</a>

### Trello

Current tasks for this project:
<a href="https://trello.com/b/xIs6PpGv/shop" target="_blank">MakeUp Shop Tasks</a>
