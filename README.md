# Simple Scheduling
Methods to implement:
-- Create student passing a object (Student) like parameter and a list of id classes, using the POST method. Returning the created Student, with Classes with their names (Inherited object).
-- Edit the student using the PUT method, sending the same parameters as the create option, using the PUT method. Returning the created Student, with Classes with their names (Inherited object).
-- Delete the student sending the id, using the DELETE method. Returning the created Student, with Classes with their names (Inherited object).
-- Create classes passing a object (Class) like parameter and a list of id students, using the POST method. Returning the created Class, with Students with their names (Inherited object).
-- Edit the class using the PUT method, sending the same parameters as the create option, using the PUT method. Returning the created Class, with Students with their names (Inherited object).
-- Delete the class sending the code, using the DELETE method. Returning the created Class, with Students with their names (Inherited object).
-- Searching students by id using the GET Method.
-- Searching class by code using the GET Method.
-- For the ids, codes is going to use an set to receive unique elements.
-- Like is going to be simulated the student and class source, is necessary to store those on a map.
-- For the associated fields we going to have: for students the concatenation of the firstName and the lastName fields to search by full name. For Class we is going to be employed the title.
-- For the above searches is going to be used an map on each case.
