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

import bisq.core.payment.FasterPaymentsAccount;
import bisq.core.payment.payload.FasterPaymentsAccountPayload;

public class FasterPaymentsPaymentAccountConverter extends AbstractPaymentAccountConverter<FasterPaymentsAccount, FasterPaymentsAccountPayload, FasterPaymentsPaymentAccount> {

    @Override
    public FasterPaymentsAccount toBusinessModel(FasterPaymentsPaymentAccount rest) {
        FasterPaymentsAccount business = new FasterPaymentsAccount();
        business.init();
        business.setAccountNr(rest.accountNr);
        business.setSortCode(rest.sortCode);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public FasterPaymentsPaymentAccount toRestModel(FasterPaymentsAccount business) {
        FasterPaymentsPaymentAccount rest = toRestModel((FasterPaymentsAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public FasterPaymentsPaymentAccount toRestModel(FasterPaymentsAccountPayload business) {
        FasterPaymentsPaymentAccount rest = new FasterPaymentsPaymentAccount();
        rest.accountNr = business.getAccountNr();
        rest.sortCode = business.getSortCode();
        toRestModel(rest, business);
        return rest;
    }

}
