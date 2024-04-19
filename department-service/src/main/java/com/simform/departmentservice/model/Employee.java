package com.simform.departmentservice.model;

//Record helps to reduce boilerplate code
//auto generates constructor, getters, toString and equals methods.
//It won't have any setters
//The class and its data members are declared as final by default. This implies that this class cannot be extended,
// i.e. cannot be inherited, and is immutable as well.
// Instance fields need to be present in the record's parameters, but, record can define static fields.
//Custom instance and static methods can be created inside a record
//ref:https://www.geeksforgeeks.org/what-are-java-records-and-how-to-use-them-alongside-constructors-and-methods/
public record Employee(Long id, Long departmentId, String name, int age, String position) {
}
