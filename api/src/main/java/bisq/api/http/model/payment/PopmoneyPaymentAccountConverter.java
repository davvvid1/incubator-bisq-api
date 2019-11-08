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

import bisq.core.payment.PopmoneyAccount;
import bisq.core.payment.payload.PopmoneyAccountPayload;

public class PopmoneyPaymentAccountConverter extends AbstractPaymentAccountConverter<PopmoneyAccount, PopmoneyAccountPayload, PopmoneyPaymentAccount> {

    @Override
    public PopmoneyAccount toBusinessModel(PopmoneyPaymentAccount rest) {
        PopmoneyAccount business = new PopmoneyAccount();
        business.init();
        business.setAccountId(rest.accountId);
        business.setHolderName(rest.holderName);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public PopmoneyPaymentAccount toRestModel(PopmoneyAccount business) {
        PopmoneyPaymentAccount rest = toRestModel((PopmoneyAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public PopmoneyPaymentAccount toRestModel(PopmoneyAccountPayload business) {
        PopmoneyPaymentAccount rest = new PopmoneyPaymentAccount();
        rest.accountId = business.getAccountId();
        rest.holderName = business.getHolderName();
        toRestModel(rest, business);
        return rest;

    }

}
