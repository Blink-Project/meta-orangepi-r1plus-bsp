FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "4862c9859b5624398dd1b74f7ae9a612c68f513c"
SRCREV_rkbin = "rkbin-2021_05_18"

SRC_URI_orangepi-r1plus = "\
           git://github.com/aledemers/u-boot-rockchip.git;protocol=https;branch=u-boot-2017.09; \
           git://github.com/aledemers/rkbin.git;protocol=https;branch=master;name=rkbin;destsuffix=rkbin; \
           file://0001-adapt-rkboot-for-orangepi.patch \
           file://0002-make-it-compile-with-py3.patch"

SRC_URI_orangepi-r1plus-lts = "\
           git://github.com/aledemers/u-boot-rockchip.git;protocol=https;branch=u-boot-2017.09; \
           git://github.com/aledemers/rkbin.git;protocol=https;branch=master;name=rkbin;destsuffix=rkbin; \
           file://0001-adapt-rkboot-for-orangepi-lts.patch \
           file://0002-make-it-compile-with-py3.patch"

UBOOT_INITIAL_ENV = ""
