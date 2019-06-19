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

import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;

import org.junit.Test;

import static bisq.api.http.exceptions.ExceptionMapperTestHelper.assertInstanceOfValidationErrorMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



import com.github.javafaker.Faker;
import javax.ws.rs.core.Response;

public class JsonMappingExceptionMapperTest {

    @Test
    public void toResponse_always_statusIs422() {
        //        Given
        ExceptionMappers.JsonMappingExceptionMapper mapper = new ExceptionMappers.JsonMappingExceptionMapper();
        Faker faker = Faker.instance();
        String propertyName = faker.internet().slug();
        String prefix = faker.lorem().sentence();
        String suffix = faker.lorem().sentence();
        String msg = String.format("%s missing property '%s' %s", prefix, propertyName, suffix);

        //        When
        Response response = mapper.toResponse(new JsonMappingException(null, msg));

        //        Then
        assertEquals(422, response.getStatus());
    }

    @Test
    public void toResponse_always_bodyIsViolationErrorMessageWithNonEmptyErrorList1() {
        ExceptionMappers.JsonMappingExceptionMapper mapper = new ExceptionMappers.JsonMappingExceptionMapper();
        Faker faker = Faker.instance();
        String propertyName = faker.internet().slug();
        String prefix = faker.lorem().sentence();
        String suffix = faker.lorem().sentence();
        String msg = String.format("%s missing property '%s' %s", prefix, propertyName, suffix);
        String expectedError = String.format("%s may not be null", propertyName);

        //        When
        Response response = mapper.toResponse(new JsonMappingException(null, msg));

        //        Then
        Object entity = response.getEntity();
        assertNotNull(entity);
        assertInstanceOfValidationErrorMessage(entity);
        ValidationErrorMessage payload = (ValidationErrorMessage) entity;
        assertEquals(List.of(expectedError), payload.getErrors());
    }

    @Test
    public void toResponse_always_bodyIsViolationErrorMessageWithNonEmptyErrorList2() {
        ExceptionMappers.JsonMappingExceptionMapper mapper = new ExceptionMappers.JsonMappingExceptionMapper();
        String msg = "Unrecognized error ";
        String expectedError = "Unable to recognize payload";

        //        When
        Response response = mapper.toResponse(new JsonMappingException(null, msg));

        //        Then
        Object entity = response.getEntity();
        assertNotNull(entity);
        assertInstanceOfValidationErrorMessage(entity);
        ValidationErrorMessage payload = (ValidationErrorMessage) entity;
        assertEquals(List.of(expectedError), payload.getErrors());
    }
}
