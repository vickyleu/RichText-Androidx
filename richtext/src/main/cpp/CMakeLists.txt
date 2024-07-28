cmake_minimum_required(VERSION 3.4.1)
project(FreetypeRichText)
add_library( # Sets the name of the library.
             FreetypeRichText

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             freetype_jni.cpp
             )

find_package(cdep-dependencies REQUIRED)

add_all_cdep_dependencies(FreetypeRichText)

target_link_libraries(FreetypeRichText log android)
