# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

inherit python3-dir

PV = "2017.09+git${SRCPV}"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "4862c9859b5624398dd1b74f7ae9a612c68f513c"
SRCREV_rkbin = "rkbin-2021_05_18"

BASE_SRC_URI = "\
           git://github.com/aledemers/u-boot-rockchip.git;protocol=https;branch=u-boot-2017.09; \
           git://github.com/aledemers/rkbin.git;protocol=https;branch=master;name=rkbin;destsuffix=rkbin; \
           file://0002-make-it-compile-with-py3.patch \
           file://0001-Revert-Makefile-enable-Werror-option.patch \
           "

SRC_URI:orangepi-r1plus = "${BASE_SRC_URI} file://0001-adapt-rkboot-for-orangepi.patch "

SRC_URI:orangepi-r1plus-lts = "${BASE_SRC_URI} file://0001-adapt-rkboot-for-orangepi-lts.patch "

SRCREV_FORMAT = "default_rkbin"

DEPENDS += "${PYTHON_PN}-native bc-native dtc-native python3-setuptools-native"

# Needed for packing BSP u-boot
DEPENDS += "coreutils-native ${PYTHON_PN}-pyelftools-native"

do_configure:prepend() {
	# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
	for s in `grep -rIl python ${S}`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done

	# Support python3
	sed -i -e 's/\(open(.*[^"]\))/\1, "rb")/' -e 's/,$//' \
		-e 's/print >> \([^,]*\), *\(.*\)$/print(\2, file=\1)/' \
		-e 's/print \(.*\)$/print(\1)/' \
		${S}/arch/arm/mach-rockchip/make_fit_atf.py

	# Remove unneeded stages from make.sh
	sed -i -e '/^select_tool/d' -e '/^clean/d' -e '/^\t*make/d' ${S}/make.sh

	if [ "x${RK_ALLOW_PREBUILT_UBOOT}" = "x1" ]; then
		# Copy prebuilt images
		if [ -e "${S}/${UBOOT_BINARY}" ]; then
			bbnote "${PN}: Found prebuilt images."
			mkdir -p ${B}/prebuilt/
			mv ${S}/*.bin ${S}/*.img ${B}/prebuilt/
		fi
	fi

	[ -e "${S}/.config" ] && make -C ${S} mrproper
}

# Generate Rockchip style loader binaries
RK_IDBLOCK_IMG = "idblock.img"
RK_LOADER_BIN = "loader.bin"
RK_TRUST_IMG = "trust.img"
UBOOT_BINARY = "uboot.img"

do_compile:append() {
	cd ${B}

	if [ -e "${B}/prebuilt/${UBOOT_BINARY}" ]; then
		bbnote "${PN}: Using prebuilt images."
		ln -sf ${B}/prebuilt/*.bin ${B}/prebuilt/*.img ${B}/
	else
		# Prepare needed files
		for d in make.sh scripts configs arch/arm/mach-rockchip; do
			cp -rT ${S}/${d} ${d}
		done

		# Pack rockchip loader images
		./make.sh
	fi

	ln -sf *_loader*.bin "${RK_LOADER_BIN}"

	# Generate idblock image
	bbnote "${PN}: Generating ${RK_IDBLOCK_IMG} from ${RK_LOADER_BIN}"
	./tools/boot_merger --unpack "${RK_LOADER_BIN}"

	if [ -f FlashHead ];then
		cat FlashHead FlashData > "${RK_IDBLOCK_IMG}"
	else
		./tools/mkimage -n "${SOC_FAMILY}" -T rksd -d FlashData \
			"${RK_IDBLOCK_IMG}"
	fi

	cat FlashBoot >> "${RK_IDBLOCK_IMG}"
}

do_deploy:append() {
	cd ${B}

	for binary in "${RK_IDBLOCK_IMG}" "${RK_LOADER_BIN}" "${RK_TRUST_IMG}";do
		[ -f "${binary}" ] || continue
		install "${binary}" "${DEPLOYDIR}/${binary}-${PV}"
		ln -sf "${binary}-${PV}" "${DEPLOYDIR}/${binary}"
	done
}

UBOOT_INITIAL_ENV = ""
