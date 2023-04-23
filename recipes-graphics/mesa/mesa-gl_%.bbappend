# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

PACKAGECONFIG:class-target = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'dri dri3 gallium', 'osmesa gallium', d)}"

EXTRA_OEMESON:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '-Dglx=dri', '', d)}"
