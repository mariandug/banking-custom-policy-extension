package org.mule.extension.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(BankingcustompolicyextOperations.class)
//@ConnectionProviders(BankingcustompolicyextConnectionProvider.class)
public class BankingcustompolicyextConfiguration {

  @Parameter
  private String issuer;

  public String getIssuer(){
    return issuer;
  }

  @Parameter
  private String jwksUrl;

  public String getJwksUrl(){
    return jwksUrl;
  }

  @Parameter
  private String signingAlgorithm;

  public String getSigningAlgorithm(){
    return signingAlgorithm;
  }

  @Parameter
  private String encryptionKey;

  public String getEncryptionKey(){
    return encryptionKey;
  }
}
