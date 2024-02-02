SUMMARY = "Data partition construction and mount services"
DESCRIPTION = "The services provided here are meant ot build a persistent data partition, on which some selected folders can be bind mounted."
LICENSE = "CLOSED"

inherit systemd

SRC_URI += "\
            file://data.mount \
            file://home-root.mount \
            file://var-lib-docker.mount \
            file://datapartition.service \
            file://try-recovering-or-format-datapartition.sh \
            "

PACKAGES =+ "${PN}-mount ${PN}-docker ${PN}-root"

SYSTEMD_PACKAGES = "${PN}-mount ${PN}-docker ${PN}-root ${PN}"
SYSTEMD_SERVICE:${PN}-mount = "data.mount"
SYSTEMD_SERVICE:${PN}-docker = "var-lib-docker.mount"
SYSTEMD_SERVICE:${PN}-root = "home-root.mount"
SYSTEMD_SERVICE:${PN} = "datapartition.service"

RDEPENDS:${PN} = "bash"
RDEPENDS:${PN}-mount = "${PN}"
RDEPENDS:${PN}-docker = "${PN}-mount"
RDEPENDS:${PN}-root = "${PN}-mount"

do_install() {
    install -d ${D}${sbindir}
    install -d ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/try-recovering-or-format-datapartition.sh ${D}${sbindir}
    install -c -m 0644 ${WORKDIR}/datapartition.service ${D}${systemd_system_unitdir}
    install -c -m 0644 ${WORKDIR}/data.mount ${D}${systemd_system_unitdir}
    install -c -m 0644 ${WORKDIR}/home-root.mount ${D}${systemd_system_unitdir}
    install -c -m 0644 ${WORKDIR}/var-lib-docker.mount ${D}${systemd_system_unitdir}
}
