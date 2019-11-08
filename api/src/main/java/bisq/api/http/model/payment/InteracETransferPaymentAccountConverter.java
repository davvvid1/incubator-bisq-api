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

import bisq.core.payment.InteracETransferAccount;
import bisq.core.payment.payload.InteracETransferAccountPayload;

public class InteracETransferPaymentAccountConverter extends AbstractPaymentAccountConverter<InteracETransferAccount, InteracETransferAccountPayload, InteracETransferPaymentAccount> {

    @Override
    public InteracETransferAccount toBusinessModel(InteracETransferPaymentAccount rest) {
        InteracETransferAccount business = new InteracETransferAccount();
        business.init();
        business.setHolderName(rest.holderName);
        business.setEmail(rest.emailOrMobileNr);
        business.setQuestion(rest.question);
        business.setAnswer(rest.answer);
        toBusinessModel(business, rest);
        return business;
    }

    @Override
    public InteracETransferPaymentAccount toRestModel(InteracETransferAccount business) {
        InteracETransferPaymentAccount rest = toRestModel((InteracETransferAccountPayload) business.getPaymentAccountPayload());
        toRestModel(rest, business);
        return rest;
    }

    @Override
    public InteracETransferPaymentAccount toRestModel(InteracETransferAccountPayload business) {
        InteracETransferPaymentAccount rest = new InteracETransferPaymentAccount();
        rest.answer = business.getAnswer();
        rest.question = business.getQuestion();
        rest.holderName = business.getHolderName();
        rest.emailOrMobileNr = business.getEmail();
        toRestModel(rest, business);
        return rest;
    }

}
