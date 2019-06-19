/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.api.http.exceptions;

import bisq.api.http.model.ValidationErrorMessage;

import bisq.core.payment.PaymentAccount;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.type.SimpleType;

import java.util.List;

import org.junit.Test;

import static bisq.api.http.exceptions.ExceptionMapperTestHelper.assertInstanceOfValidationErrorMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



import com.github.javafaker.Faker;
import javax.ws.rs.core.Response;

public class InvalidTypeIdExceptionMapperTest {

    @Test
    public void toResponse_always_statusIs501() {
        //        Given
        ExceptionMappers.InvalidTypeIdExceptionMapper mapper = new ExceptionMappers.InvalidTypeIdExceptionMapper();
        SimpleType baseType = SimpleType.constructUnsafe(PaymentAccount.class);

        //        When
        Response response = mapper.toResponse(new InvalidTypeIdException(null, null, baseType, null));

        //        Then
        assertEquals(422, response.getStatus());
    }

    @Test
    public void toResponse_always_bodyIsViolationErrorMessageWithNonEmptyErrorList() {
        //        Given
        ExceptionMappers.InvalidTypeIdExceptionMapper mapper = new ExceptionMappers.InvalidTypeIdExceptionMapper();
        SimpleType baseType = SimpleType.constructUnsafe(PaymentAccount.class);
        String typeId = Faker.instance().internet().slug();
        String expectedError = String.format("Unable to recognize sub type of %s. Value '%s' is invalid.", PaymentAccount.class.getSimpleName(), typeId);

        //        When
        Response response = mapper.toResponse(new InvalidTypeIdException(null, null, baseType, typeId));

        //        Then
        Object entity = response.getEntity();
        assertNotNull(entity);
        assertInstanceOfValidationErrorMessage(entity);
        ValidationErrorMessage payload = (ValidationErrorMessage) entity;
        assertEquals(List.of(expectedError), payload.getErrors());
    }
}
