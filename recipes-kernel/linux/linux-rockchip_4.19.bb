# Copyright (C) 2020, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)
#
# This kernel works for Orange Pi R1+, but does not support all LEDs, nor
# the LTS version of the OrangePi R1+.
# It supports the analog display (tve) though, which is why we keep this version
# here for reference.
#
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

require recipes-kernel/linux/linux-yocto.inc

SRCREV = "kernel-4.19-2021_02_06"

SRC_URI = " \
	git://github.com/aledemers/linux-rockchip.git;branch=kernel-4.19; \
"

require linux-rockchip.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "4.19"
