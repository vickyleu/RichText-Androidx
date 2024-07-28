package com.pvporbit.freetype;

public class Size extends Utils.Pointer {

    public Size(long pointer) {
        super(pointer);
    }

    public SizeMetrics getMetrics() {
        long sizeMetrics = FreeType.FT_Size_Get_metrics(pointer);
        if (sizeMetrics <= 0)
            return null;
        return new SizeMetrics(sizeMetrics);
    }
}