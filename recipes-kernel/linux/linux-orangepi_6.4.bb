FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_${PV}:${THISDIR}/${PN}:"

require recipes-kernel/linux/linux-yocto.inc

SRCREV = "6995e2de6891c724bfeb2db33d7b87775f913ad1"

SRC_URI = " \
	file://defconfig \
	git://github.com/torvalds/linux.git;protocol=https;branch=master \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION = "6.4"

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONFIG_MODE ?= "--alldefconfig"

KERNEL_OUTPUT_DIR = "${B}/arch/arm64/boot/"

KERNEL_IMAGETYPES += "Image"

INITRAMFS_IMAGE = "orangepi-image-initramfs"
