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

package bisq.api.http.model.payment;

import bisq.api.http.model.Validations;

import bisq.core.payment.payload.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName(PaymentMethod.SPECIFIC_BANKS_ID)
public class SpecificBanksAccountPaymentAccount extends PaymentAccount {

    public List<String> acceptedBanks = new ArrayList<>();

    public String accountNr;

    public String accountType;

    public String bankId;

    public String bankName;

    public String branchId;

    public String countryCode;

    public String holderName;

    public String holderTaxId;

    public SpecificBanksAccountPaymentAccount() {
        super(PaymentMethod.SPECIFIC_BANKS_ID);
    }

    public void validate() {
        Validations validations = getValidations();
        validations.notEmpty("acceptedBanks", acceptedBanks);
        validations.notEmpty("accountNr", accountNr);
        validations.notEmpty("bankId", bankId);
        validations.notEmpty("bankName", bankName);
        validations.notEmpty("branchId", branchId);
        validations.notEmpty("countryCode", countryCode);
        validations.countryCode("countryCode", countryCode);
        validations.notEmpty("holderName", holderName);
        validations.throwIfAnyViolation();
    }
}
