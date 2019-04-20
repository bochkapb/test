TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh plymouth-log screen-cleanup x11-common apparmor udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh networking checkroot.sh lvm2 iscsid urandom mountdevsubfs.sh checkfs.sh open-iscsi mountall-bootclean.sh mountall.sh bootmisc.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountnfs.sh kmod procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
networking: resolvconf mountkernfs.sh urandom procps
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
iscsid: networking
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
open-iscsi: networking iscsid
mountall-bootclean.sh: mountall.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh udev mountnfs-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
kmod: checkroot.sh
procps: mountkernfs.sh udev
