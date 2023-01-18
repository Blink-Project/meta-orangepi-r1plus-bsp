inherit python3-dir

DEPENDS += "openssl-native lz4-native ${PYTHON_PN}-native"

SRC_URI += "file://orangepi-r1plus.dts \
            file://orangepi-r1plus-lts.dts \ 
            file://defconfig"

do_configure_prepend() {
    cp ${WORKDIR}/*.dts ${STAGING_KERNEL_DIR}/arch/arm64/boot/dts/rockchip/
}

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONFIG_MODE ?= "--alldefconfig"

KERNEL_OUTPUT_DIR = "${B}/arch/arm64/boot/"

KERNEL_IMAGETYPES += "Image"

