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

package bisq.api.http.service;

import bisq.api.http.exceptions.ExperimentalFeatureException;

import bisq.core.app.AppOptionKeys;
import bisq.core.app.BisqEnvironment;

import javax.inject.Inject;

public class ExperimentalFeature {

    public static final String NOTE = "This is EXPERIMENTAL FEATURE! Run it at your own risk! Requires --" + AppOptionKeys.HTTP_API_EXPERIMENTAL_FEATURES_ENABLED + " flag at startup.";
    private final BisqEnvironment environment;

    @Inject
    public ExperimentalFeature(BisqEnvironment environment) {
        this.environment = environment;
    }

    public void assertEnabled() {
        if (!environment.isHttpApiExperimentalFeaturesEnabled()) {
            throw new ExperimentalFeatureException();
        }
    }
}
