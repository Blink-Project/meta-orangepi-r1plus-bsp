FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:${THISDIR}/${PN}:"

require recipes-kernel/linux/linux-yocto.inc

SRCREV = "bd9ae704e2f47bfe5b9921d78be8c5817e44a944"

SRC_URI = " \
	file://defconfig \
    file://0001-Rename-rk3328-orangepi-dtsi-to-match-kernel-5.10.patch \
    file://0002-rk3328-orangepi-r1-plus-common.dtsi-fix-support-for-eth1.patch \
	git://github.com/orangepi-xunlong/linux-orangepi.git;protocol=https;branch=orange-pi-5.8-rockchip64; \
"

require linux-rockchip.inc

do_patch_append(){
	sed -i 's/-Wno-format-security/-Wno-format-security -Wno-implicit-fallthrough/g' ${S}/Makefile
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.8"
