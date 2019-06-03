package io.github.zelr0x.jdeep;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CopyablePerson implements DeepCopyable<CopyablePerson> {
    private String firstName;
    private String lastName;
    private final Set<PhoneNumber> phoneNumbers;
    private final Set<CopyablePerson> friends;

    public CopyablePerson(final String firstName, final String lastName,
                          final Set<PhoneNumber> phoneNumbers,
                          final Set<CopyablePerson> friends) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.friends = friends;
    }

    public CopyablePerson(final String firstName, final String lastName,
                          final Set<PhoneNumber> phoneNumbers) {
        this(firstName, lastName, phoneNumbers, new HashSet<>());
    }

    public CopyablePerson(final String firstName, final String lastName) {
        this(firstName, lastName, new HashSet<>());
    }

    public CopyablePerson(final String firstName, final String lastName,
                          final PhoneNumber phoneNumber) {
        this(firstName, lastName);
        addNumber(phoneNumber);
    }

    public void addFriend(final CopyablePerson friend) {
        this.friends.add(friend);
    }

    public void removeFriend(final CopyablePerson friend) {
        this.friends.remove(friend);
    }

    public void addNumber(final PhoneNumber number) {
        this.phoneNumbers.add(number);
    }

    public void removeNumber(final PhoneNumber number) {
        this.phoneNumbers.remove(number);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CopyablePerson person = (CopyablePerson) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(phoneNumbers, person.phoneNumbers) &&
                friends.hashCode() == person.friends.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumbers);
    }

    @Override
    public CopyablePerson deepCopy() {
        final var phoneNumbers = new HashSet<>(this.phoneNumbers);
        final var friends = new HashSet<>(this.friends);
        return new CopyablePerson(this.firstName, this.lastName,
                phoneNumbers, friends);
    }
}
