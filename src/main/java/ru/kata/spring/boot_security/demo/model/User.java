package ru.kata.spring.boot_security.demo.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 25, message = "Имя должно быть в диапазоне от 2 до 25 символов")
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;
    @NotEmpty(message = "Поле Email не должно быть пустым")
    @Email(message = "Email должен быть валидным")
    @Column(name = "email")
    private String email;
    @Min(value = 0, message = "Возраст должен быть больше 0")
    @Column(name = "age")
    private byte age;

    public User() {
    }

    public User(String name, String department, String email, Byte age) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
