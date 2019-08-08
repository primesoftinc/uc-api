package com.prime.uc.util;


import java.util.UUID;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor;

public class OptimizedUUIDType extends AbstractSingleColumnStandardBasicType<UUID> {
    static final long serialVersionUID = 1L;

    public static OptimizedUUIDType INSTANCE = new OptimizedUUIDType();

    public OptimizedUUIDType() {
        super(BinaryTypeDescriptor.INSTANCE, OptimizedUUIDTypeDescriptor.INSTANCE);
    }

    public String getName() {
        return "uuid-binary-optimized";
    }

    public boolean registerUnderJavaType() {
        return true;
    }
}