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

import java.util.List;

import org.junit.Test;

import static bisq.api.http.exceptions.ExceptionMapperTestHelper.assertInstanceOfValidationErrorMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



import javax.ws.rs.core.Response;

public class ExperimentalFeatureExceptionMapperTest {

    @Test
    public void toResponse_always_statusIs501() {
        //        Given
        ExceptionMappers.ExperimentalFeatureExceptionMapper mapper = new ExceptionMappers.ExperimentalFeatureExceptionMapper();

        //        When
        Response response = mapper.toResponse(new ExperimentalFeatureException());

        //        Then
        assertEquals(501, response.getStatus());
    }

    @Test
    public void toResponse_always_bodyIsViolationErrorMessageWithNonEmptyErrorList() {
        ExceptionMappers.ExperimentalFeatureExceptionMapper mapper = new ExceptionMappers.ExperimentalFeatureExceptionMapper();
        String expectedError = "Experimental features disabled";

        //        When
        Response response = mapper.toResponse(new ExperimentalFeatureException());

        //        Then
        Object entity = response.getEntity();
        assertNotNull(entity);
        assertInstanceOfValidationErrorMessage(entity);
        ValidationErrorMessage payload = (ValidationErrorMessage) entity;
        assertEquals(List.of(expectedError), payload.getErrors());
    }
}
