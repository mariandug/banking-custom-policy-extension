/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package org.mule.extension.exceptions;

import org.mule.runtime.extension.api.exception.ModuleException;
import static org.mule.extension.exceptions.BankingcustompolicyextErrorTypes.INVALID_JWT;

public class InvalidJWTException extends ModuleException {
    public InvalidJWTException(Throwable cause) {
        super("Invalid JWT", INVALID_JWT, cause);
    }
}
