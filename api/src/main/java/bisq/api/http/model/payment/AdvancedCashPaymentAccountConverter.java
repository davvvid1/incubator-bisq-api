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

import bisq.core.payment.AdvancedCashAccount;
import bisq.core.payment.payload.AdvancedCashAccountPayload;

public class AdvancedCashPaymentAccountConverter extends AbstractPaymentAccountConverter<AdvancedCashAccount, AdvancedCashAccountPayload, AdvancedCashPaymentAccount> {

    @Override
    public AdvancedCashAccount toBusinessModel(AdvancedCashPaymentAccount rest) {
        AdvancedCashAccount business = new AdvancedCashAccount();
        business.init();
        business.setAccountNr(rest.accountNr);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public AdvancedCashPaymentAccount toRestModel(AdvancedCashAccount business) {
        AdvancedCashPaymentAccount rest = toRestModel((AdvancedCashAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public AdvancedCashPaymentAccount toRestModel(AdvancedCashAccountPayload business) {
        AdvancedCashPaymentAccount rest = new AdvancedCashPaymentAccount();
        rest.accountNr = business.getAccountNr();
        toRestModel(rest, business);
        return rest;
    }

}
