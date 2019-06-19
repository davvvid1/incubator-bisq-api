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

import bisq.core.payment.PerfectMoneyAccount;
import bisq.core.payment.payload.PerfectMoneyAccountPayload;

public class PerfectMoneyPaymentAccountConverter extends AbstractPaymentAccountConverter<PerfectMoneyAccount, PerfectMoneyAccountPayload, PerfectMoneyPaymentAccount> {

    @Override
    public PerfectMoneyAccount toBusinessModel(PerfectMoneyPaymentAccount rest) {
        PerfectMoneyAccount business = new PerfectMoneyAccount();
        business.init();
        business.setAccountNr(rest.accountNr);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public PerfectMoneyPaymentAccount toRestModel(PerfectMoneyAccount business) {
        PerfectMoneyPaymentAccount rest = toRestModel((PerfectMoneyAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public PerfectMoneyPaymentAccount toRestModel(PerfectMoneyAccountPayload business) {
        PerfectMoneyPaymentAccount rest = new PerfectMoneyPaymentAccount();
        rest.accountNr = business.getAccountNr();
        toRestModel(rest, business);
        return rest;
    }

}
