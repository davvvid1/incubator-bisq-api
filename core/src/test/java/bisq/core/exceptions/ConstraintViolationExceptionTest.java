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

package bisq.core.exceptions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class ConstraintViolationExceptionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void builderThrowIfAnyViolation_noViolations_doesNotThrow() {
        //        Given
        ConstraintViolationException.Builder builder = new ConstraintViolationException.Builder();

        //        When
        builder.throwIfAnyViolation();
    }

    @Test
    public void builderThrowIfAnyViolation_violationAdded_doesNotThrow() {
        //        Given
        ConstraintViolationException.Builder builder = new ConstraintViolationException.Builder();
        String must_not_be_null = "must not be null";
        String username = "username";
        builder.addViolation(username, must_not_be_null);
        String should_be_an_email = "should be an email";
        String address = "address";
        builder.addViolation(address, should_be_an_email);
        //        When
        try {
            builder.throwIfAnyViolation();
            fail("Expected ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            //        Then
            assertNull(e.getMessage());
            Set<ConstraintViolationException.ConstraintViolation> constraintViolations = e.getConstraintViolations();
            assertNotNull(constraintViolations);
            assertEquals(2, constraintViolations.size());
            Set<String> resultMessageSet = constraintViolations.stream().map(ConstraintViolationException.ConstraintViolation::getMessage).collect(Collectors.toSet());
            Set<String> resultPropertyPathSet = constraintViolations.stream().map(ConstraintViolationException.ConstraintViolation::getPropertyPath).collect(Collectors.toSet());
            assertEquals(new HashSet<>(List.of(must_not_be_null, should_be_an_email)), resultMessageSet);
            assertEquals(new HashSet<>(List.of(username, address)), resultPropertyPathSet);
        }
    }
}
