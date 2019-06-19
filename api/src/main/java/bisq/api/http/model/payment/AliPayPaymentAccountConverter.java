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

import bisq.core.payment.AliPayAccount;
import bisq.core.payment.payload.AliPayAccountPayload;

public class AliPayPaymentAccountConverter extends AbstractPaymentAccountConverter<AliPayAccount, AliPayAccountPayload, AliPayPaymentAccount> {

    @Override
    public AliPayAccount toBusinessModel(AliPayPaymentAccount rest) {
        AliPayAccount business = new AliPayAccount();
        business.init();
        business.setAccountNr(rest.accountNr);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public AliPayPaymentAccount toRestModel(AliPayAccount business) {
        AliPayPaymentAccount rest = toRestModel((AliPayAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public AliPayPaymentAccount toRestModel(AliPayAccountPayload business) {
        AliPayPaymentAccount rest = new AliPayPaymentAccount();
        rest.accountNr = business.getAccountNr();
        toRestModel(rest, business);
        return rest;
    }

}
