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

import bisq.core.payment.WeChatPayAccount;
import bisq.core.payment.payload.WeChatPayAccountPayload;

public class WeChatPayPaymentAccountConverter extends AbstractPaymentAccountConverter<WeChatPayAccount, WeChatPayAccountPayload, WeChatPayPaymentAccount> {

    @Override
    public WeChatPayAccount toBusinessModel(WeChatPayPaymentAccount rest) {
        WeChatPayAccount business = new WeChatPayAccount();
        business.init();
        business.setAccountNr(rest.accountNr);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public WeChatPayPaymentAccount toRestModel(WeChatPayAccount business) {
        WeChatPayPaymentAccount rest = toRestModel((WeChatPayAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public WeChatPayPaymentAccount toRestModel(WeChatPayAccountPayload business) {
        WeChatPayPaymentAccount rest = new WeChatPayPaymentAccount();
        rest.accountNr = business.getAccountNr();
        toRestModel(rest, business);
        return rest;

    }

}
