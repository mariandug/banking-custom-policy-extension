package org.mule.extension.internal;

import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.AnyType;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

import static org.mule.metadata.api.model.MetadataFormat.JAVA;
import static org.mule.metadata.api.model.MetadataFormat.JSON;

public class BankingcustompolicyextMetadataResolver implements OutputTypeResolver<Object> {

    private static final AnyType ANY_TYPE = BaseTypeBuilder.create(JAVA).anyType().build();

    @Override
    public String getCategoryName() {
        return "BankingCustomPolicy";
    }

    @Override
    public MetadataType getOutputType(MetadataContext context, Object key)
        throws MetadataResolvingException, ConnectionException {
        return ANY_TYPE;
    }

}
