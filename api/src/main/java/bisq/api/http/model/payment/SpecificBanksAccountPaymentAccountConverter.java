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

import bisq.core.payment.SpecificBanksAccount;
import bisq.core.payment.payload.SpecificBanksAccountPayload;

import java.util.List;

public class SpecificBanksAccountPaymentAccountConverter extends AbstractPaymentAccountConverter<SpecificBanksAccount, SpecificBanksAccountPayload, SpecificBanksAccountPaymentAccount> {

    @Override
    public SpecificBanksAccount toBusinessModel(SpecificBanksAccountPaymentAccount rest) {
        SpecificBanksAccount business = new SpecificBanksAccount();
        business.init();
        SpecificBanksAccountPayload paymentAccountPayload = (SpecificBanksAccountPayload) business.getPaymentAccountPayload();
        paymentAccountPayload.setAccountNr(rest.accountNr);
        paymentAccountPayload.setAccountType(rest.accountType);
        paymentAccountPayload.setBankId(rest.bankId);
        paymentAccountPayload.setBankName(rest.bankName);
        paymentAccountPayload.setBranchId(rest.branchId);
        paymentAccountPayload.setCountryCode(rest.countryCode);
        paymentAccountPayload.setHolderName(rest.holderName);
        paymentAccountPayload.setHolderTaxId(rest.holderTaxId);
        rest.acceptedBanks.forEach(paymentAccountPayload::addAcceptedBank);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public SpecificBanksAccountPaymentAccount toRestModel(SpecificBanksAccount business) {
        SpecificBanksAccountPaymentAccount rest = toRestModel((SpecificBanksAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public SpecificBanksAccountPaymentAccount toRestModel(SpecificBanksAccountPayload business) {
        SpecificBanksAccountPaymentAccount rest = new SpecificBanksAccountPaymentAccount();
        rest.accountNr = business.getAccountNr();
        rest.accountType = business.getAccountType();
        rest.bankId = business.getBankId();
        rest.bankName = business.getBankName();
        rest.branchId = business.getBranchId();
        rest.countryCode = business.getCountryCode();
        rest.holderName = business.getHolderName();
        rest.holderTaxId = business.getHolderTaxId();
        List<String> acceptedBanks = business.getAcceptedBanks();
        if (acceptedBanks != null)
            rest.acceptedBanks.addAll(acceptedBanks);
        toRestModel(rest, business);
        return rest;

    }

}
