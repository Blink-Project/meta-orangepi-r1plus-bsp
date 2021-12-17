RDEPENDS_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxdamage libxext libxfixes', '', d)}"
