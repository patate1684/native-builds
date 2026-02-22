include("$ENV{VCPKG_ROOT}/scripts/toolchains/mingw.cmake")

# konan toolchain
# set(CMAKE_C_COMPILER "$ENV{TOOLCHAIN_DIR}/bin/clang.exe" CACHE FILEPATH "" FORCE)
# set(CMAKE_CXX_COMPILER "$ENV{TOOLCHAIN_DIR}/bin/clang++.exe" CACHE FILEPATH "" FORCE)
# set(CMAKE_AR "$ENV{TOOLCHAIN_DIR}/bin/llvm-ar.exe" CACHE FILEPATH "" FORCE)
# set(CMAKE_LINKER "$ENV{TOOLCHAIN_DIR}/bin/ld.lld.exe" CACHE FILEPATH "" FORCE)

set(CMAKE_C_COMPILER "C:/msys64/mingw64/bin/gcc.exe" CACHE FILEPATH "" FORCE)
set(CMAKE_CXX_COMPILER "C:/msys64/mingw64/bin/g++.exe" CACHE FILEPATH "" FORCE)
set(CMAKE_AR "C:/msys64/mingw64/bin/ar.exe" CACHE FILEPATH "" FORCE)
# No CMAKE_LINKER override — GCC knows its own linker
