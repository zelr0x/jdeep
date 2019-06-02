package io.github.zelr0x.jdeep;

import org.junit.Assert;
import org.junit.Test;

import static io.github.zelr0x.jdeep.Copier.deepCopy;

public class CopierTest {
    @Test
    public void deepCopyTest() {
        final var john = new Person("John", "Jeek",
                new PhoneNumber("+12345678900", Provider.JERIZON));

        final var jess = new Person("Jess", "Newjess",
                new PhoneNumber("+19876543210", Provider.JTNT));

        john.addFriend(jess);
        jess.addFriend(john);

        final var john2 = deepCopy(john);

        Assert.assertEquals(john, john2);
    }
}
