package io.github.zelr0x.jdeep;

import java.util.HashSet;
import java.util.Set;

public class Person {
    private String firstName;
    private String lastName;
    private final Set<PhoneNumber> phoneNumbers;
    private final Set<Person> friends;

    public Person(final String firstName, final String lastName,
                  final Set<PhoneNumber> phoneNumbers,
                  final Set<Person> friends) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.friends = friends;
    }

    public Person(final String firstName, final String lastName,
                  final Set<PhoneNumber> phoneNumbers) {
        this(firstName, lastName, phoneNumbers, new HashSet<>());
    }

    public Person(final String firstName, final String lastName) {
        this(firstName, lastName, new HashSet<>());
    }

    public Person(final String firstName, final String lastName,
                  final PhoneNumber phoneNumber) {
        this(firstName, lastName);
        addNumber(phoneNumber);
    }

    public void addFriend(final Person friend) {
        this.friends.add(friend);
    }

    public boolean isFriendOf(final Person person) {
        return this.friends.contains(person);
    }

    public void addNumber(final PhoneNumber number) {
        this.phoneNumbers.add(number);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Set<Person> getFriends() {
        return friends;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
