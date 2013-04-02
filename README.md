appuigrader
===========

Leading UI&amp;Ux Engineer, Inspired by MIT's Android App Inventor. This system is a online virtual self-learning environment and assignments management system led by Prof. Steve Fickas at University of Oregon cooperating with MIT Center for Mobile Learning @ MIT Media Lab. Features include assignments management, and a scalable design that allows for integration with student information systems and authentication protocols. Construct with JavaScript , JSF, PrimeFace and MySQL. This project is still under construction and open-sourced.

enviroment configuration
========================

Follow the instructions in wiki page 

Make sure the following environment matched before moving into code trouble shooting

*  Dynamic Web Module 3.0
*  Java 1.6
*  Javascript 1.0
*  JavaServer Faces 2.0
*  Tomcat Server 6.0
*  mysql Ver 14.14 Distrib 5.5.28
*  Primefaces 3.3

Core Algorithms
===============

There are three files under path: src/java/mybeans/mydb/compare,  handling the whole data structure an comparing algorithm. 

<p><b>Component.java</p>
Defines the data unit that stores the JSON structure. 

<p><b>dataBuild.java</p>
Defines the algorithm of parsing the JSON file generated bu App Inventor. It converts the JSON object to the "Component" so that comparing function can apply them for comparison.

<p><b>compareUI.java</p> 
Handles the comparison. It has seperate functions to deal with different types of comparisons.

<p><b>splitList.java</p>
Splits the UI components to invisible components and visible components.


