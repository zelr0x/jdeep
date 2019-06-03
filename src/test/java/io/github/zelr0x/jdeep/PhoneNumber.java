package io.github.zelr0x.jdeep;

import java.io.Serializable;
import java.util.Objects;

public class PhoneNumber implements Serializable {
    private final String value;
    private Provider provider;

    public PhoneNumber(final String value, final Provider provider) {
        this.value = value;
        this.provider = provider;
    }

    public PhoneNumber(final String value) {
        this.value = value;
        this.provider = Provider.UNKNOWN;
    }

    public String get() {
        return value;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(final Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value) &&
                provider == that.provider;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, provider);
    }
}
