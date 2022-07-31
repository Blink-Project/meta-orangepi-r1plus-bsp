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
> git clone https://github.com/JeffyCN/meta-rockchip.git  
> cd meta-rockchip && git checkout 0cac27f272549b40b00c6a6494c59f1b2a18bf6e && cd ..  
2. Start environment:  
> source poky/oe-init-build-env  
3. You can use the example files local.conf.example and bblayers.conf.example from this repository to have a functionnal base. Put these files in "build/conf", removing the ".example" extension.  
4. Build a basic image:  
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
