# Copyright (C) 2020, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-orangepi.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRCREV = "bd9ae704e2f47bfe5b9921d78be8c5817e44a944"

SRC_URI = " \
	file://defconfig \
	git://github.com/orangepi-xunlong/linux-orangepi.git;protocol=https;branch=orange-pi-5.8-rockchip64; \
"

do_patch_append(){
	sed -i 's/-Wno-format-security/-Wno-format-security -Wno-implicit-fallthrough/g' ${S}/Makefile
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.8"
