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

import bisq.core.exceptions.NotFoundException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



import com.github.javafaker.Faker;
import javax.ws.rs.core.Response;

public class NotFoundExceptionMapperTest {

    @Test
    public void toResponse_always_statusIs404() {
        //        Given
        ExceptionMappers.NotFoundExceptionMapper mapper = new ExceptionMappers.NotFoundExceptionMapper();
        String expectedMessage = Faker.instance().chuckNorris().fact();

        //        When
        Response response = mapper.toResponse(new NotFoundException(expectedMessage));

        //        Then
        assertEquals(404, response.getStatus());
    }

    @Test
    public void toResponse_always_bodyIsString() {
        //        Given
        ExceptionMappers.NotFoundExceptionMapper mapper = new ExceptionMappers.NotFoundExceptionMapper();
        String expectedMessage = Faker.instance().chuckNorris().fact();

        //        When
        Response response = mapper.toResponse(new NotFoundException(expectedMessage));

        //        Then
        Object entity = response.getEntity();
        assertEquals(expectedMessage, entity);
    }
}
