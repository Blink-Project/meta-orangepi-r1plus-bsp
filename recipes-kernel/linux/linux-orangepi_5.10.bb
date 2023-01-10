# Copyright (C) 2020, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-orangepi.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

SRCREV = "66fc4ec3454bf59fc6e6ceee6659c8a554f3bf6d"

SRC_URI = " \
	file://defconfig \
	git://github.com/orangepi-xunlong/linux-orangepi.git;protocol=https;branch=orange-pi-5.10-rockchip64; \
	file://0001-enable-analog-sound-for-r1-plus-board.patch \
"

do_patch_append(){
	sed -i 's/-Wno-format-security/-Wno-format-security -Wno-implicit-fallthrough/g' ${S}/Makefile
}

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.10"
