package io.github.zelr0x.jdeep;

/**
 * This interface allows the implementor of it to provide the copying logic.
 * @param <T> the type of this object
 */
public interface DeepCopyable<T> {
    /**
     * Creates a deep deepCopy of this object.
     * @return deep deepCopy of this (implicit parameter)
     */
    T deepCopy();
}
