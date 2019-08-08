package com.prime.uc.util;


import static java.lang.System.arraycopy;
import java.util.UUID;

import static org.hibernate.internal.util.BytesHelper.asLong;
import static org.hibernate.internal.util.BytesHelper.fromLong;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/*
 * Auto apply this converter to all UUID-based attributes, excluding the list 
 * of exceptions mentioned in the JPA spec.
 */
@Converter(autoApply=true)
public class OptimizedUUIDConverter implements AttributeConverter<UUID, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(final UUID uuid) {
        final byte[] out = new byte[16];
        final byte[] msbIn = fromLong(uuid.getMostSignificantBits());
        
        // Reorder the UUID, swapping the hi- and low- digits of the timestamp
        arraycopy(msbIn, 6, out, 0, 2);
        arraycopy(msbIn, 4, out, 2, 2);
        arraycopy(msbIn, 0, out, 4, 4);

        // The remainder of the UUID (clock sequence and MAC address) are kept as-is
        arraycopy(fromLong(uuid.getLeastSignificantBits()), 0, out, 8, 8);
        
        return out;
    }

    @Override
    public UUID convertToEntityAttribute(final byte[] bytes) {
        byte[] msb = new byte[8];
        byte[] lsb = new byte[8];
        
        // Reorder the UUID, swapping the hi- and low- digits of the timestamp
        arraycopy(value, 4, msb, 0, 4);
        arraycopy(value, 2, msb, 4, 2);
        arraycopy(value, 0, msb, 6, 2);

        // The remainder of the UUID (clock sequence and MAC address) are kept as-is
        arraycopy(value, 8, lsb, 0, 8);
        
        return new UUID(asLong(msb), asLong(lsb));
    }
}
