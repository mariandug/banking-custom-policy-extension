/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package org.mule.extension.internal;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankingcustompolicyextConnectionProvider implements PoolingConnectionProvider<BankingcustompolicyextConnection> {

    private final Logger LOGGER = LoggerFactory.getLogger(BankingcustompolicyextConnectionProvider.class);

    /**
     * A parameter that is always required to be configured.
     */
    @Parameter
    private String requiredParameter;

    /**
     * A parameter that is not required to be configured by the user.
     */
    @DisplayName("Friendly Name")
    @Parameter
    @Optional(defaultValue = "100")
    private int optionalParameter;

    @Override
    public BankingcustompolicyextConnection connect() throws ConnectionException {
        return new BankingcustompolicyextConnection(requiredParameter + ":" + optionalParameter);
    }

    @Override
    public void disconnect(BankingcustompolicyextConnection connection) {
        try {
            connection.invalidate();
        } catch (Exception e) {
            LOGGER.error("Error while disconnecting [" + connection.getId() + "]: " + e.getMessage(), e);
        }
    }

    @Override
    public ConnectionValidationResult validate(BankingcustompolicyextConnection connection) {
        return ConnectionValidationResult.success();
    }
}
