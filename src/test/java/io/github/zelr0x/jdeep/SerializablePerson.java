package io.github.zelr0x.jdeep;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SerializablePerson implements Serializable {
    private String firstName;
    private String lastName;
    private final Set<PhoneNumber> phoneNumbers;
    private final Set<SerializablePerson> friends;

    public SerializablePerson(final String firstName, final String lastName,
                              final Set<PhoneNumber> phoneNumbers,
                              final Set<SerializablePerson> friends) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.friends = friends;
    }

    public SerializablePerson(final String firstName, final String lastName,
                              final Set<PhoneNumber> phoneNumbers) {
        this(firstName, lastName, phoneNumbers, new HashSet<>());
    }

    public SerializablePerson(final String firstName, final String lastName) {
        this(firstName, lastName, new HashSet<>());
    }

    public SerializablePerson(final String firstName, final String lastName,
                              final PhoneNumber phoneNumber) {
        this(firstName, lastName);
        addNumber(phoneNumber);
    }

    public void addFriend(final SerializablePerson friend) {
        this.friends.add(friend);
    }

    public void removeFriend(final SerializablePerson friend) {
        this.friends.remove(friend);
    }

    public boolean isFriendOf(final SerializablePerson person) {
        return this.friends.contains(person);
    }

    public void addNumber(final PhoneNumber number) {
        this.phoneNumbers.add(number);
    }

    public void removeNumber(final PhoneNumber number) {
        this.phoneNumbers.remove(number);
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

    public Set<SerializablePerson> getFriends() {
        return friends;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SerializablePerson that = (SerializablePerson) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phoneNumbers, that.phoneNumbers) &&
                friends.hashCode() ==  that.friends.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumbers);
    }
}
