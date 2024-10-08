package br.com.erivelton.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVO2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    private Date birthDate = new Date();

    public PersonVO2() {}

    public Long getKey() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVO2 personVO2)) return false;
        return Objects.equals(getKey(), personVO2.getKey()) && Objects.equals(getFirstName(), personVO2.getFirstName()) && Objects.equals(getLastName(), personVO2.getLastName()) && Objects.equals(getAddress(), personVO2.getAddress()) && Objects.equals(getGender(), personVO2.getGender()) && Objects.equals(getBirthDate(), personVO2.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getFirstName(), getLastName(), getAddress(), getGender(), getBirthDate());
    }
}
