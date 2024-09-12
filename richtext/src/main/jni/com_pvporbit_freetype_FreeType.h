/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_pvporbit_freetype_FreeType */

#ifndef _Included_com_pvporbit_freetype_FreeType
#define _Included_com_pvporbit_freetype_FreeType
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Init_FreeType
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Init_1FreeType
        (JNIEnv *, jclass);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Done_FreeType
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Done_1FreeType
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Library_Version
 * Signature: (J)Lcom/pvporbit/freetype/LibraryVersion;
 */
JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Library_1Version
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_New_Memory_Face
 * Signature: (JLjava/nio/ByteBuffer;IJ)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1New_1Memory_1Face
        (JNIEnv *, jclass, jlong, jobject, jint, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_ascender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1ascender
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_descender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1descender
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_face_flags
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1face_1flags
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_face_index
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1face_1index
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_family_name
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1family_1name
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_heigth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1heigth
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_max_advance_height
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1max_1advance_1height
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_max_advance_width
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1max_1advance_1width
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_num_faces
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1num_1faces
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_num_glyphs
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1num_1glyphs
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_style_flags
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1style_1flags
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_style_name
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1style_1name
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_underline_position
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1underline_1position
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_underline_thickness
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1underline_1thickness
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_units_per_EM
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1units_1per_1EM
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_glyph
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1glyph
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_size
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1size
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Track_Kerning
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Track_1Kerning
        (JNIEnv *, jclass, jlong, jlong, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_Get_Kerning
 * Signature: (JCCI)Lcom/pvporbit/freetype/Kerning;
 */
JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1Get_1Kerning
        (JNIEnv *, jclass, jlong, jchar, jchar, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Done_Face
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Done_1Face
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Reference_Face
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Reference_1Face
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_HAS_KERNING
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1HAS_1KERNING
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Postscript_Name
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Postscript_1Name
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Select_Charmap
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Select_1Charmap
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Set_Charmap
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Set_1Charmap
        (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_CheckTrueTypePatents
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1CheckTrueTypePatents
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Face_SetUnpatentedHinting
 * Signature: (JZ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Face_1SetUnpatentedHinting
        (JNIEnv *, jclass, jlong, jboolean);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_First_Char
 * Signature: (J)[I
 */
JNIEXPORT jintArray JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1First_1Char
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Next_Char
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Next_1Char
        (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Char_Index
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Char_1Index
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Name_Index
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Name_1Index
        (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Glyph_Name
 * Signature: (JI)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Glyph_1Name
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_FSType_Flags
 * Signature: (J)S
 */
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1FSType_1Flags
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Select_Size
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Select_1Size
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Load_Char
 * Signature: (JCI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Load_1Char
        (JNIEnv *, jclass, jlong, jchar, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Request_Size
 * Signature: (JLcom/pvporbit/freetype/SizeRequest;)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Request_1Size
        (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Set_Pixel_Sizes
 * Signature: (JFF)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Set_1Pixel_1Sizes
        (JNIEnv *, jclass, jlong, jfloat, jfloat);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Load_Glyph
 * Signature: (JII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Load_1Glyph
        (JNIEnv *, jclass, jlong, jint, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Set_Char_Size
 * Signature: (JIIII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Set_1Char_1Size
        (JNIEnv *, jclass, jlong, jint, jint, jint, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Get_metrics
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Get_1metrics
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_ascender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1ascender
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_descender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1descender
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_height
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1height
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_max_advance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1max_1advance
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_x_ppem
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1x_1ppem
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_x_scale
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1x_1scale
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_y_ppem
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1y_1ppem
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Size_Metrics_Get_y_scale
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Size_1Metrics_1Get_1y_1scale
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_linearHoriAdvance
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1linearHoriAdvance
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_linearVertAdvance
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1linearVertAdvance
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_advance
 * Signature: (J)Lcom/pvporbit/freetype/GlyphSlot/Advance;
 */
JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1advance
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_format
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1format
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_bitmap_left
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1bitmap_1left
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_bitmap_top
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1bitmap_1top
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_bitmap
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1bitmap
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_GlyphSlot_Get_metrics
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1metrics
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Render_Glyph
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Render_1Glyph
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_width
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1width
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_height
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1height
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_horiAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1horiAdvance
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_vertAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1vertAdvance
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_horiBearingX
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1horiBearingX
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_horiBearingY
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1horiBearingY
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_vertBearingX
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1vertBearingX
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Glyph_Metrics_Get_vertBearingY
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1vertBearingY
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_width
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1width
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_rows
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1rows
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_pitch
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1pitch
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_num_grays
 * Signature: (J)S
 */
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1num_1grays
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_palette_mode
 * Signature: (J)C
 */
JNIEXPORT jchar JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1palette_1mode
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_pixel_mode
 * Signature: (J)C
 */
JNIEXPORT jchar JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1pixel_1mode
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Bitmap_Get_buffer
 * Signature: (J)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1buffer
        (JNIEnv *, jclass, jlong);

/*
 * Class:     com_pvporbit_freetype_FreeType
 * Method:    FT_Get_Charmap_Index
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Charmap_1Index
        (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif