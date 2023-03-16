FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://systemd-networkd-wait-online.service \
"

do_install_append(){
    install -d ${D}/${systemd_unitdir}/system/ 
    install -m 0644 ${WORKDIR}/systemd-networkd-wait-online.service ${D}/${systemd_unitdir}/system/ 
}
