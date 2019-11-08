package bisq.api.http.exceptions;


import static org.junit.Assert.assertTrue;

public class ExceptionMapperTestHelper {

    public static void assertInstanceOfValidationErrorMessage(Object object) {
        String message = String.format("Response payload is not an instance of %s: %s", ValidationErrorMessage.class.getCanonicalName(), object.getClass().getCanonicalName());
        assertTrue(message, object instanceof ValidationErrorMessage);
    }
}
