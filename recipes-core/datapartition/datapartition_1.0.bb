SUMMARY = "Data partition construction and mount services"
DESCRIPTION = "The services provided here are meant to build a persistent data partition, on which some selected folders can be bind mounted."
LICENSE = "CLOSED"

inherit systemd

SRC_URI += "\
            file://var-lib-docker.mount \
            file://init_datapartition \
            "

PACKAGES =+ "${PN}-docker"

SYSTEMD_PACKAGES = "${PN}-docker"
SYSTEMD_SERVICE:${PN}-docker = "var-lib-docker.mount"

RDEPENDS:${PN} = "${PN}-docker e2fsprogs util-linux-fdisk"

do_install() {
    install -d ${D}/${sbindir}/
    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/init_datapartition ${D}/${sbindir}/init_datapartition
    install -c -m 0644 ${WORKDIR}/var-lib-docker.mount ${D}${systemd_system_unitdir}
}

FILES:${PN} += "/sbin/"
