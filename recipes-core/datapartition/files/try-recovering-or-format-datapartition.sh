#!/bin/bash

DATAPARTITION_DEV=/dev/mmcblk0
DATAPARTITION_NO=5
DATAPARTITION="${DATAPARTITION_DEV}p${DATAPARTITION_NO}"

try_repair_datapartition_filesystem() {
    e2fsck -y "${DATAPARTITION}" 
}

datapartition_has_filesystem() {
    mount "${DATAPARTITION}" /mnt
    ret=$?

    if [[ "${ret}" == 0 ]];then
        umount "${DATAPARTITION}" 
    fi
    return "${ret}"
}

if [[ ! -e "${DATAPARTITION}" ]]; then
    (echo n; echo ${DATAPARTITION_NO}; echo; echo; echo w)| fdisk "${DATAPARTITION_DEV}"
    try_repair_datapartition_filesystem
    if ! datapartition_has_filesystem; then
        echo | mkfs.ext3 "${DATAPARTITION}"
    fi
fi
