require conf/machine/include/rk3328.inc

IMAGE_FSTYPES = "tar.xz wic.gz wic.bmap"

UBOOT_MACHINE = "rk3328_defconfig"

RK_WIFIBT_FIRMWARES = " \
	rkwifibt-firmware-ap6356-wifi \
	rkwifibt-firmware-ap6356-bt \
	brcm-tools \
"

IMAGE_POSTPROCESS_COMMAND_remove = "gen_rkupdateimg;"

WKS_FILE = "orangepi-r1plus-imagedef.wks.in"

MACHINE_EXTRA_RRECOMMENDS += "kernel-modules"

PREFERRED_PROVIDER_virtual/kernel = "linux-orangepi"
PREFERRED_VERSION_linux-rockchip = "5.10%"

KBUILD_DEFCONFIG = ""

MACHINE_FEATURES += "alsa"

IMAGE_INSTALL += "u-boot-fw-utils rockchip-libmali"

ORANGEPI_BANK_A_PART_UUID = "614e0000-0000-4b53-8000-1d28000054a9"
ORANGEPI_BANK_B_PART_UUID = "614e0000-0000-4b53-8000-1d28000054aa"
ORANGEPI_DATA_PART_UUID = "614e0000-0000-4b53-8000-1d28000054ab"
