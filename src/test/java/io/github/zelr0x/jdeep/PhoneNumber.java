package io.github.zelr0x.jdeep;

public class PhoneNumber {
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
}
