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

import bisq.core.payment.USPostalMoneyOrderAccount;
import bisq.core.payment.payload.USPostalMoneyOrderAccountPayload;

public class USPostalMoneyOrderPaymentAccountConverter extends AbstractPaymentAccountConverter<USPostalMoneyOrderAccount, USPostalMoneyOrderAccountPayload, USPostalMoneyOrderPaymentAccount> {

    @Override
    public USPostalMoneyOrderAccount toBusinessModel(USPostalMoneyOrderPaymentAccount rest) {
        USPostalMoneyOrderAccount business = new USPostalMoneyOrderAccount();
        business.init();
        business.setHolderName(rest.holderName);
        business.setPostalAddress(rest.postalAddress);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public USPostalMoneyOrderPaymentAccount toRestModel(USPostalMoneyOrderAccount business) {
        USPostalMoneyOrderPaymentAccount rest = toRestModel((USPostalMoneyOrderAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public USPostalMoneyOrderPaymentAccount toRestModel(USPostalMoneyOrderAccountPayload business) {
        USPostalMoneyOrderPaymentAccount rest = new USPostalMoneyOrderPaymentAccount();
        rest.holderName = business.getHolderName();
        rest.postalAddress = business.getPostalAddress();
        toRestModel(rest, business);
        return rest;
    }

}
