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

import bisq.core.payment.MoneyBeamAccount;
import bisq.core.payment.payload.MoneyBeamAccountPayload;

public class MoneyBeamPaymentAccountConverter extends AbstractPaymentAccountConverter<MoneyBeamAccount, MoneyBeamAccountPayload, MoneyBeamPaymentAccount> {

    @Override
    public MoneyBeamAccount toBusinessModel(MoneyBeamPaymentAccount rest) {
        MoneyBeamAccount business = new MoneyBeamAccount();
        business.init();
        business.setAccountId(rest.accountId);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public MoneyBeamPaymentAccount toRestModel(MoneyBeamAccount business) {
        MoneyBeamPaymentAccount rest = toRestModel((MoneyBeamAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public MoneyBeamPaymentAccount toRestModel(MoneyBeamAccountPayload business) {
        MoneyBeamPaymentAccount rest = new MoneyBeamPaymentAccount();
        rest.accountId = business.getAccountId();
        toRestModel(rest, business);
        return rest;
    }

}
