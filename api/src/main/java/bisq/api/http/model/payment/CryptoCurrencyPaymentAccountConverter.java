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

import bisq.core.payment.CryptoCurrencyAccount;
import bisq.core.payment.payload.CryptoCurrencyAccountPayload;

public class CryptoCurrencyPaymentAccountConverter extends AbstractPaymentAccountConverter<CryptoCurrencyAccount, CryptoCurrencyAccountPayload, CryptoCurrencyPaymentAccount> {

    @Override
    public CryptoCurrencyAccount toBusinessModel(CryptoCurrencyPaymentAccount rest) {
        CryptoCurrencyAccount business = new CryptoCurrencyAccount();
        business.init();
        business.setAddress(rest.address);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public CryptoCurrencyPaymentAccount toRestModel(CryptoCurrencyAccount business) {
        CryptoCurrencyPaymentAccount rest = toRestModel((CryptoCurrencyAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public CryptoCurrencyPaymentAccount toRestModel(CryptoCurrencyAccountPayload business) {
        CryptoCurrencyPaymentAccount rest = new CryptoCurrencyPaymentAccount();
        rest.address = business.getAddress();
        toRestModel(rest, business);
        return rest;
    }

}
