# This kernel currently does not work for Orange Pi R1+. 
# The SD Card is not detected, and cannot be used a root=.
# We keep it here, as the kernel 5.10 is the mainline proposed by orange pi. 
# We should fix the issue in a near future.

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:${THISDIR}/${PN}:"

require recipes-kernel/linux/linux-yocto.inc

SRCREV = "66fc4ec3454bf59fc6e6ceee6659c8a554f3bf6d"

SRC_URI = " \
	file://defconfig \
	git://github.com/orangepi-xunlong/linux-orangepi.git;protocol=https;branch=orange-pi-5.10-rockchip64; \
"

require linux-rockchip.inc

do_patch_append(){
	sed -i 's/-Wno-format-security/-Wno-format-security -Wno-implicit-fallthrough/g' ${S}/Makefile
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.10"
