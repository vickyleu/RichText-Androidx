package com.pvporbit.freetype;

public class CharMap extends Utils.Pointer {

    public CharMap(long pointer) {
        super(pointer);
    }

    public static int getCharmapIndex(CharMap charmap) {
        return FreeType.FT_Get_Charmap_Index(charmap.getPointer());
    }
}