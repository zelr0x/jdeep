package io.github.zelr0x.jdeep;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.github.zelr0x.jdeep.Copier.deepCopy;

public class CopierCopyableTest {
    private static final PhoneNumber johnPhone =
            new PhoneNumber("+12345678900", Provider.JERIZON);
    private static CopyablePerson john;
    private static CopyablePerson jess;

    @Before
    public void setUp() {
        john = new CopyablePerson("John", "Jeek", johnPhone);
        jess = new CopyablePerson("Jess", "Newjess",
                new PhoneNumber("+19876543210", Provider.JTNT));
        john.addFriend(jess);
        jess.addFriend(john);
    }

    @Test
    public void copyableDeepCopyEqualityTest() {
        final var john2 = deepCopy(john);

        Assert.assertEquals("Original and copy are equal", john, john2);
    }

    @Test
    public void copyableDeepCopyMutabilityEffectTest() {
        final var john2 = deepCopy(john);
        john.removeNumber(johnPhone);

        Assert.assertNotEquals("Copy is affected by mutation of original", john, john2);
    }

    @Test
    public void copyableDeepCopyBehaviorTest() {
        final var john2 = deepCopy(john);
        john2.removeFriend(jess);
        final var expected = new CopyablePerson("John", "Jeek", johnPhone);

        Assert.assertEquals("Failed invoking a method on a deep copy", expected, john2);
    }
}
