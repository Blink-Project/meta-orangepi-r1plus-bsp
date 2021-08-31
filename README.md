This README file contains information on the contents of the meta-orangepi-r1plus-bsp layer.

It has been developped by myself (aledemers), for my own orangepi r1+ device, and it does not support all peripherals yet. Help me complete it if you want!  

Dependencies
============

  Layer: meta-rockchip  
  branch: yocto-next  

Patches
=======

Please submit any patches against the meta-orangepi-r1plus-bsp layer to the current github repository. 

Maintainer: Alexi Demers <alexidemers@gmail.com>

Table of Contents
=================

  I. Building core-image-minimal for orangepi-r1+  
  II. Flashing the SD card  

I. Building core-image-minimal for orangepi-r1+ 
=================================================

1. Clone Poky and current repository:  
> git clone http://git.yoctoproject.org/git/poky -b gatesgarth  
> git clone https://github.com/aledemers/meta-orangepi-r1plus.git  
> git clone git://git.yoctoproject.org/meta-rockchip -b yocto-next  
> cd meta-rockchip && git checkout 8715a854fa0778d8695d99b31da0d7557cddf3b1 && cd ..  
2. Start environment:  
> source poky/oe-init-build-env  
3. Edit conf/local.conf and set the correct MACHINE:
> MACHINE = "orangepi-r1plus"  
4. Add meta-orangepi-r1plus and meta-rockchip layers to conf/bblayers.conf  
4. Add a user to the image by adding the following lines at the end of the file:  
> INHERIT += "extrausers"  
> EXTRA_USERS_PARAMS = "usermod -P root root; "  
5. Save local.conf file, then build a basic image:  
> bitbake core-image-minimal  

II. Flashing the SD card
========
1. Find the output SD Card image:  
> tmp/deploy/images/orangepi-r1plus/core-image-minimal-orangepi-r1plus.wic  
2. Insert the SD card to be programmed in your computer.  
3. Flash the image, replacing sdX by your SD card identifier (e.g.: sdb):  
> sudo dd if=tmp/deploy/images/orangepi-r1plus/core-image-minimal-orangepi-r1plus.wic of=/dev/sdX && sync  
4. Remove the SD card from your computer, insert it in your orangepi-r1+, and watch it boot!  
5. UART console access will be provided on the 3-pins header beside the USB connector, baudrate=1500000 (see user orangepi r1+ user manual for more information about debug UART header)  
