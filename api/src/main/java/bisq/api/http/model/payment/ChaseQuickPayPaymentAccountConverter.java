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

import bisq.core.payment.ChaseQuickPayAccount;
import bisq.core.payment.payload.ChaseQuickPayAccountPayload;

public class ChaseQuickPayPaymentAccountConverter extends AbstractPaymentAccountConverter<ChaseQuickPayAccount, ChaseQuickPayAccountPayload, ChaseQuickPayPaymentAccount> {

    @Override
    public ChaseQuickPayAccount toBusinessModel(ChaseQuickPayPaymentAccount rest) {
        ChaseQuickPayAccount business = new ChaseQuickPayAccount();
        business.init();
        business.setEmail(rest.email);
        business.setHolderName(rest.holderName);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public ChaseQuickPayPaymentAccount toRestModel(ChaseQuickPayAccount business) {
        ChaseQuickPayPaymentAccount rest = toRestModel((ChaseQuickPayAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public ChaseQuickPayPaymentAccount toRestModel(ChaseQuickPayAccountPayload business) {
        ChaseQuickPayPaymentAccount rest = new ChaseQuickPayPaymentAccount();
        rest.email = business.getEmail();
        rest.holderName = business.getHolderName();
        toRestModel(rest, business);
        return rest;
    }

}
