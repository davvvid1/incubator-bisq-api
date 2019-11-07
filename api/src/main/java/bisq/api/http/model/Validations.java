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

package bisq.api.http.model;

import bisq.core.exceptions.ConstraintViolationException;
import bisq.core.locale.CountryUtil;

import java.util.List;

public class Validations {

    private ConstraintViolationException.Builder builder;

    private boolean countryCodeCheck(String value) {
        return CountryUtil.findCountryByCode(value).isPresent();
    }

    private boolean emptyCheck(String value) {
        return value.trim().isEmpty();
    }

    private boolean emptyCheck(List<String> value) {
        return value.isEmpty();
    }

    private boolean nullCheck(String value) {
        return value == null;
    }

    public Validations(ConstraintViolationException.Builder builder) {
        this.builder = builder;
    }

    public void countryCode(String path, String value) {
        if (!nullCheck(value) && !emptyCheck(value) && !countryCodeCheck(value))
            builder.addViolation(path, "is not valid country code");
    }

    public void notEmpty(String path, String value) {
        if (!nullCheck(value) && emptyCheck(value))
            builder.addViolation(path, "may not be empty");
    }

    public void notEmpty(String path, List<String> value) {
        if (emptyCheck(value))
            builder.addViolation(path, "may not be empty");
    }

    public void notNull(String path, String value) {
        if (nullCheck(value))
            builder.addViolation(path, "may not be null");
    }

    public void throwIfAnyValidation() {
        builder.throwIfAnyValidation();
    }
}
