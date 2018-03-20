Dynamic Sort

We can sort the given Person class objects, based on any one field in a dynamic way. The following are the approaches.
1.	Implementing a custom dynamic comparator using Reflections.
This approach is an overkill for this example considering the effect on performance while using Reflections due to runtime evaluation of method name and increased number of method calls.And we need to take special care to handle runtime exceptions due to inconsistant method signatures. I would prefer this approach only if we are trying to create a generic API like Apache BeanComparator.
2.	Using Apache BeanComparator Implementation which internally uses Reflections.
This approach is an overkill for this example considering the effect on performance while using Reflections. I would prefer this approach only if we are trying to sort many type of objects with standard method signature for setters and getters.
3.	Generate a separate Comparator for each field.
I prefer this approach for this example, because we are trying to sort only one class which have few fields to sort upon. This approach results in better performance compared to above two cases due to compile time evaluation of Comparators.




I created a Spring Boot Web app to demonstrate my Implementation.

To run the app:

•	Install Java 8, Maven

•	Download the source code from GitHub: https://github.com/Satyanandana/Person-Sorter

•	Navigate to the folder

•	Open the Command prompt and run:       mvn spring-boot: run

•	Click http://localhost:8080/sorter/people?sortField=ssn&ascending=true

•	Click http://localhost:8080/sorter/people?sortField=ssn&ascending=false

•	Click http://localhost:8080/sorter/people?sortField=dateOfBirth&ascending=true

•	Click http://localhost:8080/sorter/people?sortField=dateOfBirth&ascending=false

Check the sorter Implementation : https://github.com/Satyanandana/Person-Sorter/blob/master/src/main/java/com/service/sort/service/PersonsSorter.java	

I used the third approach to Implement the Sorter.
I used a Map to hold the Comparators instead of using a Factory method. To eliminate if conditional statements.				
