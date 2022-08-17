FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "4862c9859b5624398dd1b74f7ae9a612c68f513c"
SRCREV_rkbin = "rkbin-2021_05_18"

SRC_URI = " \
	git://github.com/aledemers/u-boot-rockchip.git;protocol=https;branch=u-boot-2017.09; \
	git://github.com/aledemers/rkbin.git;protocol=https;branch=master;name=rkbin;destsuffix=rkbin; \
"

SRC_URI += "file://0001-adapt-rkboot-for-orangepi.patch"
