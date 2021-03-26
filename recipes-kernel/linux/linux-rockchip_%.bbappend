FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://orangepi-r1plus.dts"

do_configure_prepend() {
    cp ${WORKDIR}/orangepi-r1plus.dts ${STAGING_KERNEL_DIR}/arch/arm64/boot/dts/rockchip/
}
