interface Payable {
    double getPaymentAmount();
}

class Person implements Payable, Comparable<Person> {
    private static int count = 0;
    private final int id;
    private String name;
    private String surname;

    public Person() {
        this.id = ++count;
        this.name = "Unknown";
        this.surname = "Unknown";
    }

    public Person(String name, String surname) {
        this.id = ++count;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return "Student"; // Default position for Person
    }

    public String toString() {
        return id + ". " + name + " " + surname;
    }

    public double getPaymentAmount() {
        return 0.0; // Default payment amount for Person
    }

    public int compareTo(Person other) {
        return Double.compare(this.getPaymentAmount(), other.getPaymentAmount());
    }
}

class Employee extends Person {
    private String position;
    private double salary;

    public Employee() {
        super();
        this.position = "Unknown";
        this.salary = 0.0;
    }

    public Employee(String name, String surname, String position, double salary) {
        super(name, surname);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getPaymentAmount() {
        return salary; // Payment amount for Employee is their salary
    }

    public String toString() {
        return "Employee: " + super.toString();
    }
}

class Student extends Person {
    private double gpa;
    private static final double STIPEND = 36660.0;

    public Student() {
        super();
        this.gpa = 0.0;
    }

    public Student(String name, String surname, double gpa) {
        super(name, surname);
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getPaymentAmount() {
        return (gpa > 2.67) ? STIPEND : 0.0; // Stipend based on GPA
    }

    public String toString() {
        return "Student: " + super.toString();
    }
}

import java.util.*;

public class MyApplication {

    public static void printData(Iterable<Payable> items) {
        for (Payable p : items) {
            System.out.printf("%s earns %.2f tenge%n", p.toString(), p.getPaymentAmount());
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("John", "Lennon", "Manager", 27045.78);
        Employee e2 = new Employee("George", "Harrison", "Developer", 50000.00);
        Student s1 = new Student("Ringo", "Starr", 2.5); // No stipend
        Student s2 = new Student("Paul", "McCartney", 3.5); // Gets stipend

        ArrayList<Payable> people = new ArrayList<>();
        people.add(e1);
        people.add(e2);
        people.add(s1);
        people.add(s2);

        Collections.sort(people); // Sort based on payment amount

        printData(people); // Print the sorted data
    }
}