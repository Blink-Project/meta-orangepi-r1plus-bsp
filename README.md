This README file contains information on the contents of the meta-orangepi-r1plus-bsp layer.

See this link for a complete build setup using this layer: [Yocto OrangePi R1+](https://github.com/aledemers/yocto-orangepi-r1plus)

Dependencies
============

  Layer: poky  

Patches
=======

Please submit any patches against the meta-orangepi-r1plus-bsp layer to the current github repository. 

Maintainers: Samuel Geoffroy-Héroux <samuel@geoffroy.dev> and Alexi Demers <alexidemers@gmail.com>

Table of Contents
=================

  I. Building core-image-minimal for orangepi-r1+  
  II. Flashing the SD card  

I. Building core-image-minimal for orangepi-r1+ 
=================================================

1. Clone Poky and current repository:  
> mkdir layers && cd layers  
> git clone http://git.yoctoproject.org/git/poky -b kirkstone 
> git clone git://git.openembedded.org/meta-openembedded -b kirkstone  
> git clone https://github.com/aledemers/meta-orangepi-r1plus.git  
> cd -  
2. Start environment:  
> source layers/poky/oe-init-build-env  
3. You can use the example files local.conf.example and bblayers.conf.example from this repository to have a functionnal base. Put these files in "build/conf", removing the ".example" extension.  
4. Build a basic image:  
> bitbake core-image-minimal  

II. Flashing the SD card
========
1. Find the output SD Card image:  
> tmp/deploy/images/orangepi-r1plus/core-image-minimal-orangepi-r1plus.wic  
2. Insert the SD card to be programmed in your computer.  
3. Flash the image, replacing sdX by your SD card identifier (e.g.: sdb):  
> sudo bmaptool --bmap tmp/deploy/images/orangepi-r1plus/core-image-minimal-orangepi-r1plus.wic.bmap tmp/deploy/images/orangepi-r1plus/core-image-minimal-orangepi-r1plus.wic /dev/sdX  
4. Remove the SD card from your computer, insert it in your orangepi-r1+, and watch it boot!  
5. UART console access will be provided on the 3-pins header beside the USB connector, baudrate=1500000 (see user orangepi r1+ user manual for more information about debug UART header)  
