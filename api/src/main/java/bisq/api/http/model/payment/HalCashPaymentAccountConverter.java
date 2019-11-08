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

import bisq.core.payment.HalCashAccount;
import bisq.core.payment.payload.HalCashAccountPayload;

public class HalCashPaymentAccountConverter extends AbstractPaymentAccountConverter<HalCashAccount, HalCashAccountPayload, HalCashPaymentAccount> {

    @Override
    public HalCashAccount toBusinessModel(HalCashPaymentAccount rest) {
        HalCashAccount business = new HalCashAccount();
        business.init();
        business.setMobileNr(rest.mobileNr);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public HalCashPaymentAccount toRestModel(HalCashAccount business) {
        HalCashPaymentAccount rest = toRestModel((HalCashAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public HalCashPaymentAccount toRestModel(HalCashAccountPayload business) {
        HalCashPaymentAccount rest = new HalCashPaymentAccount();
        rest.mobileNr = business.getMobileNr();
        toRestModel(rest, business);
        return rest;
    }

}
