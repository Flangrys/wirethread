package com.wirethread.core.testing;

public final class TestingContext extends ContextProvider {

    public static boolean isUnsafePerformThreadsOperations() {
        return true;
    }

    public static boolean isUnsafePerformRegistryOperations() {
        return true;
    }
}
