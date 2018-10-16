package org.mule.extension.internal;

import org.mule.extension.exceptions.BankingcustompolicyextErrorTypes;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "banking-custom-policy-ext")
@Extension(name = "Banking-custom-policy-ext")
@Configurations(BankingcustompolicyextConfiguration.class)
@ErrorTypes(BankingcustompolicyextErrorTypes.class)
public class BankingcustompolicyextExtension {

}
