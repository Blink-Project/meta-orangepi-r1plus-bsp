# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

inherit deploy native

DESCRIPTION = "Rockchip binary tools"

LICENSE = "LICENSE.rockchip"
LIC_FILES_CHKSUM = "file://${METAOPIBASE}/licenses/LICENSE.rockchip;md5=d63890e209bf038f44e708bbb13e4ed9"
SRC_URI = " \
	git://github.com/aledemers/rkbin.git;protocol=https;branch=rkbin-2021_10_13;name=rkbin \
	git://github.com/aledemers/rkdeveloptool.git;protocol=https;branch=master;name=tools;destsuffix=git/extra \
"

PV_append = "+git${SRCPV}"

SRCREV_rkbin = "4ad65f2b5fe427661c607b829bab7b09f9a83d06"
SRCREV_tools = "d7dcaab628441a78acc9814396a895c3b504139a"
SRCREV_FORMAT ?= "rkbin_tools"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN} = "already-stripped"

# The pre-built tools have different link loader, don't change them.
UNINATIVE_LOADER := ""

do_install () {
	install -d ${D}/${bindir}

	cd ${S}/tools

	install -m 0755 boot_merger ${D}/${bindir}
	install -m 0755 trust_merger ${D}/${bindir}
	install -m 0755 firmwareMerger ${D}/${bindir}

	install -m 0755 kernelimage ${D}/${bindir}
	install -m 0755 loaderimage ${D}/${bindir}

	install -m 0755 mkkrnlimg ${D}/${bindir}
	install -m 0755 resource_tool ${D}/${bindir}

	install -m 0755 upgrade_tool ${D}/${bindir}

	cd ${S}/extra/linux/Linux_Pack_Firmware/rockdev

	install -m 0755 afptool ${D}/${bindir}
	install -m 0755 rkImageMaker ${D}/${bindir}
}
