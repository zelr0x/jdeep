package io.github.zelr0x.jdeep;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.github.zelr0x.jdeep.Copier.deepCopy;

public class CopierSerializableTest {
    private static final PhoneNumber johnPhone =
            new PhoneNumber("+12345678900", Provider.JERIZON);
    private static SerializablePerson john;
    private static SerializablePerson jess;
    private static SerializablePerson johnCopy;

    @Before
    public void setUp() {
        john = new SerializablePerson("John", "Jeek", johnPhone);
        jess = new SerializablePerson("Jess", "Newjess",
                new PhoneNumber("+19876543210", Provider.JTNT));
        john.addFriend(jess);
        jess.addFriend(john);
        johnCopy = deepCopy(john);
    }

    @Test
    public void serializableDeepCopyEqualityTest() {
        Assert.assertEquals("Original and copy are equal", john, johnCopy);
    }

    @Test
    public void serializableDeepCopyMutabilityEffectTest() {
        john.removeNumber(johnPhone);
        Assert.assertNotEquals("Copy is affected by mutation of original",
                john, johnCopy);
    }

    @Test
    public void serializableDeepCopyBehaviorTest() {
        johnCopy.removeFriend(jess);
        final var expected = new SerializablePerson(
                "John", "Jeek", johnPhone);
        Assert.assertEquals("Failed invoking a method on a deep copy",
                expected, johnCopy);
    }
}
