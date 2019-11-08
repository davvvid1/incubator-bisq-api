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

import bisq.core.payment.PromptPayAccount;
import bisq.core.payment.payload.PromptPayAccountPayload;

public class PromptPayPaymentAccountConverter extends AbstractPaymentAccountConverter<PromptPayAccount, PromptPayAccountPayload, PromptPayPaymentAccount> {

    @Override
    public PromptPayAccount toBusinessModel(PromptPayPaymentAccount rest) {
        PromptPayAccount business = new PromptPayAccount();
        business.init();
        business.setPromptPayId(rest.promptPayId);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public PromptPayPaymentAccount toRestModel(PromptPayAccount business) {
        PromptPayPaymentAccount rest = toRestModel((PromptPayAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public PromptPayPaymentAccount toRestModel(PromptPayAccountPayload business) {
        PromptPayPaymentAccount rest = new PromptPayPaymentAccount();
        rest.promptPayId = business.getPromptPayId();
        toRestModel(rest, business);
        return rest;
    }

}
