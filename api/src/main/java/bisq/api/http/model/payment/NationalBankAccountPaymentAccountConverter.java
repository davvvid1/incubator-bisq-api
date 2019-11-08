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

import bisq.core.payment.NationalBankAccount;
import bisq.core.payment.payload.NationalBankAccountPayload;

public class NationalBankAccountPaymentAccountConverter extends AbstractPaymentAccountConverter<NationalBankAccount, NationalBankAccountPayload, NationalBankAccountPaymentAccount> {

    @Override
    public NationalBankAccount toBusinessModel(NationalBankAccountPaymentAccount rest) {
        NationalBankAccount business = new NationalBankAccount();
        business.init();
        NationalBankAccountPayload paymentAccountPayload = (NationalBankAccountPayload) business.getPaymentAccountPayload();
        paymentAccountPayload.setAccountNr(rest.accountNr);
        paymentAccountPayload.setAccountType(rest.accountType);
        paymentAccountPayload.setBankId(rest.bankId);
        paymentAccountPayload.setBankName(rest.bankName);
        paymentAccountPayload.setBranchId(rest.branchId);
        paymentAccountPayload.setCountryCode(rest.countryCode);
        paymentAccountPayload.setHolderName(rest.holderName);
        paymentAccountPayload.setHolderTaxId(rest.holderTaxId);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public NationalBankAccountPaymentAccount toRestModel(NationalBankAccount business) {
        NationalBankAccountPaymentAccount rest = toRestModel((NationalBankAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public NationalBankAccountPaymentAccount toRestModel(NationalBankAccountPayload business) {
        NationalBankAccountPaymentAccount rest = new NationalBankAccountPaymentAccount();
        rest.accountNr = business.getAccountNr();
        rest.accountType = business.getAccountType();
        rest.bankId = business.getBankId();
        rest.bankName = business.getBankName();
        rest.branchId = business.getBranchId();
        rest.countryCode = business.getCountryCode();
        rest.holderName = business.getHolderName();
        rest.holderTaxId = business.getHolderTaxId();
        toRestModel(rest, business);
        return rest;

    }

}
