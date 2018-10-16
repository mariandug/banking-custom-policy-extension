package org.mule.extension.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.extension.exceptions.InvalidJWTException;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class BankingcustompolicyextOperations {

  private final Logger LOGGER = LoggerFactory.getLogger(BankingcustompolicyextOperations.class);

  /**
   * Example of an operation that uses the configuration and a connection instance to perform some action.
   */
  @MediaType(value = ANY, strict = false)
  public String retrieveInfo(@Config BankingcustompolicyextConfiguration configuration){
    return "Using Configuration [" + configuration.getIssuer();
  }

  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   */
  @MediaType(value = ANY, strict = false)
  public String sayHi(String person) {
    return buildHelloMessage(person);
  }

  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   */
  @MediaType(value = ANY, strict = false)
  public String validateJWT(@Config BankingcustompolicyextConfiguration configuration, String token) {
    LOGGER.info("token="+token);
    LOGGER.info("url="+configuration.getJwksUrl());
    JwtClaims jwtClaims = null;
		try {

			HttpsJwksVerificationKeyResolver verificationKeys = new HttpsJwksVerificationKeyResolver(new HttpsJwks(configuration.getJwksUrl()));

			String jwkJson = String.format("{\"kty\": \"oct\", \"k\": \"%s\"}", configuration.getEncryptionKey());
			OctetSequenceJsonWebKey symmetricKey = (OctetSequenceJsonWebKey) JsonWebKey.Factory.newJwk(jwkJson);

			JwtConsumer jwtConsumer = new JwtConsumerBuilder()
					.setRequireExpirationTime()
					.setJwsAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST, configuration.getSigningAlgorithm()))
          .setJweAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST, "dir"))
					.setMaxFutureValidityInMinutes(30 * 24 * 60) // 30 days
					.setAllowedClockSkewInSeconds(0)
					.setRequireSubject()
					.setSkipDefaultAudienceValidation()
					.setExpectedIssuer(configuration.getIssuer())
					.setVerificationKeyResolver(verificationKeys)
					.setDecryptionKey(symmetricKey.getKey())
					.build();

			jwtClaims = jwtConsumer.processToClaims(token);
			LOGGER.info("Claims= " + jwtClaims);
			return jwtClaims.getClaimsMap().toString();

		} catch (Exception e) {
			LOGGER.info("Policy {{ policyId }}: JWS access_token could not be verified: " + e);
			throw new InvalidJWTException(e);
		}
  }

  /**
   * Private Methods are not exposed as operations
   */
  private String buildHelloMessage(String person) {
    return "Hello " + person + "!!!";
  }
}
